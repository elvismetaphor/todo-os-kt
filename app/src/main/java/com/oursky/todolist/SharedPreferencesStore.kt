package com.oursky.todolist

interface SharedPreferencesStore {
    fun getTodoList(): ArrayList<TodoModel>
    fun setTodoList(todos: ArrayList<TodoModel>)
}