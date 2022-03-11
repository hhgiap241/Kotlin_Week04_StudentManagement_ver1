package com.example.studentmanagement.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentmanagement.R
import com.example.studentmanagement.adapters.MainAdapter


class InputClassActivity : AppCompatActivity() {

    var classroom: List<String> = emptyList()
    var icons: List<Int> = emptyList()

    fun createData() {
        classroom = listOf("19KTPM1", "19KTPM2", "19KTPM3")
        icons = listOf(R.drawable.classroom, R.drawable.classroom, R.drawable.classroom)
    }

    var inputClassListView: ListView? = null
    var saveInputClassroomBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_class)

        saveInputClassroomBtn = findViewById(R.id.saveClassroomBtn)
        inputClassListView = findViewById(R.id.inputClassListView)
        createData()

        val adapter = MainAdapter(this, classroom, icons)
        var chosenClassroom: String? = null
        inputClassListView!!.adapter = adapter
        inputClassListView!!.setOnItemClickListener { adapterView, view, i, l ->
            chosenClassroom = classroom[i]
        }
        saveInputClassroomBtn!!.setOnClickListener {
            if (chosenClassroom === null){
                Toast.makeText(this, "No class has been selected yet", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val replyIntent = Intent()
            replyIntent.putExtra("classroom", chosenClassroom)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
    }
}