package com.bignerdranch.android.jetpack4fragmentlshardlivedatatest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider


class OneFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val parentView: View = inflater.inflate(R.layout.fragment_one, container, false)
        val seekBar = parentView.findViewById<SeekBar>(R.id.seekBar)

        // 注意：这里ViewModelProvider(requireActivity())这里的参数需要是Activity，而不能是Fragment，否则收不到监听
        val shareDataViewModel = ViewModelProvider(requireActivity())[ShareDataViewModel::class.java]
        val liveData = shareDataViewModel.getProgress() as MutableLiveData<Int>

        // 通过observe方法观察ViewModel中字段数据的变化，并在变化时，得到通知
        liveData.observe(viewLifecycleOwner) { progress -> seekBar.progress = progress!! }
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                liveData.value = progress // 用户操作SeekBar时，更新ViewModel中的数据
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        return parentView
    }
}