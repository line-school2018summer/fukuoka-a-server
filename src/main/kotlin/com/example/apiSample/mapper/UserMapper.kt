package com.example.apiSample.mapper

import com.example.apiSample.model.*
import org.apache.ibatis.annotations.*

@Mapper
interface UserMapper {
    @Select(
            """
        SELECT * FROM authuser
        """
    )
    fun findAllUserData(): List<user>
    @Insert(
            """
         INSERT INTO authuser (Id, Name, UId,NamedId) VALUES (#{UserId},#{UserName},#{UId},#{NamedId})
        """
    )
    fun InsertUserData(UserId:Long,UserName:String,UId:String,NamedId:String)

    @Delete(
            """
         DELETE FROM authuser
        """
    )
    fun DeleteUserData()

    @Update(
            """
         UPDATE authuser SET name=#{changedName} WHERE Id=#{id}
        """
    )
    fun update(id:Long,changedName:String):Unit

    @Select(
            """
        SELECT * FROM authuser WHERE Id=#{id}
        """
    )
    fun findById(id:Long): user?

    @Select(
            """
        SELECT * FROM authuser WHERE UId=#{UId}
        """
    )
    fun findByUId(UId:String): user

    @Select(
            """
        SELECT * FROM authuser WHERE NamedId=#{NamedId}
        """
    )
    fun findByNamedId(NamedId:String): user?

    @Select(
            """
        SELECT * FROM authuser WHERE Name=#{Name}
        """
    )
    fun findByName(Named:String): user?

    @Select(
            """
         SELECT MAX(Id) FROM authuser
        """
    )
    fun UserMaxId():Long?
    /*
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
    */

}
