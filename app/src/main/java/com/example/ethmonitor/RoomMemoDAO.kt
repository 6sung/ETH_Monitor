package com.example.ethmonitor

import androidx.room.*

@Dao
interface RoomMemoDAO {
    @Query("select * from room_memo")
    fun getAll() : List<RoomMemo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(memo:RoomMemo)

    @Query("delete from room_memo")
    fun delete()
}