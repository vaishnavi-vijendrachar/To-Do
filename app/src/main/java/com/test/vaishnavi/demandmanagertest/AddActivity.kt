package com.test.vaishnavi.demandmanagertest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import com.test.vaishnavi.testdm.data.Task
import com.test.vaishnavi.testdm.data.TaskDatabase
import kotlinx.android.synthetic.main.add_layout.*


class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_layout)
        supportActionBar?.setHomeButtonEnabled(true)

        var temp : String? = intent.getStringExtra("text_edit")
        Log.d("vish","temp"+temp)
        if(!TextUtils.isEmpty(temp)){
            title_text.setText(temp)
        }

        save_fab.setOnClickListener {
            var  title: String = title_text.text.toString()
            var description : String = desc.text.toString()
            if(temp != null){
                editTask(temp,title)
            }else{
                saveTask(title,description)
            }
        }
    }

    private fun editTask(temp: String, title: String) {
        TaskDatabase.getInstance(this).taskDao().updateByTitle(temp,title)
        finish()
    }

    private fun saveTask(temp: String, title: String) {
        val task = Task(temp, title)
        TaskDatabase.getInstance(this).taskDao().insertTask(task);
        finish()
    }


}


