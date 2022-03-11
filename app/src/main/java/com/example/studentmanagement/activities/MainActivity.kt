package com.example.studentmanagement.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.studentmanagement.R
import com.example.studentmanagement.adapters.MainAdapter

class MainActivity : AppCompatActivity() {

    var titles: List<String> = emptyList()
    var icons: List<Int> = emptyList()

    fun createData(){
        titles = listOf("Input student’s information", "Display list of students")
        icons = listOf(R.drawable.add_student, R.drawable.show_student_list)
    }

    var mainMenuListView: ListView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainMenuListView = findViewById(R.id.mainMenuListView)

        createData()
        val adapter = MainAdapter(this, titles, icons)
        mainMenuListView!!.adapter = adapter
        mainMenuListView!!.setOnItemClickListener { adapterView, view, i, l ->
            if (i === 0){
                val intent = Intent(this, AddStudentActivity::class.java)
                startActivity(intent)
            }
            if (i === 1){
                val intent = Intent(this, ShowStudentListActivity::class.java)
                startActivity(intent)
            }
        }
    }
}