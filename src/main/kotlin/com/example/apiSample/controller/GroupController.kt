package com.example.apiSample.controller

import com.example.apiSample.model.GroupInfoData
import com.example.apiSample.model.GroupsData
import com.example.apiSample.service.GroupInfoService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class GroupController(private val groupinfoService: GroupInfoService){
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
}