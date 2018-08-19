package com.example.apiSample.mapper

import com.example.apiSample.model.MessageData
import com.example.apiSample.model.UserData
import com.example.apiSample.model.UserProfile
import com.example.apiSample.model.UserList
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
        SELECT UserId, UserName, UserEmail,UserIconId FROM userDB WHERE UserId=#{userId}
        """
    )
    fun findUserDataByUserId(userId: Long): UserData
    @Select(
            """
        SELECT SenderId, RoomId, RoomType,Message,MessageType,MessageId,SendTime FROM MessageDB WHERE SenderId=#{SenderId}
        """
    )
    fun findMessageByMessageId(SenderId: Long): MessageData
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
