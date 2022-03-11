package com.example.studentmanagement.models

import java.io.Serializable

class StudentList: Serializable{
    private var studentList = ArrayList<Student>()
    fun getStudentList(): ArrayList<Student> {
        return this.studentList
    }
    fun addStudent(student: Student){
        studentList.add(student)
    }
    fun deleteStudent(student: Student){
        for (i in studentList.indices){
            if (studentList[i].isEqual(student)){
                studentList.removeAt(i)
                return
            }
        }
    }
    fun editStudentInfo(oldStudent: Student, newStudent: Student){
        for (i in studentList.indices){
            if (studentList[i].isEqual(oldStudent)){
                studentList[i].setName(newStudent.getName())
                studentList[i].setBirthday(newStudent.getBirthday())
                studentList[i].setGender(newStudent.getGender())
                studentList[i].setClassroom(newStudent.getClassroom())
                return
            }
        }
    }
}