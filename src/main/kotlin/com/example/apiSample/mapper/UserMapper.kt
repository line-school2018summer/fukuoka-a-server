package com.example.apiSample.mapper

import com.example.apiSample.model.MessageData
import com.example.apiSample.model.UserData
import com.example.apiSample.model.UserProfile
import com.example.apiSample.model.UserList
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
        SELECT UserId, UserName, UserEmail,UserIconId FROM userDB WHERE id=#{userId}
        """
    )
    fun findUserDataByUserId(userId: Long): UserData
    @Select(
            """
        SELECT SenderId, RoomId, RoomType,Message,MeaageType,MessageId,SendTime, FROM MessageDB WHERE id=#{SenderId}
        """
    )
    fun findMessageByMessageId(SenderId: Long): MessageData
}
