package com.example.apiSample.mapper

import com.example.apiSample.model.GroupInfoData
import com.example.apiSample.model.GroupsData
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface GroupMapper {
    @Select(
            """
        SELECT MAX(GroupInfoId) FROM GroupAttendInfo
        """
    )
    fun findLastGroupInfoId(): Long?

    @Select(
            """
        SELECT GroupInfoId,UserId,GroupId FROM GroupAttendInfo WHERE GroupInfoId=#{GroupInfoId}
        """
    )
    fun findGroupInfoDataByGroupInfoId(GroupInfoId: Long): GroupInfoData
    @Select(
            """
        SELECT GroupId FROM GroupAttendInfo WHERE UserId=#{UserId}
        """
    )
    fun findGroupIdByUserId(UserId: Long): List<Long>
    @Select(
            """
        SELECT UserId FROM GroupAttendInfo WHERE GroupId=#{GroupId}
        """
    )
    fun findUserIdByGroupId(GroupId: Long): List<Long>
    @Select(
            """
        SELECT GroupId,GroupName,isGroup,GroupIconURL FROM groups WHERE GroupId=#{GroupId}
        """
    )
    fun GroupsdataByGroupId(GroupId: Long): GroupsData
    @Delete(
            """
         DELETE FROM groups
        """
    )
    fun DeleteGroupData()


    @Select(
            """
         SELECT * FROM groups
        """
    )
    fun AllGroupData(): List<GroupsData>
    @Select(
            """
         SELECT * FROM GroupAttendInfo
        """
    )
    fun AllGroupinfoData(): List<GroupInfoData>
    @Insert(
            """
         INSERT INTO GroupAttendInfo (GroupInfoId,UserId,GroupId) VALUES (#{GroupInfoId},#{UserId},#{GroupId})
        """
    )
    fun InsertGroupInfoData(GroupInfoId: Long,UserId: Long,GroupId:Long)
    @Insert(
            """
         INSERT INTO groups (GroupId,GroupName,isGroup,GroupIconURL) VALUES (#{GroupId},#{GroupName},#{isGroup},#{GroupIconURL})
        """
    )
    fun InsertGroupData(GroupId: Long,GroupName:String,isGroup:Boolean,GroupIconURL:String)
    @Delete(
            """
         DELETE FROM GroupAttendInfo
        """
    )
    fun DeleteGroupInfoData()
}