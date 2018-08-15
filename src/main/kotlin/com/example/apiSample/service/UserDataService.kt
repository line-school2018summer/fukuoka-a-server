package com.example.apiSample.service

import com.example.apiSample.mapper.UserMapper
import com.example.apiSample.model.UserData
import org.springframework.stereotype.Service

@Service
class UserDataService(private val userMapper: UserMapper) {
    fun getUserData(userId: Long): UserData {
        val userdata = userMapper.findUserDataByUserId(userId)
        return userdata
    }
}