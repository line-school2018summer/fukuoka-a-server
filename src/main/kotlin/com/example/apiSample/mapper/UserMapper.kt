package com.example.apiSample.mapper

import com.example.apiSample.model.MessageData
import com.example.apiSample.model.UserData
import com.example.apiSample.model.UserProfile
import com.example.apiSample.model.UserList
import com.example.apiSample.model.GroupInfoData
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface UserMapper {
    @Select(
        """
        SELECT id, name, email, created_at, updated_at FROM users WHERE id=#{userId}
        """
    )
    fun findByUserId(userId: Long): UserProfile
    @Select(
        """
        SELECT id, name, email FROM users WHERE name LIKE CONCAT('%', #{searchStr}, '%')
        """
    )
    fun findBySearchStr(searchStr: String): ArrayList<UserList>
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
        SELECT SenderId, RoomId, RoomType,Message,MessageType,MessageId,SendTime FROM MessageDB WHERE MessageId=#{MessageId}
        """
    )
    fun findMessageDataByMessageId(MessageId: Long): MessageData
    @Select(
            """
        SELECT MessageId FROM MessageDB WHERE RoomId=#{RoomId}
        """
    )
    fun findMessageIdByRoomId(RoomId: Long): List<Long>
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
    fun findLastMessageId(): Long

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
    fun findGroupIdByUserId(UserId: Long): Long
    @Insert(
            """
         INSERT INTO userDB (UserId, UserName, UserEmail,UserIconId) VALUES (#{UserId},#{UserName},#{UserEmail},#{UserIconId})
        """
    )
    fun InsertUserData(UserId:Long,UserName:String,UserEmail:String,UserIconId:Long)
    @Insert(
            """
         INSERT INTO MessageDB (SenderId,RoomId,RoomType,Message,MessageType,MessageId,SendTime) VALUES (#{senderId},#{roomId},#{roomType},#{message},"string",#{messageId},CURRENT_TIMESTAMP)
        """
    )
    fun InsertMessageData(senderId: Long,roomId: Long,roomType:String,message:String,messageId:Long)
}
