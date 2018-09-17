package com.example.apiSample.mapper

import com.example.apiSample.model.MessageData
import com.example.apiSample.model.message
import org.apache.ibatis.annotations.*

@Mapper
interface MessageMapper {
    @Select(
            """
         SELECT * FROM message
        """
    )
    fun AllMessageData():List<message>
    @Insert(
            """
         INSERT INTO message (Id,SenderId,RoomId,Content,Type,CreatedAt) VALUES (#{Id},#{SenderId},#{RoomId},#{content},"string",CURRENT_TIMESTAMP)
        """
    )
    fun InsertMessageData(Id:Long,SenderId: Long,RoomId: Long,content:String)
    @Delete(
            """
         DELETE FROM message
        """
    )
    fun DeleteMessageData()
    @Select(
            """
         SELECT MAX(Id) FROM message
        """
    )
    fun MessageMaxId():Long?

    @Update(
            """
         UPDATE message SET `Content`=#{Content} WHERE Id=#{messageId}
        """
    )
    fun UpdateMessage(messageId:Long,Content:String):Long

    @Select(
            """
         SELECT * FROM message WHERE Id=#{messageId}
        """
    )
    fun findById(messageId: Long):message?
    /*
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
    @Select(
            """
         SELECT * FROM MessageDB WHERE MessageId>#{MessageId} AND GroupId=#{GroupId}
        """
    )
    fun GetRoomTimeMessage(MessageId: Long,GroupId:Long):List<MessageData>
    */

}