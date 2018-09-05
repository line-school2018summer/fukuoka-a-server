package com.example.apiSample.service

import com.example.apiSample.mapper.MessageMapper

import com.example.apiSample.model.MessageData
import org.springframework.stereotype.Service

@Service
class MessageService(private val messageMapper: MessageMapper) {
    fun getMessageData(SenderId: Long): MessageData {
        val messagedata = messageMapper.findMessageDataByMessageId(SenderId)
        return messagedata
    }
    fun getMessageIdByRoomId(SenderId: Long): List<Long> {
        return messageMapper.findMessageIdByRoomId(SenderId)
    }
    fun getMessageByMessageId(MessageId: Long): String {
        return messageMapper.findMessageByMessageId(MessageId)
    }
    fun getSenderIdByMessageId(MessageId: Long): Long {
        return messageMapper.findSenderIdByMessageId(MessageId)
    }
    fun getLastMessageId(): Long {
        if(messageMapper.findLastMessageId()==null)return 0
        return messageMapper.findLastMessageId()!!
    }
    fun postMessageData(senderId: Long,groupId: Long,message:String,messageId:Long){
        return messageMapper.InsertMessageData(senderId,groupId,message,messageId)
    }
    fun deleteMessageData(){
        return messageMapper.DeleteMessageData()
    }
    fun allMessageData():List<MessageData>{
        return messageMapper.AllMessageData()
    }
    fun roomTimeMessageData(MessageId: Long,GroupId:Long):List<MessageData>{
        return messageMapper.GetRoomTimeMessage(MessageId,GroupId)
    }
}