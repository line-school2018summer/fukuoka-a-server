package com.example.apiSample.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.sql.Timestamp

data class user(
        var Id: Long,
        var Name: String,
        var UId: String,
        var Named_id:String
        //var Email: String
)
data class icon(
        var Id: Long,
        var URL: String,
        var UserId: Long
)
data class roominfo(
        var UserId: Long,
        var RoomId: Long
)
data class room(
        var Id: Long,
        var Name: String,
        var isGroup: Boolean,
        var IconURL: String
)
data class message(
        var Id: Long,
        var SenderId: Long,
        var RoomId: Long,
        var Content: String,
        var Type: String,
        @get:JsonProperty("SendTime") var CreatedAt: Timestamp
)
/*
data class UserData(
    var UserId: Long,
    var UserName: String,
    var UserEmail: String,
    var UserIconId: Long,
    var UserIconURL: String
)*/
data class MessageData(
        var SenderId:Long,
        var RoomId:Long,
        var Message:String,
        var MessageType:String,
        var MessageId:Long,
        @get:JsonProperty("SendTime") var SendTime: Timestamp
)
data class GroupInfoData(
        var GroupInfoId:Long,
        var UserId:Long,
        var GroupId:Long
        )

data class GroupsData(
        var GroupId:Long,
        var GroupName:String,
        var isGroup:Boolean,
        var GroupIconURL:String
        )
