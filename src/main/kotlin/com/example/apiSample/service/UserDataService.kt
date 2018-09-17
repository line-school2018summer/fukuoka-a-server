package com.example.apiSample.service

import com.example.apiSample.controller.BadRequestException
import com.example.apiSample.controller.UnauthorizedException
import com.example.apiSample.mapper.UserMapper
import com.example.apiSample.model.user
import org.springframework.stereotype.Service

@Service
class UserDataService(private val userMapper: UserMapper) {
    fun getAllUserData(): List<user> {
        return userMapper.findAllUserData()
    }
    fun deleteUserData(){
        userMapper.DeleteUserData()
    }
    fun postUserData(UserId:Long, UserName:String, UId:String,NamedId:String):user{
        try {
            userMapper.InsertUserData(UserId, UserName, UId, NamedId)
        }
        catch(e:Exception){
            throw BadRequestException("cannot post")
        }
        val postdata=userMapper.findById(UserId) ?:throw BadRequestException("cannot post")
        return postdata
    }
    fun findById(Id:Long):user{
        if(userMapper.findById(Id)==null){throw Exception("no id")}
        else return userMapper.findById(Id)!!
    }
    fun findByNamedId(NamedId:String):user{
        if(userMapper.findByNamedId(NamedId)==null){throw Exception("no NamedId")}
        else return userMapper.findByNamedId(NamedId)!!
    }
    fun findByUId(UId:String):user{
        if(userMapper.findByUId(UId)==null){throw Exception("no Uid")}
        else return userMapper.findByUId(UId)!!
    }
    fun updatename(Id:Long,changedName:String):user{
        try {
            userMapper.update(Id,changedName)
            return findById(Id)
        }
        catch(e:Exception){
            throw Exception("invalid change")
        }
    }
    fun maxuserid():Long{
        if(userMapper.UserMaxId()==null)return 0
        else return userMapper.UserMaxId()!!
    }
    fun findByName(Name:String):user{
        try {
            return userMapper.findByName(Name)!!
        }
        catch(e:Exception){
            throw Exception("invalid change")
        }
    }
    /*
    fun postUserData(UserId:Long, UserName:String, UId:Long){
        userMapper.InsertUserData(UserId, UserName, UId)
    }*/



    /*
    fun getAllUserData(): List<UserData> {
        return userMapper.findAllUserData()
    }
    fun getUserData(userId: Long): UserData {
        val userdata = userMapper.findUserDataByUserId(userId)
        return userdata
    }
    fun getUserName(userId: Long): String{
        return userMapper.findUserNameByUserId(userId)
    }
    fun getUserIconURL(userId: Long): String{
        return userMapper.findUserIconURLByUserId(userId)
    }

    fun postUserData(UserId:Long, UserName:String, UserEmail:String, UserIconId:Long,UserIconURL:String){
        userMapper.InsertUserData(UserId, UserName, UserEmail, UserIconId,UserIconURL)
    }
    fun deleteUserData(){
        userMapper.DeleteUserData()
    }
    fun getmaxUserIconId(): Long {
        if(userMapper.findLastUserIconId()==null)return 0
        return userMapper.findLastUserIconId()!!
    }
*/
}