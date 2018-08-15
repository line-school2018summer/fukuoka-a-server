package com.example.apiSample.service

import com.example.apiSample.mapper.UserMapper
import com.example.apiSample.model.MessageData
import org.springframework.stereotype.Service

@Service
class MessageService(private val userMapper: UserMapper) {
    fun getMessageData(MessageId: Long): MessageData {
        val messagedata = userMapper.findMessageByMessageId(MessageId)
        return messagedata
    }
}