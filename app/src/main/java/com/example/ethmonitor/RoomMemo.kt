package com.example.ethmonitor

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_memo")
class RoomMemo {
    @PrimaryKey(autoGenerate = true) //no에 값이 없을때 자동 증가된 숫자값 db에 입력
    @ColumnInfo
    var no: Long? = null
    @ColumnInfo
    var dvvName:String = ""
    @ColumnInfo
    var ipAddress:String = ""
    @ColumnInfo
    var avg_hash:String = ""

    constructor(dvvName : String ,ipAddress:String, avg_hash:String){
        this.dvvName=dvvName
        this.ipAddress=ipAddress
        this.avg_hash=avg_hash
    }
}