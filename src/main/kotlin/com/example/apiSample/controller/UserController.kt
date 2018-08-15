package com.example.apiSample.controller

import com.example.apiSample.model.MessageData
import com.example.apiSample.model.UserData
import com.example.apiSample.model.UserProfile
import com.example.apiSample.model.UserList
import com.example.apiSample.service.MessageService
import com.example.apiSample.service.UserDataService
import com.example.apiSample.service.UserProfileService
import com.example.apiSample.service.UserService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

data class UserListResponse(
        var id: Long,
        var name: String,
        var email: String
)

data class PostSearchRequest(
        val search_str: String
)

@RestController
class UserController(private val userProfileService: UserProfileService, private val userService: UserService,private val userDataService: UserDataService,
                     private val messageService: MessageService) {
    @GetMapping(
            value = ["/user"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun hello(): String{
        return "{\"greeting\": \"Hello World!\"}"
    }

    @GetMapping(
            value = ["/user/{id}/profile"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getProfile(@PathVariable("id" ) userId: Long): UserProfile {
        return userProfileService.getProfile(userId)
    }

    @GetMapping(
            value = ["/user/{id}/userdata"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getData(@PathVariable("id" ) userId: Long): UserData {
        return userDataService.getUserData(userId)
    }

    @GetMapping(
            value = ["/message/{id}/messagedata"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getMessageData(@PathVariable("id" ) userId: Long): MessageData {
        return messageService.getMessageData(userId)
    }

    @PostMapping(
            value = ["/user/search"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getList(@RequestBody request: PostSearchRequest): Map<String, List<UserListResponse>> {
        val userList: ArrayList<UserList> = userService.findUsersList(request.search_str)
        return mapOf("results" to userList.map {
            UserListResponse(
                    id = it.id,
                    name = it.name,
                    email = it.email
            )
        })
    }

}
