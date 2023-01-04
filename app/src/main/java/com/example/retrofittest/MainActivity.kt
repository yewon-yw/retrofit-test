package com.example.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofittest.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val xAccessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsInVzZXJFbWFpbCI6ImV1bjIwNTE2QG5hdmVyLmNvbSIsInVzZXJOaWNuYW1lIjoi67Cx7J2A6rK9IiwidXNlclByb2ZpbGVJbWFnZSI6Imh0dHA6Ly9rLmtha2FvY2RuLm5ldC9kbi9iaGdmZHIvYnRyUFFNeHVsVTEvbTg3NGRLVFFVbXBBYlF6ZWhtY0N2Sy9pbWdfNjQweDY0MC5qcGciLCJpYXQiOjE2Njc5OTY0NzQsImV4cCI6MTY5OTUzMjQ3NCwic3ViIjoidXNlckluZm8ifQ.leo8A2TGijtHdmDMkCl2__gkX7cD43lKOpREGUcYDDY"

        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("X-ACCESS-TOKEN", xAccessToken)
                    .build()
                it.proceed(request)
            }
            .build()
        val retrofit =
            Retrofit.Builder().baseUrl("http://43.201.108.60:80/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        val service = retrofit.create(DiaryApi::class.java)
//        val service = retrofit.create(UserApi::class.java)
//
//        service.getUser().enqueue(object: Callback<User>{
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                if(response.isSuccessful){
//                    val result: User = response.body()!!
//                    if(result.isSuccess){
//                        Log.d(TAG,"onResponse 성공 ")
//                        if (result != null) {
//                            Log.d(TAG,"${result}")
//                            binding.nameTv.text = result.result.userNickname
//                            binding.emailTv.text = result.result.userEmail
//                        }
//                    } else{
//                        Log.d(TAG,"${result.message}")
//                    }
//                } else {
//                    Log.d(TAG,"onResponse 실패")
//                }
//            }
//
//            override fun onFailure(call: Call<User>, t: Throwable) {
//                Log.d(TAG,"onFailure 예외: " + t.message)
//            }
//        })

        service.getDiaryList("2022-11-23").enqueue(object: Callback<DiaryList>{
            override fun onResponse(call: Call<DiaryList>, response: Response<DiaryList>) {
                if(response.isSuccessful){
                    val result: DiaryList = response.body()!!
                    Log.d(TAG,"onResponse 성공 ")
                    if (result.isSuccess) {
                        Log.d(TAG,"${result}")
                    } else {
                        Log.d(TAG,"${result.message}")
                    }
                } else {
                    Log.d(TAG,"onResponse 실패")
                }
            }

            override fun onFailure(call: Call<DiaryList?>, t: Throwable) {
                Log.d(TAG,"onFailure 예외: "+t.message)
            }
        })

    }
}