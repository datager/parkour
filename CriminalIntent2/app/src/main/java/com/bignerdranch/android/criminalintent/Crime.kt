package com.bignerdranch.android.criminalintent

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Crime(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var date: Date = Date(),
    var isSolved: Boolean = false,
    var requiresPolice: Boolean = false, // 是否需要警方介入
    var suspect: String = "", // 嫌疑人的名字
    var phoneNumber: String = "13388889999" // 嫌疑人的电话号
) {
    val photoFileName get() = "IMG_$id.jpg"
}