package gql.health.back.food

import gql.DSL
import graphql.schema.DataFetchingEnvironment
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.GraphQLObjectType

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
   * This type has been declared here as an instance field and not in {@link Types} as an
   * static field because it needs to resolve a given field ('entries') using the {@link FoodService} instance.
   *
   * @since 0.1.0
   */
  GraphQLObjectType GraphQLMeal = DSL.type('Meal'){
    description 'an entry describing what you have eaten in a given moment of the day'

    addField Types.MEAL_ID
    addField Types.MEAL_COMMENTS
    addField Types.MEAL_DATE
    addField Types.MEAL_TYPE

    field('entries') {
      description 'a list of different food you ate in a given meal'
      type list(Types.GraphQLMealEntry)
      fetcher { DataFetchingEnvironment env ->
        def meal = env.source as Map

        return foodService.findAllEntriesByMealId(meal.id as UUID)
      }
    }
  }

  /**
   * Defines how to get all meals of a given date
   *
   * @return an instance of {@link GraphQLFieldDefinition}
   * @since 0.1.0
   */
  GraphQLFieldDefinition findAllMealsByDate() {
    DSL.field('findAllMealsByDate') {
      description 'looks for all meals of a person at a given date'

      type list(GraphQLMeal)
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
    DSL.field('addMeal') {
      description 'adds a new meal'

      type GraphQLMeal
      argument 'meal', Types.GraphQLMealInput
      fetcher { DataFetchingEnvironment env ->
        foodService.addNewMeal(env.arguments.meal as Map)
      }
    }
  }
}
