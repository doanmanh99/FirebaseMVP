package com.example.firebasemvp.screen.main

import com.example.firebasemvp.model.Key
import com.example.firebasemvp.model.User

interface MainInterface {
    interface Model{
        fun insertDataUser(user: User)

        fun getDataUser(onFinishedListener: OnFinishedListener)
        interface OnFinishedListener {
            fun onFinished(list: ArrayList<Key>)
            fun onError(error: String)
        }

        fun updateDataUser(key: Key,onFinishedListener: MainInterface.Model.OnFinishedListener)

        fun deleteDataUser(username:String,onFinishedListener: MainInterface.Model.OnFinishedListener)
    }
    interface View{
        fun getDataUser(): User
        fun setDatatoRecyclerView(list: ArrayList<Key>)

        fun getDataUserToUpdate() : Key

        fun getDataUserToDelete() : String
    }
    interface Presenter{
        fun addDataUserToDB()
        fun requestData()
        fun updateDataUserToDB()
        fun deleteDataUserToDB()
    }
}