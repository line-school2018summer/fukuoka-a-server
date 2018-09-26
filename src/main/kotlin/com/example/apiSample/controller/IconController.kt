package com.example.apiSample.controller

import com.example.apiSample.model.icon
import com.example.apiSample.model.room
import com.example.apiSample.model.roominfo
import com.example.apiSample.service.GroupInfoService
import com.example.apiSample.service.IconService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class IconController(private val iconService: IconService){
    //groupデータの全消去
    @DeleteMapping(
            value = ["/icon"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun deletegroup():String {
        //val uid = authGateway.verifyIdToken(token) ?: throw UnauthorizedException("invalid token")
        iconService.deleteIconData()
        return "ALL ICON DELETE"
    }

    //group全検索
    @GetMapping(
            value = ["/icon"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getAllGroupdata(): List<icon> {
        //val uid = authGateway.verifyIdToken(token) ?: throw UnauthorizedException("invalid token")
        return iconService.allIconData()
    }

    //データの送信
    @PostMapping(
            value = ["/icon/{URL}/{UserId}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getgroupinfoList(@PathVariable("URL" ) URL: String,@PathVariable("UserId" ) userId: Long
    ):Boolean {
        //val uid = authGateway.verifyIdToken(token) ?: throw UnauthorizedException("invalid token")
        iconService.postIconData(iconService.IconIdMax()+1,URL,userId)
        return true
    }

}