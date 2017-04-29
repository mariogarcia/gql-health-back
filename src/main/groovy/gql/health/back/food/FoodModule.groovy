package gql.health.back.food

import com.google.inject.AbstractModule
import com.google.inject.Scopes

/**
 * @since 0.1.3
 */
class FoodModule extends AbstractModule {
  @Override
  void configure() {
    bind(FoodGraphQL).in(Scopes.SINGLETON)
  }
}
