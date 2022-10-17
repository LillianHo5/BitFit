package com.example.bitfit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {
    @Query("SELECT * FROM entry_table")
    fun getAll(): Flow<List<EntryEntity>>

    @Insert
    fun insertAll(entries: List<EntryEntity>)

    @Insert
    fun insert(articles: EntryEntity)

    @Query("DELETE FROM entry_table")
    fun deleteAll()

    @Query("SELECT AVG(calories) as calories FROM entry_table")
    fun averageCalories(): Int

    @Query("SELECT MIN(calories) as calories FROM entry_table")
    fun minCalories(): Int

    @Query("SELECT MAX(calories) as calories FROM entry_table")
    fun maxCalories(): Int
}