import gql.health.back.food.FoodModule

import static ratpack.groovy.Groovy.ratpack

import gql.health.back.graphql.HandlerModule
import gql.health.back.system.SystemModule

ratpack {
  bindings {
    module HandlerModule
    module SystemModule
    module FoodModule
  }
}
