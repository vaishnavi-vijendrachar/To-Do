package com.test.vaishnavi.demandmanagertest

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.test.vaishnavi.testdm.data.Task
import com.test.vaishnavi.testdm.data.TaskDatabase

class MainViewModel : ViewModel() {

    private var tasklist: LiveData<List<Task>>? = null

    fun getDataList(context: Context): LiveData<List<Task>> {
        if (tasklist == null) {
            tasklist = MutableLiveData<List<Task>>()
        }
        tasklist = TaskDatabase.getInstance(context).taskDao().getTasks()
        return tasklist as LiveData<List<Task>>
    }


//    fun deleteDataList(context: Context, title : String): LiveData<List<Task>> {
//        if (tasklist == null) {
//            tasklist = MutableLiveData<List<Task>>()
//        }
//        TaskDatabase.getInstance(context).taskDao().deleteByTitle(title)
//        return tasklist as LiveData<List<Task>>
//    }
}