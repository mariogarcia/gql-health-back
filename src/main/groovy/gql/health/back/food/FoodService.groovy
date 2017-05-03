package gql.health.back.food

import groovy.sql.Sql
import groovy.util.logging.Slf4j

import javax.inject.Inject

/**
 * Service accessing meal related information from the persistence layer
 *
 * @since 0.1.0
 */
@Slf4j
class FoodService {

  @Inject Sql sql

  /**
   * Adds a new meal and its entries
   *
   * @param meal meal information to be persisted
   * @return the persisted meal with the persistence id
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
   * @return a list of meals introduced that day
   * @since 0.1.0
   */
  List<Map> findAllByDate(Date date) {
    return sql.rows(/SELECT * FROM gql.meal where "date" = ?/, date) as List<Map>
  }

  /**
   * Returns a list of all entries belonging to the meal whose id has been passed
   * as parameter
   *
   * @param uuid meal id
   * @return a list of meal entries belonging to the specified meal
   * @since 0.1.0
   */
  List<Map> findAllEntriesByMealId(UUID uuid) {
    return sql.rows(/SELECT * FROM gql.meal_entry where meal_id = ?/, uuid) as List<Map>
  }
}
