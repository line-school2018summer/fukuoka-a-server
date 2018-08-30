package com.example.apiSample.service

import com.example.apiSample.mapper.UserMapper
import com.example.apiSample.model.UserData
import org.springframework.stereotype.Service

@Service
class UserDataService(private val userMapper: UserMapper) {
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

}