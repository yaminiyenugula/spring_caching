
package com.example.caching;

/**
 * Represents a book entity used throughout the application.
 *
 * A Java record provides an immutable data carrier with
 * automatically generated constructor, accessors, equals,
 * hashCode, and toString methods.
 *
 *  isbn  unique identifier of the book
 *  title title or name of the book
 */
public record Book(String isbn, String title) {
}
