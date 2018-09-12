package com.example.apiSample.firebase

import org.springframework.stereotype.Controller

//@Controller

class  NoTokenGateway : AuthGateway{
    override fun verifyIdToken(id_token: String?): String?{
        return "fakeid1"
    }
}

