package com.test.vaishnavi.demandmanagertest.main

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.test.vaishnavi.demandmanagertest.R

import com.test.vaishnavi.testdm.data.Task

class MainAdapter(val context: Context?, val list: List<Task>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    class MainViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {
        lateinit var title_text :CheckBox
        lateinit var desc_text :TextView

        init{
            title_text = itemview.findViewById(R.id.title)
            desc_text = itemview.findViewById(R.id.desc)
            desc_text.setVisibility(View.GONE)

        }
    }

   /* fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }
*/
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainViewHolder {
        var view : View = LayoutInflater.from(context).inflate(R.layout.list_item, p0, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("vish","size : "+list.size)
        return list.size
    }

    override fun onBindViewHolder(p0: MainViewHolder, p1: Int) {
        p0.title_text.setText(list.get(p1).title)
        p0.desc_text.setText(list.get(p1).description)
        p0.title_text.setOnClickListener{
        }

        p0.title_text.setOnCheckedChangeListener{buttonView, isChecked ->
            if(isChecked){
                p0.title_text.setPaintFlags(p0.title_text.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
                p0.title_text.setEnabled(false)
            }
        }
    }
}


