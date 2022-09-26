package com.example.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.sunnyweather.logic.Repository
import com.example.sunnyweather.logic.model.Place

class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    // 对界面上显示的城市数据进行缓存, 保证手机屏幕旋转式数据不丢失
    val placeList = ArrayList<Place>()
    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        // 发起网络请求, 同时将仓库层返回的LiveData对象转换成一个可供Activity观察的LiveData对象
        Repository.searchPlaces(query)
    }

    // 将传入的搜索参数赋值给了一个searchLiveData对象，并使用Transformations的switchMap()方法来观察这个对象
    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
}