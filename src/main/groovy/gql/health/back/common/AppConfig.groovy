package gql.health.back.common

/**
 * Holds the configuration of the app
 *
 * @since 0.1.0
 */
class AppConfig {

  /**
   * App current running version
   *
   * @since 0.1.0
   */
  String version

  /**
   * Data related with the data source connection
   *
   * @since 0.1.0
   */
  DataSource dataSource

  /**
   * Contains information about the datasource connection
   *
   * @since 0.1.0
   */
  static class DataSource {

  /**
   * Database driver class
   *
   * @since 0.1.0
   */
    String driverClassName

    /**
     * URI to connect to the datasource
     *
     * @since 0.1.0
     */
    String uri

    /**
     * Username used to connect to the data source
     * @since 0.1.0
     */
    String username

    /**
     * Password used to connect to the data source
     *
     * @since 0.1.0
     */
    String password

    /**
     * A comma separated names of schemas to be migrated
     *
     * @since 0.1.0
     */
    String flywaySchemas
  }
}
