package com.example.firebasemvp.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasemvp.R
import com.example.firebasemvp.adapter.UserAdapter
import com.example.firebasemvp.common.Common
import com.example.firebasemvp.model.Key
import com.example.firebasemvp.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainInterface.View,UserAdapter.OnItemCLickListener {
    private lateinit var presenter: MainPresenterImpl
    private lateinit var adapter: UserAdapter
    private lateinit var key:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenterImpl(this)
        presenter.requestData()
        btnRegistration.setOnClickListener(View.OnClickListener {
            presenter.addDataUserToDB()
            presenter.requestData()
        })
        btnUpdate.setOnClickListener(View.OnClickListener {
            presenter.updateDataUserToDB()
        })
        btnDelete.setOnClickListener(View.OnClickListener {
            presenter.deleteDataUserToDB()
        })
        btnLoadData.setOnClickListener(View.OnClickListener {
            presenter.requestData()
        })

    }

    override fun getDataUser(): User {
        return User(edtUsername.text.toString(), edtPassword.text.toString())
    }

    override fun setDatatoRecyclerView(list: ArrayList<Key>) {
        val listUser:ArrayList<User> = ArrayList()
        Log.d("AAA",list.size.toString())
        for (i in 0 until list.size)
            listUser.add(list[i].user)
        adapter = UserAdapter(listUser,this)
        val verticalLayoutManager =
            LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = verticalLayoutManager
        recyclerView.adapter = adapter

    }

    override fun getDataUserToUpdate(): Key {
        return Key(key,User(edtUsername.text.toString(), edtPassword.text.toString()))
    }

    override fun getDataUserToDelete(): String {
        return edtUsername.text.toString()
    }

    override fun onItemCLick(position: Int) {
        key=Common.list[position].id
        edtUsername.setText(Common.list[position].user.username)
        edtPassword.setText(Common.list[position].user.password)
    }

}