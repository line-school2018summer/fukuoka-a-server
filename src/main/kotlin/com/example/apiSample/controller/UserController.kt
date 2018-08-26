package com.example.apiSample.controller

import com.example.apiSample.model.*
import com.example.apiSample.service.MessageService
import com.example.apiSample.service.UserDataService
import com.example.apiSample.service.UserProfileService
import com.example.apiSample.service.GroupInfoService
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
                     private val messageService: MessageService, private val groupinfoService: GroupInfoService) {
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
            value = ["/userget/{id}/userdata"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getData(@PathVariable("id" ) userId: Long): UserData {
        return userDataService.getUserData(userId)
    }

    @GetMapping(
            value = ["/userget/{userid}/username"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getUserName(@PathVariable("userid" ) userId: Long): String {
        return userDataService.getUserName(userId)
    }

    @GetMapping(
            value = ["/userget/{userid}/usericonurl"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getUserIconId(@PathVariable("userid" ) userId: Long): String {
        return userDataService.getUserIconURL(userId)
    }

    @GetMapping(
            value = ["/messageid/{messageid}/messagedata"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getMessageData(@PathVariable("messageid" ) userId: Long): MessageData {
        return messageService.getMessageData(userId)
    }


    @GetMapping(
            value = ["/roomid/{roomid}/messageid"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getMessageIdByRoomId(@PathVariable("roomid" ) roomId: Long):List<Long> {
        return messageService.getMessageIdByRoomId(roomId)
    }
    @GetMapping(
            value = ["/messageid/{messageid}/message"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getMessageByMessageId(@PathVariable("messageid" ) messageId: Long):String {
        return messageService.getMessageByMessageId(messageId)
    }
    @GetMapping(
            value = ["/messageid/{messageid}/senderid"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getSenderIdByMessageId(@PathVariable("messageid" ) messageId: Long):Long {
        return messageService.getSenderIdByMessageId(messageId)
    }
    @GetMapping(
            value = ["/messageid"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getSenderIdByMessageId():Long {
        return messageService.getLastMessageId()
    }
    @GetMapping(
            value = ["/groupinfoid/{groupinfoid}/data"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getdataByGroupInfoId(@PathVariable("groupinfoid" ) GroupInfoId: Long):GroupInfoData {
        return groupinfoService.getdataByGroupInfoId(GroupInfoId)
    }

    @GetMapping(
            value = ["/userid/{userid}/groupid"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getGroupIdByUserId(@PathVariable("userid" ) UserId: Long):Long {
        return groupinfoService.getGroupIdByUserId(UserId)
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

    @PostMapping(
            value = ["/userpost/{UserId}/{UserName}/{UserEmail}/{UserIconId}/userdata"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getList(@PathVariable("UserId" ) userId: Long, @PathVariable("UserName" ) userName: String,
                @PathVariable("UserEmail" ) userEmail: String, @PathVariable("UserIconId" ) userIconId: Long
    ):String {
        userDataService.postUserData(userId,userName,userEmail,userIconId)
        return "post: USERID:"+userId+"  USERNAME:"+userName+"  USEREMAIL:"+userEmail+"  USERICONID:"+userIconId
    }

    @PostMapping(
            value = ["/messagepost/{SenderId}/{RoomId}/{RoomType}/{Message}/{MessageId}/messagedata"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getMessage(@PathVariable("SenderId" ) senderId: Long, @PathVariable("RoomId" ) roomId: Long,
                    @PathVariable("RoomType" ) roomType: String, @PathVariable("Message" ) message: String,
                      @PathVariable("MessageId" ) messageId: Long
    ):String {
        if(messageService.getLastMessageId()!=messageId)
            return "canceled"
            else
        //messageService.postMessageData(senderId,roomId,roomType,message,messageId)
        //return "post: SENDERID  "+senderId+"ROOMID: "+roomId+"  ROOMTYPE: "+roomType+"  MESSAGE: "+message+"  MESSAGEID: "+messageId
            return "ok"
    }

}
