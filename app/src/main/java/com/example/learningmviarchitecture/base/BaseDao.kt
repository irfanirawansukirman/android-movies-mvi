package com.example.learningmviarchitecture.base

import androidx.room.*

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(arr: List<T>): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(obj: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(obj: List<T>)

    @Delete
    fun delete(obj: T)

//    @Transaction
//    open suspend fun insertOrUpdate(obj: T) {
//        val id = insert(obj)
//        if (id == -1L) update(obj)
//    }
//
//    @Transaction
//    open suspend fun insertOrUpdate(objList: List<T>) {
//        val insertResult = insert(objList)
//        val updateList = mutableListOf<T>()
//
//        for (i in insertResult.indices) {
//            if (insertResult[i] == -1L) updateList.add(objList[i])
//        }
//
//        if (updateList.isNotEmpty()) update(updateList)
//    }
}