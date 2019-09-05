package com.test.vaishnavi.demandmanagertest.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.test.vaishnavi.demandmanagertest.R
import com.test.vaishnavi.demandmanagertest.add.AddActivity
import com.test.vaishnavi.testdm.data.Task
import com.test.vaishnavi.testdm.data.TaskDatabase

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.view.*


class MainActivity : AppCompatActivity() {

    lateinit var bottomBar : BottomNavigationView
    var db : TaskDatabase? = null
    lateinit var list : List<Task>
    lateinit var adapter : MainAdapter
    lateinit var viewModel : MainViewModel
    var pos :Int =0

    companion object {
        var title_delete : String=""
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            startActivity(Intent(this, AddActivity::class.java))
        }

        list = ArrayList()

        //set up recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager?


        //Initialise View Model in the Layout
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainViewModel::class.java)
        //subscribe to LiveData
        viewModel.getDataList(this).observe(this, object : Observer<List<Task>> {
            override fun onChanged(taskList: List<Task>?) {
                list = taskList!!
                adapter = MainAdapter(applicationContext, taskList)
                recyclerView.adapter = adapter
            }
        })

        recyclerView.addOnItemTouchListener(
            RecyclerTouchListener(
                applicationContext,
                recyclerView,
                object : RecyclerTouchListener.ClickListener {
                    override fun onClick(view: View, position: Int) {
                        title_delete =
                                view.title.text.toString()
                        Log.d(
                            "vish",
                            "delete string" + title_delete
                        )
                    }

                    override fun onLongClick(view: View, position: Int) {
                    }

                })
        )

    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_edit -> editList()
            R.id.action_delete ->deleteList()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteList(): Boolean {
        TaskDatabase.getInstance(this).taskDao().deleteByTitle(title_delete)
        return true
    }

    private fun editList(): Boolean {
        var intent :Intent = Intent(this, AddActivity::class.java)
        intent.putExtra("text_edit", title_delete);
        startActivity(intent)
        return true
    }

}
