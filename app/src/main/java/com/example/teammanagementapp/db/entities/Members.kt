package com.example.teammanagementapp.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Members(
    @PrimaryKey
    val Mid: Int,
    val Mname: String,
    val Tid: Int
)
