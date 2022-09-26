package com.example.providertest

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var bookId: String? = null

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addData.setOnClickListener {
            // 添加数据
            //首先调用了Uri.parse()方法将一个内容URI解析成Uri对象，然后把要添加的数据都存放到
            //ContentValues对象中，接着调用ContentResolver的insert()方法执行添加操作就可以
            //了。注意，insert()方法会返回一个Uri对象，这个对象中包含了新增数据的id，我们通过
            //getPathSegments()方法将这个id取出，稍后会用到它。
            val uri = Uri.parse("content://com.example.databasetest.provider/book")
            val values = contentValuesOf(
                "name" to "A Clash of Kings",
                "author" to "George Martin", "pages" to 1040, "price" to 22.85
            )
            val newUri = contentResolver.insert(uri, values)
            bookId = newUri?.pathSegments?.get(1)
        }
        queryData.setOnClickListener {
            // 查询数据
            //调用了Uri.parse()方法将一个内容URI解析成Uri对象，然后调用
            //ContentResolver的query()方法查询数据，查询的结果当然还是存放在Cursor对象中。之
            //后对Cursor进行遍历，从中取出查询结果，并一一打印出来
            val uri = Uri.parse("content://com.example.databasetest.provider/book")
            contentResolver.query(uri, null, null, null, null)?.apply {
                while (moveToNext()) {
                    val name = getString(getColumnIndex("name"))
                    val author = getString(getColumnIndex("author"))
                    val pages = getInt(getColumnIndex("pages"))
                    val price = getDouble(getColumnIndex("price"))
                    Log.d("MainActivity", "book name is $name")
                    Log.d("MainActivity", "book author is $author")
                    Log.d("MainActivity", "book pages is $pages")
                    Log.d("MainActivity", "book price is $price")
                }
                close()
            }
        }
        updateData.setOnClickListener {
            // 更新数据
            //为了不想让Book表中的其他行受到影响，在调用Uri.parse()方法时，
            //给内容URI的尾部增加了一个id，而这个id正是添加数据时所返回的。这就表示我们只希望更新
            //刚刚添加的那条数据，Book表中的其他行都不会受影响
            bookId?.let {
                val uri = Uri.parse(
                    "content://com.example.databasetest.provider/book/$it"
                )
                val values = contentValuesOf(
                    "name" to "A Storm of Swords",
                    "pages" to 1216, "price" to 24.05
                )
                contentResolver.update(uri, values, null, null)
            }
        }
        deleteData.setOnClickListener {
            // 删除数据
            //在内容URI里指定了一个id，因此只会删掉拥有相应id的那行数据，Book表中的其他数据都不会受影响
            bookId?.let {
                val uri = Uri.parse(
                    "content://com.example.databasetest.provider/book/$it"
                )
                contentResolver.delete(uri, null, null)
            }
        }
    }
}