package gql.health.back.system

import gql.DSL
import graphql.schema.GraphQLObjectType

/**
 * Defines all types related to system services information
 *
 * @since 0.1.0
 */
class Types {
  static final GraphQLObjectType SYSTEMS_TYPE = DSL.type('Service') {
    field 'version', GraphQLString
    field 'os', GraphQLString
    field 'totalMemory', GraphQLBigInteger
    field 'availableMemory', GraphQLBigInteger
    field 'usedMemory', GraphQLBigInteger
  }
}
