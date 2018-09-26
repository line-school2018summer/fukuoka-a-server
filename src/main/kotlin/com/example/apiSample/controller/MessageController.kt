package com.example.apiSample.controller

import com.example.apiSample.firebase.AuthGateway
import com.example.apiSample.model.MessageData
import com.example.apiSample.model.message
import com.example.apiSample.service.MessageService
import com.example.apiSample.service.UserDataService
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.sql.Timestamp
import com.google.firebase.auth.FirebaseAuth


data class messageidAndSendtime(
        var messageid: Long,
        @get:JsonProperty("CreatedAt") var CreatedAt: Timestamp
)
@RestController
class MessageController( private val messageService: MessageService
                         ,private val authGateway: AuthGateway
                        ,private val userService: UserDataService
){
    @GetMapping(
            value = ["/message"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun allMessage():List<message> {

        return messageService.allMessageData()
    }
    @GetMapping(
            value = ["/messagestring"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun allMessagestring(@RequestHeader("Token")idToken: String):List<message> {

        val uid = authGateway.verifyIdToken(idToken) ?: throw UnauthorizedException("invalid token")
        return messageService.allMessageData()
        /*
        val uid = "5yJjo36Dd5fN0SZchN2iD89Aipx2"

        return FirebaseAuth.getInstance().createCustomToken(uid)
        */
    }
    @PostMapping(
            value = ["/message/{RoomId}/{Content}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getMessage(@RequestHeader("Token")idToken: String,
                   @PathVariable("RoomId" ) roomId: Long,
                   @PathVariable("Content" ) content: String):
           // messageidAndSendtime
    Boolean
    {
        val uid = authGateway.verifyIdToken(idToken) ?: throw UnauthorizedException("invalid token")
        val id = userService.findByUId(uid).Id
        messageService.postMessageData(messageService.MessageIdMax()+1,id,roomId,content)
        return true
        //return messageidAndSendtime(messageService.MessageIdMax(),messageService.getMessageByMessageId(messageService.MessageIdMax()).CreatedAt)
    }
    @DeleteMapping(
            value = ["/message"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun deleteMessage():String {
        //val uid = authGateway.verifyIdToken(token) ?: throw UnauthorizedException("invalid token")
        messageService.deleteMessageData()
        return "ALL MESSAGE DELETE"
    }
    @PutMapping(
            value = ["/message/{messageid}/{content}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun changeMessage(@PathVariable("messageid" ) messageId: Long,@PathVariable("content" ) content: String,@RequestHeader("Token")idToken: String
    ):message {
        val uid = authGateway.verifyIdToken(idToken) ?: throw UnauthorizedException("invalid token")
        val id = userService.findByUId(uid).Id
        return messageService.updateMessage(id,messageId,content)
    }

    @GetMapping(
            value = ["/message/{roomid}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun allMessagebyRoomid(@PathVariable("roomid" ) roomid: Long):List<message> {

        return messageService.MessageByRoomId(roomid)
    }
    /*
    @GetMapping(
            value = ["/message"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun allMessage():List<MessageData> {
        return messageService.allMessageData()
    }
    @GetMapping(
            value = ["/message/{messageId}/{groupId}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun allMessage(@PathVariable("messageId" )messageId: Long,@PathVariable("groupId" )groupId: Long):List<MessageData> {
        return messageService.roomTimeMessageData(messageId,groupId)
    }
    @GetMapping(
            value = ["/messageid/{messageid}/messagedata"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getMessageData(@PathVariable("messageid" ) userId: Long): MessageData {
        return messageService.getMessageData(userId)
    }


    @GetMapping(
            value = ["/roomid/{roomid}/messageid"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getMessageIdByRoomId(@PathVariable("roomid" ) roomId: Long):List<Long> {
        return messageService.getMessageIdByRoomId(roomId)
    }
    @GetMapping(
            value = ["/messageid/{messageid}/message"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getMessageByMessageId(@PathVariable("messageid" ) messageId: Long):String {
        return messageService.getMessageByMessageId(messageId)
    }
    @GetMapping(
            value = ["/messageid/{messageid}/senderid"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getSenderIdByMessageId(@PathVariable("messageid" ) messageId: Long):Long {
        return messageService.getSenderIdByMessageId(messageId)
    }
    @GetMapping(
            value = ["/messageid"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getSenderIdByMessageId():Long {
        return messageService.getLastMessageId()
    }
    @PostMapping(
            value = ["/message/{SenderId}/{GroupId}/{Message}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getMessage(@PathVariable("SenderId" ) senderId: Long, @PathVariable("GroupId" ) groupId: Long,
                   @PathVariable("Message" ) message: String): Boolean{
        messageService.postMessageData(senderId,groupId,message,messageService.getLastMessageId()+1)
        return true
        //return messageidAndSendtime(messageService.getLastMessageId()+1,//タイム)
    }
    @DeleteMapping(
            value = ["/message"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun deleteMessage():String {
        messageService.deleteMessageData()
        return "ALL MESSAGE DELETE"
    }
    */
}