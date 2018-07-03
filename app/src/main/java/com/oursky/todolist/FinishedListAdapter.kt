package com.oursky.todolist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_todo.view.*


class FinishedListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mTodos = ArrayList<TodoModel>()

    class FinishedItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.item_text
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {

        val toDoItemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_finished, parent, false)
        return FinishedItemViewHolder(toDoItemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val toDoItemViewHolder = holder as FinishedItemViewHolder
        toDoItemViewHolder.textView.text = mTodos[position].text
    }

    override fun getItemCount() = mTodos.size

    fun setTodos(todos: ArrayList<TodoModel>) {
        mTodos = todos
    }
}
