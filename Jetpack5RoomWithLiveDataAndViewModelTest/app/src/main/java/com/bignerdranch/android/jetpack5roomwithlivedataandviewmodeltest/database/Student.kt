package com.bignerdranch.android.jetpack5roomwithlivedataandviewmodeltest.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "student")
class Student {
    @NotNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    var id = 0

    @NotNull
    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    var name: String

    @NotNull
    @ColumnInfo(name = "age", typeAffinity = ColumnInfo.TEXT)
    var age: String

    /**
     * Room会使用这个构造器来存储数据，也就是当你从表中得到Student对象时候，Room会使用这个构造器
     */
    constructor(id: Int, name: String, age: String) {
        this.id = id
        this.name = name
        this.age = age
    }

    /**
     * 由于Room只能识别和使用一个构造器，如果希望定义多个构造器，你可以使用Ignore标签，让Room忽略这个构造器
     * 同样，@Ignore标签还可用于字段，使用@Ignore标签标记过的字段，Room不会持久化该字段的数据
     */
    @Ignore
    constructor(name: String, age: String) {
        this.name = name
        this.age = age
    }
}