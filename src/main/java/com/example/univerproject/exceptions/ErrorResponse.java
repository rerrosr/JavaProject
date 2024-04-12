package com.example.univerproject.exceptions;

/**
 * The type Error response.
 */
public record ErrorResponse(String message) {
  /**
   * Gets message.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }
}
