package com.example.studentmanagement.models

import android.app.PendingIntent.getActivity
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*

class Student : Serializable {
    private var name: String? = null
    private var birthday: String? = null
    private var classroom: String? = null
    private var gender: String? = null

    constructor() {
        this.name = ""
        this.birthday = ""
        this.classroom = ""
        this.gender = ""
    }

    constructor(name: String, birthday: String, classroom: String, gender: String) {
        this.name = name
        this.birthday = birthday
        this.classroom = classroom
        this.gender = gender
    }

    companion object {
        fun parseStudent(string: String): Student {
            var student = Student()
            var contents: List<String>? = null
            contents = string.split("-")
            student!!.setName(contents[0])
            student!!.setBirthday(contents[1])
            student!!.setClassroom(contents[2])
            student!!.setGender(contents[3])
            return student
        }
    }

    override fun toString(): String {
        return this.name + "-" + this.birthday + "-" + this.classroom + "-" + this.gender
    }

    fun setName(name: String) {
        this.name = name
    }

    fun setBirthday(birthday: String) {
        this.birthday = birthday
    }

    fun setClassroom(classroom: String) {
        this.classroom = classroom
    }

    fun setGender(gender: String) {
        this.gender = gender
    }

    fun getName(): String {
        return this.name.toString()
    }

    fun getBirthday(): String {
        return this.birthday.toString()
    }

    fun getClassroom(): String {
        return this.classroom.toString()
    }

    fun getGender(): String {
        return this.gender.toString()
    }

    fun isEqual(student: Student): Boolean {
        if (this.name != student.getName() || this.birthday != student.getBirthday() ||
            this.classroom != student.classroom || this.gender != student.getGender()
        )
            return false
        return true
    }
}