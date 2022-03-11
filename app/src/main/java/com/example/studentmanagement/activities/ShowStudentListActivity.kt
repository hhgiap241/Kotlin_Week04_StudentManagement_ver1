package com.example.studentmanagement.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.studentmanagement.R
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.studentmanagement.adapters.StudentListAdapter
import com.example.studentmanagement.models.Student
import com.example.studentmanagement.models.StudentList
import java.io.*

class ShowStudentListActivity : AppCompatActivity() {
    var adapter: StudentListAdapter?= null
    var names = ArrayList<String>()
    var classes = ArrayList<String>()
    var otherInfo= ArrayList<String>()
    var icons= ArrayList<Int>()
    var studentList = StudentList()
    val fileName = "studentList.txt"
    fun createData(){
        try {
            names.clear()
            classes.clear()
            otherInfo.clear()
            icons.clear()
            studentList.getStudentList().clear()

            val inputStream: InputStream? = openFileInput(fileName)
            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream)
                val reader = BufferedReader(inputStreamReader)
                var line: String? = reader.readLine()
                while (line != null) {
                    var student = Student.parseStudent(line)
                    studentList.addStudent(student)

                    names.add(student.getName())
                    classes.add(student.getClassroom())
                    otherInfo.add(student.getBirthday() + " - " + student.getGender())
                    icons.add(R.drawable.student_avatar)
                    line = reader.readLine()
                }
                inputStream.close()
            }
        } catch (e: FileNotFoundException) {
        } catch (t: Throwable) {
            Toast.makeText(this, "Exception: $t", Toast.LENGTH_SHORT).show()
        }
    }
    var studentListView: ListView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_student_list)

        studentListView = findViewById(R.id.studentsListView)
        this.createData()
        adapter = StudentListAdapter(this, names, classes, otherInfo, icons)
        studentListView!!.adapter = adapter
        studentListView!!.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, EditStudentInformationActivity::class.java)
            var string: List<String> = otherInfo[i].split("-")
            var oldStudent = Student(names[i], string[0].trim(), classes[i], string[1].trim())

//            intent.putExtra("name", names[i])
//            intent.putExtra("class", classes[i])
//            intent.putExtra("birthday", string[0].trim())
//            intent.putExtra("gender", string[1].trim())
            intent.putExtra("oldStudent", oldStudent)
            intent.putExtra("studentList", studentList)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }
    val REQUEST_CODE = 1112
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === REQUEST_CODE){
            if (resultCode === Activity.RESULT_OK){
                val reply = data!!.getSerializableExtra("newStudentList") as StudentList
                this.updateFile(reply)
                adapter!!.clear()
                this.createData()
                adapter = StudentListAdapter(this, names, classes, otherInfo, icons)
                studentListView!!.adapter = adapter
            }
        }
    }
    fun updateFile(studentList: StudentList){
        try {
            // File will be in "/data/data/$packageName/files/"
            val out = OutputStreamWriter(openFileOutput(fileName, 0))
            for (i in studentList.getStudentList().indices){
                out.write(studentList.getStudentList()[i].toString())
                out.write("\n")
            }
            out.close()
        } catch (t: Throwable) {
            Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
        }
    }
}