package com.xuweic.annoyingex

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class HttpJsonPharserManager(context: Context) {

    private var queue: RequestQueue = Volley.newRequestQueue(context)
    lateinit var allPhrases: AllPhrases

    fun getAllPhrases(onAllSongReady: (AllPhrases) -> Unit){
        val phraseURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"

        val request = StringRequest(Request.Method.GET, phraseURL,
            { response ->
                // success
                val gson = Gson()
                allPhrases = gson.fromJson(response, AllPhrases::class.java)

                onAllSongReady(allPhrases)
            },
            {
                // error
                onAllSongReady(allPhrases)
            })

        queue.add(request)
    }

}