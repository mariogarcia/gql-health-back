package gql.health.back.food

import gql.DSL
import graphql.schema.DataFetchingEnvironment
import graphql.schema.GraphQLFieldDefinition

import javax.inject.Inject

/**
 * Defines all possible queries and mutations over the Food/Meal domain
 *
 * @since 0.1.0
 */
class FoodGraphQL {

  /**
   * Service accessing the underlying datastore
   *
   * @since 0.1.0
   */
  @Inject
  FoodService foodService

  /**
   * Defines how to get all meals of a given date
   *
   * @return an instance of {@link GraphQLFieldDefinition}
   * @since 0.1.0
   */
  GraphQLFieldDefinition findAllMealsByDate() {
    def GraphQLMealType = DSL.type('Meal'){
      field 'id', GraphQLString
      field 'comments', GraphQLString
      field 'date', Types.GraphQLDate
      field('entries') {
        type list(Types.GraphQLMealEntry)
        fetcher { DataFetchingEnvironment env ->
          Map<String,?> meal = env.source as Map<String,?>
          foodService.findAllEntriesByMealId(meal.ID as UUID)
        }
      }
    }
    DSL.field('findAllMealsByDate') {
      description 'looks for all meals of a person at a given date'

      type list(GraphQLMealType)
      argument 'date', Types.GraphQLDate
      fetcher { DataFetchingEnvironment env ->
        foodService.findAllByDate(env.arguments.date as Date)
      }
    }
  }

  /**
   * Defines how to add a new meal
   *
   * @return an instance of {@link GraphQLFieldDefinition}
   * @since 0.1.0
   */
  GraphQLFieldDefinition addMeal() {
    def GraphQLMealType = DSL.type('Meal'){
      field 'id', GraphQLString
      field 'comments', GraphQLString
      field 'date', Types.GraphQLDate
      field('entries') {
        type list(Types.GraphQLMealEntry)
        fetcher { DataFetchingEnvironment env ->
          foodService.findAllEntriesByMealId(env.arguments.id as UUID)
        }
      }
    }
    DSL.field('addMeal') {
      description 'adds a new meal'
      type GraphQLMealType
      argument 'meal', Types.GraphQLMealInput
      fetcher { DataFetchingEnvironment env ->
        foodService.addNewMeal(env.arguments.meal as Map)
      }
    }
  }
}
