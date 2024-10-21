package com.devspace.taskbeats

import androidx.room.Database
import androidx.room.RoomDatabase

@Database([CategoryEntity::class, TaskEntity::class], version = 2)
abstract class TaskBeatDataBase : RoomDatabase() {
    abstract fun getCategoryDAO(): CategoryDAO

    abstract fun getTaskDao(): TaskDao
}