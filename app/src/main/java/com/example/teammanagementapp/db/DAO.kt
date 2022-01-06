package com.example.teammanagementapp.db

import androidx.room.*
import com.example.teammanagementapp.db.entities.Members
import com.example.teammanagementapp.db.entities.Teams
import com.example.teammanagementapp.db.relations.TeamWithMembers


@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: Teams)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMember(Member: Members)
//
//    @Transaction
//    @Query("SELECT Mname FROM Members WHERE Tid=:id")
//    suspend fun getMembersWithTeams(id: Int): TeamWithMembers

}

