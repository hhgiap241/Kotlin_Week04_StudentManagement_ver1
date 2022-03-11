package com.example.studentmanagement.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.example.studentmanagement.R
import com.example.studentmanagement.models.Student
import com.example.studentmanagement.models.StudentList
import java.io.File
import java.io.OutputStreamWriter

class EditStudentInformationActivity : AppCompatActivity() {
    val fileName = "studentList.txt"
    var editStudentNameET: EditText?= null
    var editStudentBrithdayET: EditText?= null
    var editStudentClassTV: TextView?=null
    var radioGroup: RadioGroup?= null
    var radioButton:RadioButton?= null
    var saveBtn: Button?= null
    var deleteBtn: Button?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student_information)

        editStudentNameET = findViewById(R.id.editNameET)
        editStudentBrithdayET = findViewById(R.id.editDateET)
        editStudentClassTV = findViewById(R.id.editClassTV)
        radioGroup = findViewById(R.id.radioGroup_2)
        saveBtn = findViewById(R.id.saveAddStudentBtn_2)
        deleteBtn = findViewById(R.id.deleteStudentBtn)



        val intent = intent

        var oldStudent = intent.getSerializableExtra("oldStudent") as Student
        var studentList = intent.getSerializableExtra("studentList") as StudentList
        val studentName = oldStudent.getName()
        val studentBirthday = oldStudent.getBirthday()
        val studentClass = oldStudent.getClassroom()
        val studentGender = oldStudent.getGender()
        this.setStudentInformation(studentName,studentBirthday, studentClass, studentGender)

        editStudentClassTV!!.setOnClickListener {
            val intent = Intent(this, InputClassActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
        saveBtn!!.setOnClickListener {
            // check empty name
            if (TextUtils.isEmpty(editStudentNameET!!.text.toString())) { // check if input is empty
                editStudentNameET!!.setError("This field can't be empty")
                return@setOnClickListener
            }
            // check empty birthday
            if (TextUtils.isEmpty(editStudentBrithdayET!!.text.toString())) { // check if input is empty
                editStudentBrithdayET!!.setError("This field can't be empty")
                return@setOnClickListener
            }
            // check empty class
            if (TextUtils.isEmpty(editStudentClassTV!!.text.toString())) { // check if input is empty
                editStudentClassTV!!.setError("This field can't be empty")
                return@setOnClickListener
            }
            // get student gender
            var radioID: Int = radioGroup!!.checkedRadioButtonId
            radioButton = findViewById(radioID)

            var newStudent =  Student(editStudentNameET!!.text.toString(),
                                    editStudentBrithdayET!!.text.toString(),
                                    editStudentClassTV!!.text.toString(),
                                    radioButton!!.text.toString())
            studentList.editStudentInfo(oldStudent, newStudent)
            Toast.makeText(this, "Update successfully!", Toast.LENGTH_SHORT).show()

            val replyIntent = Intent()
            replyIntent.putExtra("newStudentList", studentList)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
        deleteBtn!!.setOnClickListener {
            studentList.deleteStudent(oldStudent)
            Toast.makeText(this, "Delete successfully!", Toast.LENGTH_SHORT).show()
            val replyIntent = Intent()
            replyIntent.putExtra("newStudentList", studentList)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
    }
    val REQUEST_CODE = 1111
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === REQUEST_CODE){
            if (resultCode === Activity.RESULT_OK){
                val reply = data!!.getStringExtra("classroom")
                editStudentClassTV!!.setError(null)
                editStudentClassTV!!.setText(reply)
            }
        }
    }
    fun setStudentInformation(name: String, birthday: String, classroom: String, gender:String){
        editStudentNameET!!.setText(name)
        editStudentBrithdayET!!.setText(birthday)
        editStudentClassTV!!.setText(classroom)
        if (gender == "Male")
            radioGroup!!.check(R.id.maleRB_2)
        else if (gender == "Female")
            radioGroup!!.check(R.id.femaleRB_2)
        else
            radioGroup!!.check(R.id.otherGenderRB_2)
    }
}