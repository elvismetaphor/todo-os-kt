package com.oursky.todolist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class TodoListViewModel : ViewModel() {
    private val mData = MutableLiveData<ArrayList<TodoModel>>()
    private lateinit var mSharePreferenceStore: SharedPreferencesStore

    fun get(): LiveData<ArrayList<TodoModel>> {
        return mData
    }

    fun initializeByStore(store: SharedPreferencesStore) {
        mSharePreferenceStore = store
        mData.value = store.getTodoList()
    }

    fun addTodo(todo: TodoModel): ArrayList<TodoModel> {
        val copy = ArrayList<TodoModel>(mData.value)
        copy.add(todo)
        mData.value = copy
        mSharePreferenceStore.setTodoList(copy)
        return copy
    }

    fun deleteTodo(todo: TodoModel): ArrayList<TodoModel> {
        val copy = ArrayList<TodoModel>(mData.value)
        copy.remove(todo)
        mData.value = copy
        mSharePreferenceStore.setTodoList(copy)
        return copy
    }

    fun setTodo(todo: TodoModel): ArrayList<TodoModel> {
        val copy = ArrayList(mData.value)
        var target = copy.find { it -> it.id == todo.id }
        var pos = copy.indexOf(target)
        copy[pos] = todo
        mData.value = copy
        mSharePreferenceStore.setTodoList(copy)
        return copy
    }
}
