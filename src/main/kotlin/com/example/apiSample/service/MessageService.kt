package com.example.apiSample.service

import com.example.apiSample.mapper.MessageMapper

import com.example.apiSample.model.MessageData
import com.example.apiSample.model.message
import org.springframework.stereotype.Service

@Service
class MessageService(private val messageMapper: MessageMapper) {
    fun postMessageData(Id: Long,senderId: Long,roomId: Long,content:String){
        return messageMapper.InsertMessageData(Id,senderId,roomId,content)
    }
    fun deleteMessageData(){
        return messageMapper.DeleteMessageData()
    }
    fun allMessageData():List<message>{
        return messageMapper.AllMessageData()
    }
    fun MessageIdMax():Long{
        if(messageMapper.MessageMaxId()==null)return 0
        return messageMapper.MessageMaxId()!!
    }
    /*
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
    */
}