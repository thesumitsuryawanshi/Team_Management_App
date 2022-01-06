package com.example.teammanagementapp.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.teammanagementapp.db.entities.Members
import com.example.teammanagementapp.db.entities.Teams


data class TeamWithMembers(
    @Embedded
    val Tid: Teams,
    @Relation(
        parentColumn = "Tid",
        entityColumn = "Tid"
    )
    val Members: List<Members>
)
