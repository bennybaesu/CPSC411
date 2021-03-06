package org.csuf.cspc411

import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.utils.io.*
import java.util.*
import org.csuf.cspc411.Dao.claim.Claim
import org.csuf.cspc411.Dao.claim.ClaimDao

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    // extension
    // @annotation
    // routing constructor takes only one parameter which is a lambda function
    // DSL - Domain Specific Language
    routing{


        get("/PersonService/getAll"){
            val cList = ClaimDao.getAll()
            println("The number of claims : $cList.size}")
            // JSON Serialization/Deserialization
            val respJsonStr = Gson().toJson(cList)
            call.respondText(respJsonStr, status= HttpStatusCode.OK, contentType= ContentType.Application.Json)
        }

        post("/PersonService/add"){
            val contType = call.request.contentType()
            val data = call.request.receiveChannel()
            val dataLength = data.availableForRead
            var output = ByteArray(dataLength)
            data.readAvailable(output)
            val str = String(output)    // for further processing

            // JSON serialization/deserialization
            val gsonString = Gson().fromJson(str, Claim::class.java)
            val cObj = Claim(UUID.randomUUID(), gsonString.title, gsonString.date, isSolved = false)
            val dao = ClaimDao().addClaim(cObj)
            // GSON (Google library)

            println("HTTP message is using POST method with /post ${contType} ${str}")
            call.respondText("The POST request was successfully processed. ",
                    status= HttpStatusCode.OK, contentType = ContentType.Text.Plain)
        }
    }

}

