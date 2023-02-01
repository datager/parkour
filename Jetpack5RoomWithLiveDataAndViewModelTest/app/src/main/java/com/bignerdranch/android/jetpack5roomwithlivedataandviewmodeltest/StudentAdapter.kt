package com.bignerdranch.android.jetpack5roomwithlivedataandviewmodeltest


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.bignerdranch.android.jetpack5roomwithlivedataandviewmodeltest.database.Student


class StudentAdapter(context: Context, private val data: List<Student?>?) : BaseAdapter() {
    private val layoutInflater: LayoutInflater

    internal inner class ViewHolder {
        var tvId: TextView? = null
        var tvName: TextView? = null
        var tvAge: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView: View? = convertView
        val viewHolder: ViewHolder
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_student, null)
            viewHolder = ViewHolder()
            viewHolder.tvId = convertView.findViewById(R.id.tvId)
            viewHolder.tvName = convertView.findViewById(R.id.tvName)
            viewHolder.tvAge = convertView.findViewById(R.id.tvAge)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }
        viewHolder.tvId!!.text = (data!![position]?.id).toString()
        viewHolder.tvName!!.text = data[position]!!.name
        viewHolder.tvAge!!.text = data[position]!!.age
        return convertView
    }

    override fun getCount(): Int {
        return data?.size ?: 0
    }

    override fun getItem(position: Int): Student? {
        return data?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
}