package com.example.studentmanagement.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.studentmanagement.R

class StudentListAdapter(
    private val context: Activity,
    private val names: List<String>,
    private val classes: List<String>,
    private val otherInfo: List<String>,
    private val icons: List<Int>
): ArrayAdapter<String>(context, R.layout.student_list_item, names) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView: View = inflater.inflate(R.layout.student_list_item,null, false)
        val nameText = rowView.findViewById(R.id.studentFullNameTV) as TextView
        val classesText = rowView.findViewById(R.id.studentClassTV) as TextView
        val otherInforText = rowView.findViewById(R.id.studentOtherInfoTV) as TextView
        val imageView: ImageView = rowView.findViewById(R.id.studentAvatarIV)

        nameText.text = names[position]
        classesText.text = classes[position]
        otherInforText.text = otherInfo[position]
        imageView.setImageResource(icons[position])
        return rowView
    }
}