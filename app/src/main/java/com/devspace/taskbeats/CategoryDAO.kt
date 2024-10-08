package com.devspace.taskbeats

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDAO {
    @Query("Select * From categoryentity")
    fun getAll(): List<CategoryEntity>

    @Insert
    fun insertAll (vararg categoryEntity: CategoryEntity)


}