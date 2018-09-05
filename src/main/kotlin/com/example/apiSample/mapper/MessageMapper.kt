package com.example.apiSample.mapper

import com.example.apiSample.model.MessageData
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface MessageMapper {
    @Select(
            """
        SELECT SenderId, GroupId,Message,MessageType,MessageId,SendTime FROM MessageDB WHERE MessageId=#{MessageId}
        """
    )
    fun findMessageDataByMessageId(MessageId: Long): MessageData
    @Select(
            """
        SELECT MessageId FROM MessageDB WHERE GroupId=#{GroupId}
        """
    )
    fun findMessageIdByRoomId(GroupId: Long): List<Long>
    @Select(
            """
        SELECT Message FROM MessageDB WHERE MessageId=#{MessageId}
        """
    )
    fun findMessageByMessageId(MessageId: Long): String
    @Select(
            """
        SELECT SenderId FROM MessageDB WHERE MessageId=#{MessageId}
        """
    )
    fun findSenderIdByMessageId(MessageId: Long): Long

    @Select(
            """
        SELECT MAX(MessageId) FROM MessageDB
        """
    )
    fun findLastMessageId(): Long?
    @Insert(
            """
         INSERT INTO MessageDB (SenderId,GroupId,Message,MessageType,MessageId,SendTime) VALUES (#{senderId},#{groupId},#{message},"string",#{messageId},CURRENT_TIMESTAMP)
        """
    )
    fun InsertMessageData(senderId: Long,groupId: Long,message:String,messageId:Long)
    @Delete(
            """
         DELETE FROM MessageDB
        """
    )
    fun DeleteMessageData()
    @Select(
            """
         SELECT * FROM MessageDB
        """
    )
    fun AllMessageData():List<MessageData>
    

}