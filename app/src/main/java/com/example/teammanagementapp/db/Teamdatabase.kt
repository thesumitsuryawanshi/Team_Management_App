package com.example.teammanagementapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teammanagementapp.db.entities.Members
import com.example.teammanagementapp.db.entities.Teams


@Database(entities = [Teams::class, Members::class], exportSchema = false, version = 1)
abstract class Teamdatabase : RoomDatabase() {


    abstract val dao: DAO

    companion object {

        private var INSTANCE: Teamdatabase? = null
        fun getInstance(context: Context): Teamdatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context,
                    Teamdatabase::class.java,
                    "TeamDb"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}