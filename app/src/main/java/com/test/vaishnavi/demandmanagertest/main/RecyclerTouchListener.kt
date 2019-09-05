package com.test.vaishnavi.demandmanagertest.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.GestureDetector
import android.view.View


class RecyclerTouchListener() : RecyclerView.OnItemTouchListener {

    private var gestureDetector: GestureDetector? = null
    private var clickListener: ClickListener? = null

    constructor(context : Context, recyclerView: RecyclerView,clickListener : ClickListener) : this() {
        this.clickListener = clickListener
        gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                val child = recyclerView.findChildViewUnder(e.x, e.y)
                if (child != null && clickListener != null) {
                    clickListener.onLongClick(child, recyclerView.getChildPosition(child))
                }
            }
        })
    }

    interface ClickListener {
        fun onClick(view: View, position: Int)

        fun onLongClick(view: View, position: Int)
    }

    override fun onTouchEvent(p0: RecyclerView, p1: MotionEvent) {

    }

    override fun onInterceptTouchEvent(p0: RecyclerView, p1: MotionEvent): Boolean {
        val child = p0.findChildViewUnder(p1.getX(), p1.getY())
        if (child != null && clickListener != null && gestureDetector!!.onTouchEvent(p1)) {
            clickListener!!.onClick(child, p0.getChildPosition(child))
        }
        return false
    }

    override fun onRequestDisallowInterceptTouchEvent(p0: Boolean) {

    }
}