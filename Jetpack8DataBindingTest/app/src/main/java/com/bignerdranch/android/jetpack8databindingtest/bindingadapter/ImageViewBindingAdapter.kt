package com.bignerdranch.android.jetpack8databindingtest.bindingadapter

import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bignerdranch.android.jetpack8databindingtest.R
import com.squareup.picasso.Picasso


object ImageViewBindingAdapter {
    private const val TAG = "ImageViewBindingAdapter"

    // 加载网络图片
    @BindingAdapter("image")
    fun setImage(imageView: ImageView, imageUrl: String?) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageView)
        } else {
            imageView.setBackgroundColor(Color.DKGRAY)
        }
    }

    // 加载资源文件中的图片
    @BindingAdapter("image")
    fun setImage(imageView: ImageView, imageResource: Int) {
        imageView.setImageResource(imageResource)
    }

    // 加载网络图片，多个参数的情况
    @BindingAdapter(value = ["image", "defaultImageResource"], requireAll = false)
    fun setImage(imageView: ImageView, imageUrl: String?, imageResource: Int) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageView)
        } else {
            imageView.setImageResource(imageResource)
        }
    }

    // 演示旧参数，新参数
    @BindingAdapter("padding")
    fun setPadding(view: View, oldPadding: Int, newPadding: Int) {
        Log.e(TAG, "oldPadding:$oldPadding newPadding:$newPadding")
        if (oldPadding != newPadding) {
            view.setPadding(newPadding, newPadding, newPadding, newPadding)
        }
    }
}