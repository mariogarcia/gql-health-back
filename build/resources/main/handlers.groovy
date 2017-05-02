import ratpack.server.ServerConfigBuilder

import static ratpack.groovy.Groovy.ratpack

import gql.health.back.graphql.Utils
import gql.health.back.common.AppConfig
import gql.health.back.graphql.Handler

/**
 *
 */
ratpack {

  serverConfig { ServerConfigBuilder config ->
    config
      .port(8080)
      .yaml("gql-health.yml")
      .require("", AppConfig)
  }

  handlers {
    prefix('graphql') {
      all(Utils.createBindingHandler(Map))
      post(Handler)
    }
    files {
      dir('static').indexFiles('index.html')
    }
  }
}
