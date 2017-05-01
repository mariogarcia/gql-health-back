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

  static final String DATE_FORMAT = 'dd/MM/yyyy'

  static final GraphQLScalarType GraphQLDate = DSL.scalar('Date') {
    serialize { Date date ->
      date.format(DATE_FORMAT)
    }
    parseLiteral { String ddMMyyyy ->
      Date.parse(DATE_FORMAT, ddMMyyyy)
    }
    parseValue { String ddMMyyyy ->
      Date.parse(DATE_FORMAT, ddMMyyyy)
    }
  }

  static final GraphQLEnumType GraphQLUnitType = DSL.enum('UnitType') {
    description 'Weight measurement type'

    value 'GRAM', 1
    value 'UNIT', 2
  }

  static final GraphQLEnumType GraphQLMealType = DSL.enum('MealType') {
    description 'Meal type'

    value 'BREAKFAST', 1
    value 'LUNCH', 2
    value 'DINNER', 3
    value 'IN_BETWEEN', 4
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

    field 'description', nonNull(GraphQLString)
    field 'type', nonNull(GraphQLMealType)
    field 'date', nonNull(GraphQLDate)
    field 'entries', nonNull(list(GraphQLMealInputEntry))
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
