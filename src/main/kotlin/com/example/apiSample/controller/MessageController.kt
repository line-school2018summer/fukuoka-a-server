package com.example.apiSample.controller

import com.example.apiSample.model.MessageData
import com.example.apiSample.service.MessageService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.sql.Timestamp

@RestController
data class messageidAndSendtime(
        var messageid: Long,
        var SendTime: Timestamp
)

class MessageController( private val messageService: MessageService){
    @GetMapping(
            value = ["/Allmessage"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun allMessage():List<MessageData> {
        return messageService.allMessageData()
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
            value = ["/messagepost/{SenderId}/{GroupId}/{Message}/messagedata"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getMessage(@PathVariable("SenderId" ) senderId: Long, @PathVariable("GroupId" ) groupId: Long,
                   @PathVariable("Message" ) message: String): String{
        messageService.postMessageData(senderId,groupId,message,messageService.getLastMessageId()+1)
        return "test"
        //return messageidAndSendtime(messageService.getLastMessageId()+1,//タイム)
    }
    @DeleteMapping(
            value = ["/messagedelete"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun deleteMessage():String {
        messageService.deleteMessageData()
        return "ALL MESSAGE DELETE"
    }
}