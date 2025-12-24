package com.pLg.core.user

/**
 * Extension functions for working with collections of User.
 */

fun List<User>.sortedByCreated(): List<User> =
    this.sortedBy { it.createdAtEpochMillis }

fun List<User>.findByEmail(email: String): User? =
    this.firstOrNull { it.email.equals(email, ignoreCase = true) }

fun List<User>.names(): List<String> =
    this.map { it.name }

fun List<User>.anonymized(): List<User> =
    this.map { it.anonymized() }
