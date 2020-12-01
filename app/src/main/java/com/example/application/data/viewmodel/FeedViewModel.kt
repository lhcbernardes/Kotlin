package com.example.application.data.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.application.data.UserRepository
import com.example.application.data.model.Feed
import com.example.application.data.retrofit.RetrofitInstance
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class FeedViewModel(application: Application) : AndroidViewModel(application) {
    var multableListFeed = MutableLiveData<ArrayList<Feed>>();

    var current_page = MutableLiveData<String>()
    var per_age = MutableLiveData<String>()
    var published_at = MutableLiveData<String>()

    var userRepository: UserRepository? = null

    init{
        this.userRepository = UserRepository()
        current_page.value = "1"
        per_age.value = "20"
        published_at.value = ""
    }

    fun feed(token: String) {
        val call = RetrofitInstance().newsData().news(current_page.value.toString(), per_age.value.toString(),  published_at.value.toString(), "Bearer ${token}");


        call.enqueue(object: Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>?) {
                if(response!!.isSuccessful){
                    response?.body().let {
                        var objectToken: JSONObject = JSONObject(it?.string())
                        var feeds: JSONArray = objectToken.getJSONArray("data")
                        var listFeed = ArrayList<Feed>()
                        for (i in 0 until feeds.length()){
                            var feedObject = feeds.getJSONObject( i )
                            var feedItem: Feed = Feed(
                                feedObject.getString("title"),
                                feedObject.getString("description"),
                                feedObject.getString("content"),
                                feedObject.getString("author"),
                                feedObject.getString("published_at"),
                                feedObject.getString("highlight"),
                                feedObject.getString("url"),
                                feedObject.getString("image_url"))
                            //println(feedItem)
                            listFeed.add(feedItem)
                        }
                        multableListFeed.value = listFeed
                        //println(it.toString())
                    }
                }
                else{
                    response.errorBody().let {
                        println(it.toString())
                    }
                }


            }

            override fun onFailure(call: Call<ResponseBody?>?, t: Throwable?) {
                // Implement onFailure here
               // user.value = null
                Log.e("onFailure error", t?.message)
            }
        })
    }
}