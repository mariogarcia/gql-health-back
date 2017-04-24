import static ratpack.groovy.Groovy.ratpack
import static gql.health.back.common.SystemResources.classpath

ratpack {
  include classpath('handlers.groovy')
  include classpath('bindings.groovy')
}
