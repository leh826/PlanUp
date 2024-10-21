package com.example.planup.model

enum class Status {
    TODO,
    DOING,
    DONE;


    fun toDatabaseString(): String {
        return this.name
    }

    companion object {
        fun fromDatabaseString(value: String): Status? {
            return entries.find { it.name == value }
        }
    }
}