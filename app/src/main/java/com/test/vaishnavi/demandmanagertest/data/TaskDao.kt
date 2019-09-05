package com.test.vaishnavi.testdm.data

import android.arch.lifecycle.LiveData

import android.arch.persistence.room.*

@Dao
interface TaskDao{
    @Query("SELECT * FROM Task")
    abstract fun getTasks(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTask(task: Task)


    @Query("DELETE FROM Task WHERE title = :title")
    abstract fun deleteByTitle(title: String)

    @Query("UPDATE Task SET title = :newtitle WHERE title=:temp")
    abstract fun updateByTitle(temp: String, newtitle: String)

}