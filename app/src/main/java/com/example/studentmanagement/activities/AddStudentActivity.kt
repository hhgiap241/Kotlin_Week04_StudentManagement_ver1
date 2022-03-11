package com.example.studentmanagement.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.example.studentmanagement.R
import com.example.studentmanagement.models.Student
import java.io.OutputStreamWriter

class AddStudentActivity : AppCompatActivity() {


    var studentClassTV: TextView?= null
    var studentNameET: EditText?= null
    var studentBirthday: EditText?=null
    var studentGenderRadioBtn: RadioButton?= null
    var studentGenderRadioGroup: RadioGroup?= null
    var saveStudentBtn: Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        studentNameET = findViewById(R.id.inputNameET)
        studentBirthday = findViewById(R.id.inputDateET)
        studentGenderRadioGroup = findViewById(R.id.radioGroup)
        studentClassTV = findViewById(R.id.inputClassTV)
        saveStudentBtn = findViewById(R.id.saveAddStudentBtn_1)

        studentClassTV!!.setOnClickListener {
            val intent = Intent(this, InputClassActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
        saveStudentBtn!!.setOnClickListener {
            // check empty name
            if (TextUtils.isEmpty(studentNameET!!.text.toString())) { // check if input is empty
                studentNameET!!.setError("This field can't be empty")
                return@setOnClickListener
            }
            // check empty birthday
            if (TextUtils.isEmpty(studentBirthday!!.text.toString())) { // check if input is empty
                studentBirthday!!.setError("This field can't be empty")
                return@setOnClickListener
            }
            // check empty class
            if (TextUtils.isEmpty(studentClassTV!!.text.toString())) { // check if input is empty
                studentClassTV!!.setError("This field can't be empty")
                return@setOnClickListener
            }
            // get student gender
            var radioID: Int = studentGenderRadioGroup!!.checkedRadioButtonId
            studentGenderRadioBtn = findViewById(radioID)

            var student =  Student(studentNameET!!.text.toString(),
                                    studentBirthday!!.text.toString(),
                                    studentClassTV!!.text.toString(),
                                    studentGenderRadioBtn!!.text.toString())
            this.saveToFile(student)
        }
    }
    val REQUEST_CODE = 1111
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === REQUEST_CODE){
            if (resultCode === Activity.RESULT_OK){
                val reply = data!!.getStringExtra("classroom")
                studentClassTV!!.setError(null)
                studentClassTV!!.setText(reply)
            }
        }
    }
    fun saveToFile(student: Student) {
        try {
            val fileName = "studentList.txt"
            // File will be in "/data/data/$packageName/files/"
            val out = OutputStreamWriter(openFileOutput(fileName, Context.MODE_APPEND))
            out.write(student.toString())
            out.write("\n")
            out.close()
            this.clearInput()
            Toast.makeText(this, "Saved successfully!", Toast.LENGTH_SHORT).show()
        } catch (t: Throwable) {
            Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
        }
    }
    fun clearInput(){
        studentNameET!!.setText("")
        studentBirthday!!.setText("")
        studentClassTV!!.setText("")
        studentGenderRadioGroup!!.check(R.id.maleRB)
    }
}