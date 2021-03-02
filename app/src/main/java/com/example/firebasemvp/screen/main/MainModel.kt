package com.example.firebasemvp.screen.main

import android.util.Log
import com.example.firebasemvp.common.Common
import com.example.firebasemvp.model.Key
import com.example.firebasemvp.model.User
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainModel: MainInterface.Model {

    private val database: DatabaseReference = Firebase.database.reference
    override fun insertDataUser(user: User) {
        database.child("users").push().setValue(user)

    }

    override fun getDataUser(onFinishedListener: MainInterface.Model.OnFinishedListener) {
        val list:ArrayList<Key> = ArrayList()
        Common.list = ArrayList()
        database.child("users").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("AAA",snapshot.value.toString())
                for (userSnapshot in snapshot.children) {
                    if (userSnapshot.getValue(User::class.java)!= null){
                        list.add(Key(userSnapshot.key.toString(), userSnapshot.getValue(User::class.java)!!
                        ))
                    }

                }
                Common.list.addAll(list)
                onFinishedListener.onFinished(list)
            }

            override fun onCancelled(error: DatabaseError) {
                onFinishedListener.onError(error.message.toString())
            }

        })
    }


    override fun updateDataUser(key: Key,onFinishedListener: MainInterface.Model.OnFinishedListener) {
        Common.list = ArrayList()
        database.child("users").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.ref.child(key.id).setValue(key.user)
                getDataUser(onFinishedListener)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    override fun deleteDataUser(username: String,onFinishedListener: MainInterface.Model.OnFinishedListener) {
        database.child("users").orderByChild("username").equalTo(username).addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (usernameSnapshot in snapshot.children) {
                        usernameSnapshot.ref.removeValue()
                    }
                    getDataUser(onFinishedListener)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("AAA", error.message.toString())
                }

            })
    }
}