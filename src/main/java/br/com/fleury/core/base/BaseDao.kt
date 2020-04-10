package br.com.fleury.core.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T : BaseEntity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMany(items: List<T>)

    @Update
    fun update(ficha: T)

    @Delete
    fun delete(ficha: T)
}