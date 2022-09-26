package com.bignerdranch.android.criminalintent

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment : DialogFragment() {
    private var mTimePicker: TimePicker? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date = arguments?.getSerializable(ARG_DATE) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]
        val hour = calendar[Calendar.HOUR_OF_DAY]
        val minute = calendar[Calendar.MINUTE]
        val v = LayoutInflater.from(activity).inflate(R.layout.dialog_time, null)
        mTimePicker = v.findViewById<View>(R.id.dialog_time_picker) as TimePicker
        mTimePicker!!.hour = hour
        mTimePicker!!.minute = minute
        return AlertDialog.Builder(activity)
            .setView(v)
            .setTitle(R.string.date_picker_title)
            .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { _, _ ->
                val mDate = GregorianCalendar(year, month, day, mTimePicker!!.hour, mTimePicker!!.minute).time
                sendResult(Activity.RESULT_OK, mDate)
            })
            .create()
    }

    private fun sendResult(resultCode: Int, date: Date) {
        if (targetFragment == null) {
            return
        }
        val intent = Intent()
        intent.putExtra(EXTRA_DATE, date)
        targetFragment?.onActivityResult(targetRequestCode, resultCode, intent)
    }

    companion object {
        //两个按钮共用一个EXTRA_DATE，所以不用新添加一个TimePicker专用的EXTRA_TIME
        const val EXTRA_DATE = "com.bignerdranch.android.criminalintent.date"
        private const val ARG_DATE = "date"
        fun newInstance(date: Date?): TimePickerFragment {
            val args = Bundle()
            args.putSerializable(ARG_DATE, date)
            val fragment = TimePickerFragment()
            fragment.arguments = args
            return fragment
        }
    }
}