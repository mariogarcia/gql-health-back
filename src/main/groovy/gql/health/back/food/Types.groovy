package gql.health.back.food

import gql.DSL
import graphql.schema.GraphQLEnumType
import graphql.schema.GraphQLInputObjectType
import graphql.schema.GraphQLObjectType
import graphql.schema.GraphQLScalarType

/**
 * @since 0.1.0
 */
class Types {

  //                  _ _ _            _
  //    __ _ _  ___ _(_) (_)__ _ _ _  | |_ _  _ _ __  ___ ___
  //   / _` | || \ \ / | | / _` | '_| |  _| || | '_ \/ -_|_-<
  //   \__,_|\_,_/_\_\_|_|_\__,_|_|    \__|\_, | .__/\___/__/
  //                                       |__/|_|

  static final GraphQLScalarType GraphQLDate = DSL.scalar('Date') {
    serialize { Date date ->
      date.format('dd/MM/yyyy')
    }
    parseLiteral { String ddMMyyyy ->
      Date.parse('ddMMyyyy', ddMMyyyy)
    }
  }

  static final GraphQLEnumType GraphQLUnitType = DSL.enum('UnitType') {
    description 'Weight measurement type'

    value 'GRAM', 1
    value 'UNIT', 2
  }

  //    _                _     _
  //   (_)_ _  _ __ _  _| |_  | |_ _  _ _ __  ___ ___
  //   | | ' \| '_ \ || |  _| |  _| || | '_ \/ -_|_-<
  //   |_|_||_| .__/\_,_|\__|  \__|\_, | .__/\___/__/
  //          |_|                  |__/|_|

  static final GraphQLInputObjectType GraphQLMealInputEntry = DSL.input('MealEntryInput') {
    description 'every dish or ingredient in a given meal'

    field 'description', nonNull(GraphQLString)
    field 'quantity', nonNull(GraphQLFloat)
    field 'unitType', nonNull(GraphQLUnitType)
  }

  static final GraphQLInputObjectType GraphQLMealInput = DSL.input('MealInput') {
    description 'Every meal we eat'

    field 'entries', list(GraphQLMealInputEntry)
    field 'comments', GraphQLString
    field 'date', GraphQLDate
  }

  //             _             _     _
  //    ___ _  _| |_ _ __ _  _| |_  | |_ _  _ _ __  ___ ___
  //   / _ \ || |  _| '_ \ || |  _| |  _| || | '_ \/ -_|_-<
  //   \___/\_,_|\__| .__/\_,_|\__|  \__|\_, | .__/\___/__/
  //                |_|                  |__/|_|

  static final GraphQLObjectType GraphQLMealEntry = DSL.type('MealEntry') {
    description 'every dish or ingredient in a given meal'

    field 'description', GraphQLString
    field 'quantity', GraphQLFloat
    field 'unitType', GraphQLUnitType
  }

  static final GraphQLObjectType GraphQLMeal = DSL.type('Meal') {
    description 'Represents one given meal at a given time'

    field 'description', GraphQLString
    field 'entries', list(GraphQLMealEntry)
    field 'date', GraphQLDate
  }
}
