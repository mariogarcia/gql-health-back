package gql.health.back.food

import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import groovy.util.logging.Slf4j

import javax.inject.Inject
import java.sql.Connection

/**
 * @since 0.1.3
 */
@Slf4j
class FoodService {

  @Inject Sql sql

  /**
   * Adds a new meal and its entries
   *
   * @since 0.1.0
   */
  Map addNewMeal(Map meal) {
    List<Map> entries = meal.entries as List<Map>
    UUID mealId = UUID.randomUUID()

    log.info("Adding new meal: $meal")

    sql.withTransaction {
      sql
        .executeInsert(/INSERT INTO gql.meal (id, comments, "type", "date") VALUES (?, ?, ?, ?)/,
        mealId,
        meal.comments,
        meal.type,
        meal.date)
        .findResult { List<Object> ids ->
        ids.find() as Long
      }

      entries.each { Map entry ->
        UUID entryId = UUID.randomUUID()
        sql.executeInsert(
          /INSERT INTO gql.meal_entry (id, meal_id, description, quantity, "type") VALUES (?, ?, ?, ?, ?)/,
          entryId,
          mealId,
          entry.description,
          entry.quantity,
          entry.type)
      }
    }

    return meal + [id: mealId]
  }

  /**
   * Retrieves all meals added in the given day
   *
   * @param date an instance of {@link Date}
   * @since 0.1.0
   */
  List<Map> findAllByDate(Date date) {
    return sql.rows(/SELECT * FROM gql.meal where "date" = ?/, date) as List<Map>
  }

  List<Map> findAllEntriesByMealId(UUID uuid) {
    return sql.rows(/SELECT * FROM gql.meal_entry where meal_id = ?/, uuid) as List<Map>
  }
}
