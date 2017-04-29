package gql.health.back.food

import gql.DSL
import graphql.schema.DataFetchingEnvironment
import graphql.schema.GraphQLFieldDefinition
import groovy.transform.CompileDynamic

import javax.inject.Inject

/**
 * @since 0.1.3
 */
class FoodGraphQL {

  @Inject
  FoodService foodService

  GraphQLFieldDefinition findAllMealsByDate() {
    return DSL.field('findAllMealsByDate') {
      description 'Looks for all meals of a person at a given date'

      type Types.GraphQLMeal
      fetcher { DataFetchingEnvironment env ->
        foodService.findLast()
      }
    }
  }

  @CompileDynamic
  GraphQLFieldDefinition addMeal() {
    return DSL.field('addMeal') {
      description 'adds a new meal'

      type Types.GraphQLMeal
      fetcher this.&addMealToDatastore
      argument('meal') {
        type Types.GraphQLMealInput
      }
    }
  }

  private Map addMealToDatastore(DataFetchingEnvironment env) {
    Map meal = env.arguments.meal as Map

    return foodService.addNewMeal(meal)
  }
}
