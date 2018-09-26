package com.example.apiSample.service

import com.example.apiSample.mapper.IconMapper
import com.example.apiSample.mapper.MessageMapper
import com.example.apiSample.model.icon
import com.example.apiSample.model.message
import org.springframework.stereotype.Service
import javax.swing.Icon

@Service
class IconService(private val iconmapper: IconMapper) {
    fun postIconData(Id: Long,URL:String,UserId: Long){
        return iconmapper.InsertIconData(Id,URL,UserId)
    }
    fun deleteIconData(){
        return iconmapper.DeleteIconData()
    }
    fun allIconData(): List<icon> {
        return iconmapper.findAllIconData()
    }
    fun IconIdMax(): Long {
        if(iconmapper.IconMaxId()==null)return 0
        return iconmapper.IconMaxId()!!
    }
}
