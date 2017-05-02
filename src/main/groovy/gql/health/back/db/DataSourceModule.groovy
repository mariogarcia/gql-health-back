package gql.health.back.db

import com.google.inject.AbstractModule
import javax.sql.DataSource

/**
 * Creates a new {@link DataSource}
 * @since 0.1.0
 */
class DataSourceModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(DataSource).toProvider(HikariDataSourceProvider)
  }
}
