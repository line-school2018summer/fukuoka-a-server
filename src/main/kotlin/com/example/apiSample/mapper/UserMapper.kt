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
        SELECT MAX(UserIconId) FROM usersDB
        """
    )
    fun findLastUserIconId(): Long?

    @Insert(
            """
         INSERT INTO usersDB (UserId, UserName, UserEmail,UserIconId,UserIconURL) VALUES (#{UserId},#{UserName},#{UserEmail},#{UserIconId},#{UserIconURL})
        """
    )
    fun InsertUserData(UserId:Long,UserName:String,UserEmail:String,UserIconId:Long,UserIconURL:String)



    @Delete(
            """
         DELETE FROM usersDB
        """
    )
    fun DeleteUserData()

}
