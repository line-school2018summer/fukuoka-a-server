package com.example.apiSample.controller

import com.example.apiSample.firebase.AuthGateway
import com.example.apiSample.service.FileStorage
import org.springframework.http.MediaType
import org.springframework.web.multipart.MultipartFile
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.io.IOException

@RestController
class ImageUploadController(private val auth: AuthGateway){
    @Autowired
    lateinit var fileStorage: FileStorage
    @PostMapping(
            value = ["/upload/{UId}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )

    fun uploadFile(@RequestHeader("Token")token: String,
                   @RequestBody file : MultipartFile,
                   @PathVariable("UId" ) UId: String): Boolean{
        val uid = auth.verifyIdToken(token) ?: throw UnauthorizedException("Your token is invalid.")
        val id = UId//userService.findByUid(uid).id.toString()

        if(!file.isEmpty()){
            try{
                val type = fileStorage.checkFileType(file)
                fileStorage.store(file, "public/img/icon", id + type)
                return true
            }
            catch(t: IOException){
                throw RuntimeException(t)
            }
        }
        else{
            return false
        }

    }

}