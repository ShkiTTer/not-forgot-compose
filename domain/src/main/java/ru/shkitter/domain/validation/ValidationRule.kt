package ru.shkitter.domain.validation

import android.util.Patterns

sealed interface ValidationRule {
    fun validate(): Boolean

    data class Email(private val value: String) : ValidationRule {
        override fun validate(): Boolean = Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }

    data class MinLength(private val value: String, private val length: Int) : ValidationRule {
        companion object {
            const val PASSWORD_MIN_LENGTH = 8
        }

        override fun validate(): Boolean = value.length >= length
    }
}