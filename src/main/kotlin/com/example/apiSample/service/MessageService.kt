package com.example.apiSample.service

import com.example.apiSample.mapper.UserMapper
import com.example.apiSample.model.MessageData
import org.springframework.stereotype.Service

@Service
class MessageService(private val userMapper: UserMapper) {
    fun getMessageData(SenderId: Long): MessageData {
        val messagedata = userMapper.findMessageByMessageId(SenderId)
        return messagedata
    }
}