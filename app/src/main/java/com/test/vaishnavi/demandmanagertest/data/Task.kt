package com.test.vaishnavi.testdm.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Task( var title : String, var description : String, @PrimaryKey(autoGenerate = true) var id : Int = 0) {

}