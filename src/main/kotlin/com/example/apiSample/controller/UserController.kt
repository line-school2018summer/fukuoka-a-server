package com.example.apiSample.controller

import com.example.apiSample.firebase.AuthGateway
import com.example.apiSample.model.*
import com.example.apiSample.service.UserDataService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class UserController(private val userDataService: UserDataService
                     ,private val authGateway: AuthGateway
        ) {
    //全ユーザデータ取得
    @GetMapping(
            value = ["/user"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getAlluserData(): List<user> {
        //val uid = authGateway.verifyIdToken(token) ?: throw UnauthorizedException("invalid token")
        return userDataService.getAllUserData()
    }

    //データの全消去
    @DeleteMapping(
            value = ["/user"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun deleteUser():String {
        //val uid = authGateway.verifyIdToken(token) ?: throw UnauthorizedException("invalid token")
        userDataService.deleteUserData()
        return "ALL USER DELETE"
    }
/*
    //データの送信
    @PostMapping(
            value = ["/user/{UserId}/{UserName}/{UserEmail}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getList(@PathVariable("UserId" ) userId: Long, @PathVariable("UserName" ) userName: String,
                @PathVariable("UserEmail" ) userEmail: String):Boolean {
        //val uid = authGateway.verifyIdToken(token) ?: throw UnauthorizedException("invalid token")
        userDataService.postUserData(userId,userName,userEmail)
        return true
    }
    */

    //データの送信
    @PostMapping(
            value = ["/user/{UserName}/{NamedId}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getList(@PathVariable("UserName" ) userName: String,
                @RequestHeader("Token")idToken: String,
                @PathVariable("NamedId" ) namedId: String):user {
        val uid = authGateway.verifyIdToken(idToken) ?: throw UnauthorizedException("invalid token")
        return userDataService.postUserData(userDataService.maxuserid()+1,userName,uid,namedId)
        //return userDataService.postUserData(userDataService.maxuserid()+1,userName,"test",namedId)
    }
    //useridでデータ検索
    @GetMapping(
            value = ["/user/{id}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getData(@PathVariable("id" ) Id: Long): user {
        return userDataService.findById(Id)
    }
    //nameでデータ検索
    @GetMapping(
            value = ["/user/Name"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun ByName(@RequestParam("Name")Name: String): user {
        return userDataService.findByName(Name)
    }
    //名前IDでデータ検索
    @GetMapping(
            value = ["/user/NamedId"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun ByNamedId(@RequestParam("NamedId")NamedId: String): List<user> {
        return userDataService.findByNamedId(NamedId)
    }
    @PutMapping(
            value = ["/user"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun updatename(//@RequestHeader("Token")idToken: String,
                   @RequestParam("name")changedName:String):user{
        //val uid = authGateway.verifyIdToken(idToken) ?: throw UnauthorizedException("invalid token")
        //val id = userDataService.findByUId(uid).Id
        return userDataService.updatename(1,changedName)
    }


    //動作確認用
    @GetMapping(
            value = ["/hello"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun hello(): String{
        //val uid = authGateway.verifyIdToken(token) ?: throw UnauthorizedException("invalid token")
        return "{\"greeting\": \"Hello World!\"}"
    }

    //動作確認用
    @GetMapping(
            value = ["/user/MyId"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun myId(@RequestHeader("Token")idToken: String): Long{
        val uid = authGateway.verifyIdToken(idToken) ?: throw UnauthorizedException("invalid token")
        val id = userDataService.findByUId(uid).Id
        return id
    }
    /*
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
*/


}
