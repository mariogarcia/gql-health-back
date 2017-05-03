package gql.health.back.food

import gql.health.back.common.ID
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
    log.info("Adding new meal: $meal")

    def mealToSave = meal + [id: ID.generateID()]
    def entries = meal.entries.collect { Map entry -> entry + [id: ID.generateID()] } as List<Map>

    sql.withTransaction {
      sql
        .executeInsert(
          /INSERT INTO gql.meal (id, comments, "type", "date") VALUES (?, ?, ?, ?)/,
          mealToSave.id,
          mealToSave.comments,
          mealToSave.type,
          mealToSave.date)
        .findResult { List<Object> ids ->
          ids.find() as Long
        }

      entries.each { Map entry ->
        sql.executeInsert(
          /INSERT INTO gql.meal_entry (meal_id, id, description, quantity, "type") VALUES (?, ?, ?, ?, ?)/,
          mealToSave.id,
          entry.id,
          entry.description,
          entry.quantity,
          entry.type)
      }
    }

    return mealToSave
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
