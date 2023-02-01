package com.bignerdranch.android.jetpack5roomtest

import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.jetpack5roomtest.database.MyDatabase
import com.bignerdranch.android.jetpack5roomtest.database.Student


class MainActivity : AppCompatActivity() {
    private var myDatabase: MyDatabase? = null
    private var studentList: MutableList<Student?>? = ArrayList()
    private var studentAdapter: StudentAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.btnInsertStudent).setOnClickListener { openAddStudentDialog() }
        val lvStudent: ListView = findViewById(R.id.lvStudent)
        studentAdapter = StudentAdapter(this@MainActivity, studentList)
        lvStudent.adapter = studentAdapter
        lvStudent.onItemLongClickListener = OnItemLongClickListener { _, _, position, _ ->
            updateOrDeleteDialog((studentList as ArrayList<Student?>)[position])
            false
        }
        myDatabase = MyDatabase.getInstance(this)
        QueryStudentTask().execute()
    }

    private fun updateOrDeleteDialog(student: Student?) {
        val options = arrayOf("更新", "删除")
        AlertDialog.Builder(this@MainActivity)
            .setTitle("")
            .setItems(options, DialogInterface.OnClickListener { _, which ->
                if (which == 0) {
                    openUpdateStudentDialog(student)
                } else if (which == 1) {
                    if (student != null) {
                        DeleteStudentTask(student).execute()
                    }
                }
            }).show()
    }

    private fun openAddStudentDialog() {
        val customView: View = this.layoutInflater.inflate(R.layout.dialog_layout_student, null)
        val etName: EditText = customView.findViewById(R.id.etName)
        val etAge: EditText = customView.findViewById(R.id.etAge)
        val dialog: AlertDialog = AlertDialog.Builder(this@MainActivity).create()
        dialog.setTitle("Add Student")
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", DialogInterface.OnClickListener { _, _ ->
            if (TextUtils.isEmpty(etName.text.toString()) || TextUtils.isEmpty(etAge.text.toString())) {
                Toast.makeText(this@MainActivity, "输入不能为空", Toast.LENGTH_SHORT).show()
            } else {
                InsertStudentTask(etName.text.toString(), etAge.text.toString()).execute()
            }
        })
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", DialogInterface.OnClickListener { _, _ -> dialog.dismiss() })
        dialog.setView(customView)
        dialog.show()
    }

    private fun openUpdateStudentDialog(student: Student?) {
        if (student == null) {
            return
        }
        val customView: View = this.layoutInflater.inflate(R.layout.dialog_layout_student, null)
        val etName: EditText = customView.findViewById(R.id.etName)
        val etAge: EditText = customView.findViewById(R.id.etAge)
        etName.setText(student.name)
        etAge.setText(student.age)
        val dialog: AlertDialog = AlertDialog.Builder(this@MainActivity).create()
        dialog.setTitle("Update Student")
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", DialogInterface.OnClickListener { _, _ ->
            if (TextUtils.isEmpty(etName.text.toString()) || TextUtils.isEmpty(etAge.text.toString())) {
                Toast.makeText(this@MainActivity, "输入不能为空", Toast.LENGTH_SHORT).show()
            } else {
                UpdateStudentTask(student.id, etName.text.toString(), etAge.text.toString()).execute()
            }
        })
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() })
        dialog.setView(customView)
        dialog.show()
    }

    private inner class InsertStudentTask(var name: String, var age: String) : AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg params: Void?): Void? {
            myDatabase!!.studentDao()!!.insertStudent(Student(name, age))
            studentList!!.clear()
            studentList!!.addAll(myDatabase!!.studentDao()!!.studentList!!)
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            studentAdapter?.notifyDataSetChanged()
        }
    }

    private inner class UpdateStudentTask(var id: Int, var name: String, var age: String) : AsyncTask<Void?, Void?, Void?>() {
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            studentAdapter?.notifyDataSetChanged()
        }

        override fun doInBackground(vararg params: Void?): Void? {
            myDatabase!!.studentDao()!!.updateStudent(Student(id, name, age))
            studentList!!.clear()
            studentList!!.addAll(myDatabase!!.studentDao()!!.studentList!!)
            return null
        }
    }

    private inner class DeleteStudentTask(var student: Student) : AsyncTask<Void?, Void?, Void?>() {
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            studentAdapter?.notifyDataSetChanged()
        }

        override fun doInBackground(vararg params: Void?): Void? {
            myDatabase!!.studentDao()!!.deleteStudent(student)
            studentList!!.clear()
            studentList!!.addAll(myDatabase!!.studentDao()!!.studentList!!)
            return null
        }
    }

    private inner class QueryStudentTask : AsyncTask<Void?, Void?, Void?>() {
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            studentAdapter?.notifyDataSetChanged()
        }

        override fun doInBackground(vararg params: Void?): Void? {
            studentList!!.clear()
            studentList!!.addAll(myDatabase!!.studentDao()!!.studentList!!)
            return null
        }
    }
}