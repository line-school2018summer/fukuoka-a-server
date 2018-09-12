package com.example.apiSample.firebase

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.FirebaseOptions
import com.google.auth.oauth2.GoogleCredentials
import java.util.logging.Logger
import java.io.FileInputStream



@Component

class FirebaseGateWay:AuthGateway{
    //val logger =LoggerFactory.getLogger(FirebaseGateway::class.java)
    companion object {
        init{
            /*
            val firebase_account_path = "test-f98e8-firebase-adminsdk-lozps-f456152fe4.json"
            val databaseUrl = "https://test-f98e8.firebaseio.com"
            val firebase_account = FirebaseGateWay::class.java.getResourceAsStream(firebase_account_path)
*/
            val serviceAccount = FileInputStream("fukuoka-a-client-firebase-adminsdk-8cnob-5b7e26d23e.json")

            val options = FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://fukuoka-a-client.firebaseio.com")
                    .build()

            FirebaseApp.initializeApp(options)


        }

    }
    override fun verifyIdToken(id_token: String?): String?{
        val decodedToken = FirebaseAuth.getInstance().verifyIdTokenAsync(id_token).get()
        val uid = decodedToken.getUid();
        return uid
        /*
        try {
            val decodedToken = FirebaseAuth.getInstance().verifyIdTokenAsync(id_token).get()
            val uid = decodedToken.getUid();
            return uid
        }
        catch(e: Exception){
            //logger.info("error",e)
            return null

        }
        */
    }
}
