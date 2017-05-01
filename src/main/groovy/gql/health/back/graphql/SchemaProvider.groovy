package gql.health.back.graphql

import gql.health.back.food.FoodGraphQL

import static gql.DSL.schema

import gql.health.back.system.SystemGraphQL
import graphql.schema.GraphQLSchema

import javax.inject.Inject
import javax.inject.Provider


/**
 * Provides a singleton instance of the {@link GraphQLSchema} type
 *
 * @since 0.1.0
 */
class SchemaProvider implements Provider<GraphQLSchema> {

  @Inject SystemGraphQL graphQLSystem
  @Inject FoodGraphQL foodGraphQL

  @Override
  GraphQLSchema get() {
    return schema {
      queries {
        addField graphQLSystem.systemServices
        addField foodGraphQL.findAllMealsByDate()
      }

      mutations {
        addField foodGraphQL.addMeal()
      }
    }
  }
}
