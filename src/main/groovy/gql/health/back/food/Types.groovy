package gql.health.back.food

import gql.DSL
import gql.health.back.graphql.CommonTypes
import graphql.schema.GraphQLEnumType
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.GraphQLInputObjectType
import graphql.schema.GraphQLObjectType

/**
 * Food related GraphQL types
 *
 * @since 0.1.0
 */
class Types {

  //                  _ _ _            _
  //    __ _ _  ___ _(_) (_)__ _ _ _  | |_ _  _ _ __  ___ ___
  //   / _` | || \ \ / | | / _` | '_| |  _| || | '_ \/ -_|_-<
  //   \__,_|\_,_/_\_\_|_|_\__,_|_|    \__|\_, | .__/\___/__/
  //                                       |__/|_|

  static final GraphQLEnumType GraphQLUnitType = DSL.enum('UnitType') {
    description 'Weight measurement type'

    value 'GRAM', 'GRAM'
    value 'UNIT', 'GRAM'
  }

  static final GraphQLEnumType GraphQLMealType = DSL.enum('MealType') {
    description 'Meal type'

    value 'BREAKFAST', 'BREAKFAST'
    value 'LUNCH', 'LUNCH'
    value 'DINNER', 'DINNER'
    value 'IN_BETWEEN', 'IN_BETWEEN'
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
    field 'type', nonNull(GraphQLUnitType)
  }

  static final GraphQLInputObjectType GraphQLMealInput = DSL.input('MealInput') {
    description 'Every meal we eat'

    field 'type', nonNull(GraphQLMealType)
    field 'date', nonNull(CommonTypes.GraphQLDate)
    field 'entries', nonNull(list(GraphQLMealInputEntry))
    field('comments') {
      description 'used in case some extra comments are needed'
      type GraphQLString
    }
  }

  //             _             _     _
  //    ___ _  _| |_ _ __ _  _| |_  | |_ _  _ _ __  ___ ___
  //   / _ \ || |  _| '_ \ || |  _| |  _| || | '_ \/ -_|_-<
  //   \___/\_,_|\__| .__/\_,_|\__|  \__|\_, | .__/\___/__/
  //                |_|                  |__/|_|

  static final GraphQLObjectType GraphQLMealEntry = DSL.type('MealEntry') {
    description 'every dish or ingredient in a given meal'

    field 'id', CommonTypes.GraphQLUUID
    field 'description', GraphQLString
    field 'quantity', GraphQLFloat
    field 'type', GraphQLUnitType
  }

  static final GraphQLFieldDefinition MEAL_ID = DSL.field('id') {
    description 'Meal identifier'
    type CommonTypes.GraphQLUUID
  }

  static final GraphQLFieldDefinition MEAL_COMMENTS = DSL.field('comments') {
    description "Meal comments like: 'I was very tired'... or 'I needed to take some sugar because...'"
    type GraphQLString
  }

  static final GraphQLFieldDefinition MEAL_DATE = DSL.field('date') {
    description "When the meal took place. It must have the format '${CommonTypes.DATE_FORMAT}'"
    type CommonTypes.GraphQLDate
  }

  static final GraphQLFieldDefinition MEAL_TYPE = DSL.field('type') {
    description 'The type of meal: it was a breakfast, lunch...'
    type GraphQLMealType
  }
}
