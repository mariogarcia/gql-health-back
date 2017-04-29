package gql.health.back.food

/**
 * @since 0.1.3
 */
class FoodService {

  List<Map> meals = []

  Map addNewMeal(Map meal) {
    def newMeal = [id: meals.size() + 1] + meal
    meals << newMeal
    return newMeal
  }

  Map findLast() {
    return meals.last()
  }

  List<Map> listAll() {
    return meals
  }
}
