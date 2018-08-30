package com.example.apiSample.mapper

import com.example.apiSample.model.*
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Delete

@Mapper
interface UserMapper {
    @Select(
            """
        SELECT UserId, UserName, UserEmail,UserIconId,UserIconURL FROM usersDB
        """
    )
    fun findAllUserData(): List<UserData>
    @Select(
            """
        SELECT UserId, UserName, UserEmail,UserIconId,UserIconURL FROM usersDB WHERE UserId=#{userId}
        """
    )
    fun findUserDataByUserId(userId: Long): UserData
    @Select(
            """
        SELECT UserName FROM usersDB WHERE UserId=#{userId}
        """
    )
    fun findUserNameByUserId(userId: Long): String
    @Select(
            """
        SELECT UserIconURL FROM usersDB WHERE UserId=#{userId}
        """
    )
    fun findUserIconURLByUserId(userId: Long): String

    @Select(
            """
        SELECT SenderId, GroupId,Message,MessageType,MessageId,SendTime FROM MessageDB WHERE MessageId=#{MessageId}
        """
    )
    fun findMessageDataByMessageId(MessageId: Long): MessageData
    @Select(
            """
        SELECT MessageId FROM MessageDB WHERE GroupId=#{GroupId}
        """
    )
    fun findMessageIdByRoomId(GroupId: Long): List<Long>
    @Select(
            """
        SELECT Message FROM MessageDB WHERE MessageId=#{MessageId}
        """
    )
    fun findMessageByMessageId(MessageId: Long): String
    @Select(
            """
        SELECT SenderId FROM MessageDB WHERE MessageId=#{MessageId}
        """
    )
    fun findSenderIdByMessageId(MessageId: Long): Long

    @Select(
            """
        SELECT MAX(MessageId) FROM MessageDB
        """
    )
    fun findLastMessageId(): Long?
    @Select(
            """
        SELECT MAX(UserIconId) FROM usersDB
        """
    )
    fun findLastUserIconId(): Long?
    @Select(
            """
        SELECT MAX(GroupInfoId) FROM groups
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
    @Insert(
            """
         INSERT INTO usersDB (UserId, UserName, UserEmail,UserIconId,UserIconURL) VALUES (#{UserId},#{UserName},#{UserEmail},#{UserIconId},#{UserIconURL})
        """
    )
    fun InsertUserData(UserId:Long,UserName:String,UserEmail:String,UserIconId:Long,UserIconURL:String)
    @Insert(
            """
         INSERT INTO MessageDB (SenderId,GroupId,Message,MessageType,MessageId,SendTime) VALUES (#{senderId},#{groupId},#{message},"string",#{messageId},CURRENT_TIMESTAMP)
        """
    )
    fun InsertMessageData(senderId: Long,groupId: Long,message:String,messageId:Long)
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
    fun InsertGroupData(GroupInfoId: Long,GroupName:String,isGroup:Boolean,GroupIconURL:String)
    @Delete(
            """
         DELETE FROM MessageDB
        """
    )
    fun DeleteMessageData()
    @Delete(
            """
         DELETE FROM usersDB
        """
    )
    fun DeleteUserData()
    @Delete(
            """
         DELETE FROM groups
        """
    )
    fun DeleteGroupData()
    @Delete(
            """
         DELETE FROM GroupAttendInfo
        """
    )
    fun DeleteGroupInfoData()
    @Select(
            """
         SELECT * FROM MessageDB
        """
    )
    fun AllMessageData():List<MessageData>
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
}
