package com.example.planup.model

data class ReplyRequest(
    val projectId: String,
    val listId: String,
    val taskId: String,
    val commentId: String,
    val reply: Reply
)
