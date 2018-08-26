package com.example.apiSample.service

import com.example.apiSample.mapper.UserMapper
import com.example.apiSample.model.GroupInfoData
import org.springframework.stereotype.Service

@Service
class GroupInfoService(private val userMapper: UserMapper) {
    fun getdataByGroupInfoId(groupInfoId: Long): GroupInfoData {
        return userMapper.findGroupInfoDataByGroupInfoId(groupInfoId)
    }
    fun getGroupIdByUserId(UserId: Long): Long {
        return userMapper.findGroupIdByUserId(UserId)
    }
}