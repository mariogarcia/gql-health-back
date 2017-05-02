import gql.health.back.init.InitModule

import static ratpack.groovy.Groovy.ratpack

import gql.health.back.db.DataSourceModule
import gql.health.back.food.FoodModule
import gql.health.back.graphql.HandlerModule
import gql.health.back.system.SystemModule
import ratpack.groovy.sql.SqlModule

ratpack {
  bindings {
    module InitModule
    module DataSourceModule
    module SqlModule
    module FoodModule
    module SystemModule
    module HandlerModule
  }
}
