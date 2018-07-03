package com.oursky.todolist

import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import kotlinx.android.synthetic.main.item_todo.view.*


class TodoListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface TodoListDelegate {
        fun onDeleted(todo: TodoModel)
        fun onFinished(todo: TodoModel)
    }

    private var mTodos = ArrayList<TodoModel>()
    private var mDelegate: TodoListDelegate? = null

    class ToDoItemViewHolder(view: View, adapter: TodoListAdapter) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.item_finish_checkbox
        val textView: TextView = view.item_text
        init {
            checkBox.setOnClickListener {
                adapter.notifyFinished(adapterPosition)
            }
            itemView.setOnLongClickListener {
                AlertDialog.Builder(view.context)
                        .setTitle(R.string.delete_todo)
                        .setMessage(R.string.delete_todo_message)
                        .setPositiveButton(R.string.phrase_ok) { _, _ ->
                            adapter.notifyDeleted(adapterPosition)
                        }
                        .setNegativeButton(R.string.phrase_cancel) { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {

        val toDoItemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_todo, parent, false)
        return ToDoItemViewHolder(toDoItemView, this)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val toDoItemViewHolder = holder as ToDoItemViewHolder
        toDoItemViewHolder.textView.text = mTodos[position].text
        toDoItemViewHolder.checkBox.isChecked = mTodos[position].finished
    }

    override fun getItemCount() = mTodos.size

    fun setTodos(todos: ArrayList<TodoModel>) {
        mTodos = todos
    }

    fun setDelegate(delegate: TodoListDelegate) {
        mDelegate = delegate
    }

    fun notifyDeleted(pos: Int) {
        val todo = mTodos[pos]
        mDelegate?.onDeleted(todo)
    }

    fun notifyFinished(pos: Int) {
        val todo = mTodos[pos]
        mDelegate?.onFinished(todo)
    }
}
