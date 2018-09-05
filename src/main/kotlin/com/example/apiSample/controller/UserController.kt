package com.example.apiSample.controller

import com.example.apiSample.model.*
import com.example.apiSample.service.UserDataService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class UserController(private val userDataService: UserDataService) {
    //動作確認用
    @GetMapping(
            value = ["/hello"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun hello(): String{
        return "{\"greeting\": \"Hello World!\"}"
    }
    //全データ取得
    @GetMapping(
            value = ["/user"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getAlluserData(): List<UserData> {
        return userDataService.getAllUserData()
    }
    //useridでデータ検索
    @GetMapping(
            value = ["/userget/{id}/userdata"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getData(@PathVariable("id" ) userId: Long): UserData {
        return userDataService.getUserData(userId)
    }
    //useridで名前検索
    @GetMapping(
            value = ["/userget/{userid}/username"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getUserName(@PathVariable("userid" ) userId: Long): String {
        return userDataService.getUserName(userId)
    }
    //useridでURL検索
    @GetMapping(
            value = ["/userget/{userid}/usericonurl"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getUserIconId(@PathVariable("userid" ) userId: Long): String {
        return userDataService.getUserIconURL(userId)
    }
    //データの送信
    @PostMapping(
            value = ["/user/{UserId}/{UserName}/{UserEmail}/{UserIconURL}/userdata"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getList(@PathVariable("UserId" ) userId: Long, @PathVariable("UserName" ) userName: String,
                @PathVariable("UserEmail" ) userEmail: String, @PathVariable("UserIconURL" ) userIconURL: String
    ):Boolean {
        userDataService.postUserData(userId,userName,userEmail,userDataService.getmaxUserIconId()+1,userIconURL)
        return true
    }
    //データの全消去
    @DeleteMapping(
            value = ["/user"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun deleteUser():String {
        userDataService.deleteUserData()
        return "ALL USER DELETE"
    }



}
