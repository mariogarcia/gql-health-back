package gql.health.back.init

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import com.google.inject.multibindings.Multibinder
import gql.health.back.db.FlywayService
import ratpack.service.Service

/**
 * Binds all initial services
 *
 * @since 0.1.0
 */
class InitModule extends AbstractModule {
  @Override
  protected void configure() {
    Multibinder<Service> servicesBinder = Multibinder.newSetBinder(binder(), Service)

    servicesBinder.with {
      addBinding().to(FlywayService).in(Scopes.SINGLETON)
    }
  }
}
