package com.example.apiSample.controller

import com.example.apiSample.firebase.AuthGateway
import com.example.apiSample.model.MessageData
import com.example.apiSample.model.message
import com.example.apiSample.service.MessageService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.sql.Timestamp


data class messageidAndSendtime(
        var messageid: Long,
        var SendTime: Timestamp
)
@RestController
class MessageController( private val messageService: MessageService
                         ,private val authGateway: AuthGateway
){
    @GetMapping(
            value = ["/message"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun allMessage():List<message> {
        //val uid = authGateway.verifyIdToken(token) ?: throw UnauthorizedException("invalid token")
        return messageService.allMessageData()
    }
    @PostMapping(
            value = ["/message/{SenderId}/{RoomId}/{Content}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getMessage(@PathVariable("SenderId" ) senderId: Long,
                   @PathVariable("RoomId" ) roomId: Long,
                   @PathVariable("Content" ) content: String): Boolean{
        //val uid = authGateway.verifyIdToken(token) ?: throw UnauthorizedException("invalid token")
        messageService.postMessageData(messageService.MessageIdMax()+1,senderId,roomId,content)
        return true
        //return messageidAndSendtime(messageService.getLastMessageId()+1,//タイム)
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