package com.example.apiSample.service

import com.example.apiSample.mapper.UserMapper
import com.example.apiSample.model.GroupInfoData
import com.example.apiSample.model.GroupsData
import org.springframework.stereotype.Service

@Service
class GroupInfoService(private val userMapper: UserMapper) {
    fun getdataByGroupInfoId(groupInfoId: Long): GroupInfoData {
        return userMapper.findGroupInfoDataByGroupInfoId(groupInfoId)
    }
    fun getGroupIdByUserId(UserId: Long): List<Long> {
        return userMapper.findGroupIdByUserId(UserId)
    }
    fun getUserIdByGroupId(GroupId: Long): List<Long> {
        return userMapper.findUserIdByGroupId(GroupId)
    }
    fun getGroupsdataByGroupId(GroupId:Long):GroupsData{
        return userMapper.GroupsdataByGroupId(GroupId)
    }
    fun deletegroupinfodata(){
        return userMapper.DeleteGroupInfoData()
    }
    fun deletegroupdata(){
        return userMapper.DeleteGroupData()
    }
    fun allgroupdata(): List<GroupsData>{
        return userMapper.AllGroupData()
    }
    fun allgroupinfodata(): List<GroupInfoData>{
        return userMapper.AllGroupinfoData()
    }
    fun postGroupInfoData(GroupInfoId:Long,UserId:Long, GroupId:Long){
        userMapper.InsertGroupInfoData(GroupInfoId,UserId,GroupId)
    }
    fun postGroupData(GroupId:Long,GroupName:String, isGroup:Boolean,GroupIconURL:String){
        userMapper.InsertGroupData(GroupId,GroupName,isGroup,GroupIconURL)
    }
    fun getLastGroupInfoId(): Long {
        if(userMapper.findLastGroupInfoId()==null)return 0
        return userMapper.findLastGroupInfoId()!!
    }
}