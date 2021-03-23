package com.example.xieceng

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Callback
import java.util.concurrent.Executors

class MainViewModel : ViewModel() {
    private val _tv= MutableLiveData<String>();
    val tv:LiveData<String>
        get() =_tv;
    fun MainViewModelClick(){
        getNetWorkStr()
    }

    private fun getNetWorkStr() {
        getNetRequest()
    }
    fun getNetRequest(){
//        Executors.callable(Runnable {

//            _tv.postValue(_tv.value)
//        }).call()
        val scope= CoroutineScope(Dispatchers.Default)
        val launch = scope.launch {
            var response =getNetWorkSingleton().getStr().execute()
            val str:String= response.body().toString()!!
            val gson=Gson()
//            val s: Data? =gson.fromJson(str,Data::class.java);
//            _tv.value=str
            _tv.postValue(_tv.value)
        }
    }
    fun removeObserver(){
//        _tv?.removeObserver(this)
    }
}