package com.example.apiSample.mapper

import com.example.apiSample.model.icon
import com.example.apiSample.model.user
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface IconMapper {
    @Select(
            """
        SELECT * FROM icon
        """
    )
    fun findAllIconData(): List<icon>
    @Insert(
            """
         INSERT INTO icon (Id, URL, UserId) VALUES (#{Id},#{URL},#{UserId})
        """
    )
    fun InsertIconData(Id:Long,URL:String,UserId:Long)
    @Delete(
            """
         DELETE FROM icon
        """
    )
    fun DeleteIconData()

}