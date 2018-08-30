package com.example.apiSample.service

import com.example.apiSample.mapper.UserMapper
import com.example.apiSample.model.MessageData
import org.springframework.stereotype.Service

@Service
class MessageService(private val userMapper: UserMapper) {
    fun getMessageData(SenderId: Long): MessageData {
        val messagedata = userMapper.findMessageDataByMessageId(SenderId)
        return messagedata
    }
    fun getMessageIdByRoomId(SenderId: Long): List<Long> {
        return userMapper.findMessageIdByRoomId(SenderId)
    }
    fun getMessageByMessageId(MessageId: Long): String {
        return userMapper.findMessageByMessageId(MessageId)
    }
    fun getSenderIdByMessageId(MessageId: Long): Long {
        return userMapper.findSenderIdByMessageId(MessageId)
    }
    fun getLastMessageId(): Long {
        if(userMapper.findLastMessageId()==null)return 0
        return userMapper.findLastMessageId()!!
    }
    fun postMessageData(senderId: Long,groupId: Long,message:String,messageId:Long){
        return userMapper.InsertMessageData(senderId,groupId,message,messageId)
    }
    fun deleteMessageData(){
        return userMapper.DeleteMessageData()
    }
    fun allMessageData():List<MessageData>{
        return userMapper.AllMessageData()
    }
}