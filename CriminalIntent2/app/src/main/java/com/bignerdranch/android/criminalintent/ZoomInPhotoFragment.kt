package com.bignerdranch.android.criminalintent

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.DialogFragment

class ZoomInPhotoFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val path: String? = arguments?.getString("path") // 获取到之前传过来的路径
        val dialog = activity?.let { Dialog(it) } // 创建一个dialog
        dialog?.setContentView(R.layout.dialog_zoom_in_photo_fragment) // 设置dialog的布局,为之前创建的布局文件,里面仅有一个ImageView
        val imageView = dialog?.findViewById<View>(R.id.zoom_in_photo) as ImageView // 找到控件
        imageView.setImageBitmap(getScaledBitmap(path.toString(), requireActivity()))        // 使用 PictureUtils 类的工具来获得缩放的 Bitmap
        imageView.setOnClickListener { dialog.dismiss() } // 设置点击事件，当点击图片时候，dialog消失。
        return dialog
    }

    companion object {
        fun newInstance(path: String?): ZoomInPhotoFragment {
            val args = Bundle()
            args.putString("path", path)
            val pictureDialogFragment = ZoomInPhotoFragment()
            pictureDialogFragment.arguments = args
            return pictureDialogFragment
        }
    }
}