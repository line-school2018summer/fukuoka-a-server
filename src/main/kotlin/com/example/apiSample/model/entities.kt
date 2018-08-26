package com.example.apiSample.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.sql.Timestamp

data class UserProfile(
    var id: Long,
    var name: String,
    var email: String,
    @get:JsonProperty("created_at") var createdAt: Timestamp,
    @get:JsonProperty("updated_at") var updatedAt: Timestamp
)

data class UserList(
        var id: Long,
        var name: String,
        var email: String
)

data class UserData(
    var UserId: Long,
    var UserName: String,
    var UserEmail: String,
    var UserIconId: Long,
    var UserIconURL: String
)
data class MessageData(
        var SenderId:Long,
        var RoomId:Long,
        var RoomType:String,
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
/*
data class GroupsData(
        var GroupId:Long,
        var GroupName:String,
        var isGroup:Boolean,
        var GroupIconURL:String,
        )
        */