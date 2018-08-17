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
    var UserName: Long,
    var UserEmail: Long,
    var UserIconId: Long
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
