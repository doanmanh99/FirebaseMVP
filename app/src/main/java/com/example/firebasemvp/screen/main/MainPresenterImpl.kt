package com.example.firebasemvp.screen.main

import com.example.firebasemvp.model.Key
import com.example.firebasemvp.model.User
import com.google.firebase.database.DataSnapshot

class MainPresenterImpl(iView: MainInterface.View): MainInterface.Presenter,MainInterface.Model.OnFinishedListener {
    private var iView: MainInterface.View
    private var iModel: MainInterface.Model
    init {
        this.iView = iView
        iModel = MainModel()
    }

    override fun addDataUserToDB() {
        iModel.insertDataUser(iView.getDataUser())
    }

    override fun requestData() {
        iModel.getDataUser(this)
    }

    override fun updateDataUserToDB() {
        iModel.updateDataUser(iView.getDataUserToUpdate(),this)
    }

    override fun deleteDataUserToDB() {
        iModel.deleteDataUser(iView.getDataUserToDelete(),this)
    }


    override fun onFinished(list: ArrayList<Key>) {
        iView.setDatatoRecyclerView(list)
    }

    override fun onError(error: String) {
        TODO("Not yet implemented")
    }

}