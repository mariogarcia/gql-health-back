package gql.health.back.db

import com.google.inject.Provider
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import gql.health.back.common.AppConfig

import javax.inject.Inject
import javax.sql.DataSource

/**
 * @since 0.1.0
 */
class HikariDataSourceProvider implements Provider<DataSource> {

  @Inject
  AppConfig appConfig

  @Override
  DataSource get() {
    def config = new HikariConfig()

    config.driverClassName = appConfig.dataSource.driverClassName
    config.jdbcUrl = appConfig.dataSource.uri
    config.username = appConfig.dataSource.username
    config.password = appConfig.dataSource.password

    return new HikariDataSource(config)
  }
}
