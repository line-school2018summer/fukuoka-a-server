package com.example.apiSample.controller

import com.example.apiSample.firebase.AuthGateway
import com.example.apiSample.model.*
import com.example.apiSample.service.GroupInfoService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import com.google.firebase.auth.FirebaseAuth



@RestController
class GroupController(private val groupinfoService: GroupInfoService
                      ,private val authGateway: AuthGateway
){
    //groupデータの全消去
    @DeleteMapping(
            value = ["/room"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun deletegroup():String {
        //val uid = authGateway.verifyIdToken("s") ?: throw UnauthorizedException("invalid token")
        groupinfoService.deleteroomdata()
        return "ALL ROOM DELETE"
    }
    //groupinfoデータの全消去
    @DeleteMapping(
            value = ["/roominfo"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun deletegroupinfo():String {
        //val uid = authGateway.verifyIdToken(token) ?: throw UnauthorizedException("invalid token")
        groupinfoService.deleteroominfodata()
        return "ALL ROOM DELETE"
    }
    //group全検索
    @GetMapping(
            value = ["/room"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getAllGroupdata(): List<room> {
        //val uid = authGateway.verifyIdToken(token) ?: throw UnauthorizedException("invalid token")
        return groupinfoService.allroomdata()
    }
    //groupinfo全検索
    @GetMapping(
            value = ["/roominfo"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getAllGroupinfodata(): List<roominfo> {
        //val uid = authGateway.verifyIdToken(token) ?: throw UnauthorizedException("invalid token")
        return groupinfoService.allroominfodata()
    }
    //データの送信
    @PostMapping(
            value = ["/roominfo/{UserId}/{RoomId}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getgroupinfoList(@PathVariable("UserId" ) userId: Long, @PathVariable("RoomId" ) roomId: Long
    ):Boolean {
        //val uid = authGateway.verifyIdToken(token) ?: throw UnauthorizedException("invalid token")
        groupinfoService.postroomInfoData(userId,roomId)
        return true
    }
    //データの送信
    @PostMapping(
            value = ["/room/{Name}/{isGroup}/{IconURL}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getgroupList(@PathVariable("Name" ) name: String,
                     @PathVariable("isGroup" ) isGroup: Boolean, @PathVariable("IconURL" ) iconurl: String
    ):room {
        //val uid = authGateway.verifyIdToken(token) ?: throw UnauthorizedException("invalid token")
        groupinfoService.postroomData(groupinfoService.roomidmax()+1,name,isGroup,iconurl)
        return room(groupinfoService.roomidmax()+1,name,isGroup,iconurl)
    }
    //roomidでuseridを出す
    @GetMapping(
            value = ["/roominfo/{RoomId}/userid"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun UserIdByRoomId(@PathVariable("RoomId" ) roomId: Long
    ):List<user>{
        //val uid = authGateway.verifyIdToken(token) ?: throw UnauthorizedException("invalid token")
        return groupinfoService.getMembers(roomId)
    }

    //nameの変更
    @PutMapping(
            value = ["/room/{RoomId}/{Name}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun UpdateRoomName(@PathVariable("RoomId" ) roomId: Long,@PathVariable("Name" ) name: String
    ):room{
        //val uid = authGateway.verifyIdToken(token) ?: throw UnauthorizedException("invalid token")
        groupinfoService.updateroom(roomId,name)
        return groupinfoService.getRoomById(roomId)
    }
    //roomidのuser変更
    //useridからroomidを出す
    @GetMapping(
            value = ["/roominfo/{UserId}/roomid"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun RoomIdByUserId(@PathVariable("UserId" ) userId: Long
    ):List<Long>{
        return groupinfoService.getRoomByUserId(userId)
    }

    /*
    //groupデータの全消去
    @DeleteMapping(
            value = ["/group"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun deletegroup():String {
        groupinfoService.deletegroupdata()
        return "ALL GROUP DELETE"
    }
    //groupinfoデータの全消去
    @DeleteMapping(
            value = ["/groupinfo"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun deletegroupinfo():String {
        groupinfoService.deletegroupinfodata()
        return "ALL GROUPINFO DELETE"
    }
    //group全検索
    @GetMapping(
            value = ["/group"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getAllGroupdata(): List<GroupsData> {
        return groupinfoService.allgroupdata()
    }
    //groupinfo全検索
    @GetMapping(
            value = ["/groupinfo"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getAllGroupinfodata(): List<GroupInfoData> {
        return groupinfoService.allgroupinfodata()
    }
    //groupinfoidでデータ検索
    @GetMapping(
            value = ["/groupinfoid/{groupinfoid}/data"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getdataByGroupInfoId(@PathVariable("groupinfoid" ) GroupInfoId: Long): GroupInfoData {
        return groupinfoService.getdataByGroupInfoId(GroupInfoId)
    }
    //useridでgroupid検索
    @GetMapping(
            value = ["/userid/{userid}/groupid"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getGroupIdByUserId(@PathVariable("userid" ) UserId: Long):List<Long> {
        return groupinfoService.getGroupIdByUserId(UserId)
    }
    //groupidでuserid検索
    @GetMapping(
            value = ["/groupid/{groupid}/userid"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getUserIdByGroupId(@PathVariable("groupid" ) GroupId: Long):List<Long> {
        return groupinfoService.getUserIdByGroupId(GroupId)
    }
    //groupidでデータ検索
    @GetMapping(
            value = ["/groupid/{groupid}/groupsdata"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getGroupsdataByGroupId(@PathVariable("groupid" ) GroupId: Long): GroupsData {
        return groupinfoService.getGroupsdataByGroupId(GroupId)
    }
    //データの送信
    @PostMapping(
            value = ["/groupinfo/{UserId}/{GroupId}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getgroupinfoList(@PathVariable("UserId" ) userId: Long, @PathVariable("GroupId" ) groupId: Long
    ):Boolean {
        groupinfoService.postGroupInfoData(groupinfoService.getLastGroupInfoId(),userId,groupId)
        return true
    }
    //データの送信
    @PostMapping(
            value = ["/group/{GroupId}/{GroupName}/{isGroup}/{GroupIconURL}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getgroupList(@PathVariable("GroupId" ) groupid: Long, @PathVariable("GroupName" ) groupname: String,
                     @PathVariable("isGroup" ) isGroup: Boolean, @PathVariable("GroupIconURL" ) groupiconurl: String
    ):Boolean {
        groupinfoService.postGroupData(groupid,groupname,isGroup,groupiconurl)
        return true
    }
    */
}