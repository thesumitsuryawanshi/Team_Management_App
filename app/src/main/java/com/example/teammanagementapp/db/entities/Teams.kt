package com.example.teammanagementapp.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Teams(
    @PrimaryKey
    val Tid: Int,
    val Tname: String
)