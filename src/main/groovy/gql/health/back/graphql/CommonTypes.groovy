package gql.health.back.graphql

import gql.DSL
import graphql.language.StringValue
import graphql.schema.GraphQLScalarType

/**
 * Common GraphQL types used in the app
 *
 * @since 0.1.0
 */
class CommonTypes {

  static final String DATE_FORMAT = 'dd/MM/yyyy'

  static final GraphQLScalarType GraphQLDate = DSL.scalar('Date') {
    serialize { Date date ->
      date.format(DATE_FORMAT)
    }
    parseLiteral { StringValue ddMMyyyy ->
      Date.parse(DATE_FORMAT, ddMMyyyy.value)
    }
    parseValue { String ddMMyyyy ->
      Date.parse(DATE_FORMAT, ddMMyyyy)
    }
  }

  static final GraphQLScalarType GraphQLUUID = DSL.scalar('UUID') {
    serialize { UUID uuid ->
      uuid.toString()
    }
    parseLiteral { StringValue uuid ->
      UUID.fromString(uuid.value)
    }
    parseValue { String uuid ->
      UUID.fromString(uuid)
    }
  }
}
