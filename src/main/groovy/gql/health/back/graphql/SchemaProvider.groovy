package gql.health.back.graphql

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

  @Inject
  SystemGraphQL graphQLSystem

  @Override
  GraphQLSchema get() {
    return schema {
      query('queryRoot') {
        addField graphQLSystem.systemServices
      }
    }
  }
}
