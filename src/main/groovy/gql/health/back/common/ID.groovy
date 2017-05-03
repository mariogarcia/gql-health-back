package gql.health.back.common

/**
 * This class is responsible to generate domain ids
 *
 * @since 0.1.0
 */
class ID {

  /**
   * Generates a new valid {@link UUID}
   *
   * @return an instance of a valid {@link UUID}
   * @since 0.1.0
   */
  static UUID generateID() {
    return UUID.randomUUID()
  }
}
