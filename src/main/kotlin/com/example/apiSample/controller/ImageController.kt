package com.example.apiSample.controller


import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*




@RestController
class ImageController(){
    @PostMapping(
            value = ["/upload"],
    produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun upload():String{
      return "redirect"
    }


}