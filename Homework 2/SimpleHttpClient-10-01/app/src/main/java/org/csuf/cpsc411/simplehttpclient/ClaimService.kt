package org.csuf.cpsc411.simplehttpclient

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import java.lang.reflect.Type

class ClaimService (val ctx : CustomActivity){

    lateinit var claimList : MutableList<Claim>
    var currentIndx : Int = 0

    inner class GetAllServiceRespHandler : AsyncHttpResponseHandler() {
        override fun onSuccess(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?
        ) {
            // JSON string
            if (responseBody != null) {
                Log.d("Claim Service", "The response JSON string is ${String(responseBody)}")
                val gson = Gson()
                val personListType: Type = object : TypeToken<List<Claim>>() {}.type
                claimList = gson.fromJson(String(responseBody), personListType)
                //
                ctx.refreshScreen(claimList[currentIndx])
                //
                //act.runOnUiThread {
                //    cbLambdaFunction()
                //}
                Log.d("Claim Service", "The Person List: ${claimList}")
            }
        }

        override fun onFailure(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?,
            error: Throwable?
        ) {
            // Comment
        }
    }

    inner class addServiceRespHandler : AsyncHttpResponseHandler() {
        override fun onSuccess(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?
        ) {
            if (responseBody != null) {
                val respStr = String(responseBody)
                Log.d("Claim Service", "The add Service response : ${respStr}")
            }
        }

        override fun onFailure(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?,
            error: Throwable?
        ) {
            // Random Comment
        }
    }

    fun addClaim(cObj : Claim) {
        val client = AsyncHttpClient()
        val requestUrl = "http://192.168.0.112:8010/ClaimService/add"
        // 1. Convert the pObj into JSON string
        val pJsonString= Gson().toJson(cObj)
        // 2. Send the post request
        val entity = StringEntity(pJsonString)

        // cxt is an Android Application Context object
        client.post(ctx, requestUrl, entity,"application/json", addServiceRespHandler())
    }

    fun getAll()  {
        //var cList : List<Person> = mutableListOf()
        // Call Http
        //clientObj = clObj
        val client = AsyncHttpClient()
        val requestUrl = "http://192.168.0.112:8010/ClaimService/getAll"
        //
        Log.d("Claim Service", "About Sending the HTTP Request. ")
        //
        client.get(requestUrl, GetAllServiceRespHandler())
    }
}