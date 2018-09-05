package com.example.apiSample.service

import com.example.apiSample.mapper.GroupMapper
import com.example.apiSample.model.GroupInfoData
import com.example.apiSample.model.GroupsData
import com.example.apiSample.model.room
import com.example.apiSample.model.roominfo
import org.springframework.stereotype.Service

@Service
class GroupInfoService(private val groupMapper: GroupMapper) {
    fun allroomdata(): List<room>{
        return groupMapper.AllRoomData()
    }
    fun allroominfodata(): List<roominfo>{
        return groupMapper.AllRoominfoData()
    }
    fun postroomInfoData(UserId:Long, RoomId:Long){
        groupMapper.InsertRoominfoData(UserId,RoomId)
    }
    fun postroomData(Id:Long,Name:String, isGroup:Boolean,IconURL:String){
        groupMapper.InsertRoomData(Id,Name,isGroup,IconURL)
    }
    fun deleteroominfodata(){
        return groupMapper.DeleteRoomInfoData()
    }
    fun deleteroomdata(){
        return groupMapper.DeleteRoomData()
    }
    fun roomidmax():Long{
        if(groupMapper.RoomIdMax()==null)return 0
        return groupMapper.RoomIdMax()!!
    }
    /*
    fun getdataByGroupInfoId(groupInfoId: Long): GroupInfoData {
        return groupMapper.findGroupInfoDataByGroupInfoId(groupInfoId)
    }
    fun getGroupIdByUserId(UserId: Long): List<Long> {
        return groupMapper.findGroupIdByUserId(UserId)
    }
    fun getUserIdByGroupId(GroupId: Long): List<Long> {
        return groupMapper.findUserIdByGroupId(GroupId)
    }
    fun getGroupsdataByGroupId(GroupId:Long):GroupsData{
        return groupMapper.GroupsdataByGroupId(GroupId)
    }
    fun deletegroupinfodata(){
        return groupMapper.DeleteGroupInfoData()
    }
    fun deletegroupdata(){
        return groupMapper.DeleteGroupData()
    }
    fun allgroupdata(): List<GroupsData>{
        return groupMapper.AllGroupData()
    }
    fun allgroupinfodata(): List<GroupInfoData>{
        return groupMapper.AllGroupinfoData()
    }
    fun postGroupInfoData(GroupInfoId:Long,UserId:Long, GroupId:Long){
        groupMapper.InsertGroupInfoData(GroupInfoId,UserId,GroupId)
    }
    fun postGroupData(GroupId:Long,GroupName:String, isGroup:Boolean,GroupIconURL:String){
        groupMapper.InsertGroupData(GroupId,GroupName,isGroup,GroupIconURL)
    }
    fun getLastGroupInfoId(): Long {
        if(groupMapper.findLastGroupInfoId()==null)return 0
        return groupMapper.findLastGroupInfoId()!!
    }
    */
}