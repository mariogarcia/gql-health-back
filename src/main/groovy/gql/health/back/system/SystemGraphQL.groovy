package gql.health.back.system

import gql.DSL
import graphql.schema.DataFetchingEnvironment
import graphql.schema.GraphQLFieldDefinition

import javax.inject.Inject

/**
 * Links GraphQL calls to 'services' to service implementation responsible
 * for fetching system information
 *
 * @since 0.1.0
 */
class SystemGraphQL {

  @Inject
  SystemService systemService

  /**
   * Returns implementation for fetching system information
   *
   * @return an instance of {@link GraphQLFieldDefinition}
   * @since 0.1.0
   */
  GraphQLFieldDefinition getSystemServices() {
    return DSL.field('services') {
      type Types.GraphQLSystemHealth
      fetcher { DataFetchingEnvironment env ->
        return [
          os: systemService.systemOS,
          version: systemService.systemVersion
        ]
      }
    }
  }
}
