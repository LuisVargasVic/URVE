package com.urve.remote.task

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.urve.domain.Register
import com.urve.remote.listener.RegisterListener

class TaskRegister {
    private val db = FirebaseFirestore.getInstance()
    private var registerSnapshotListener: ListenerRegistration? = null

    companion object {
        private const val USERS_KEY = "users"
        const val AUTH_KEY = "auth"
    }

    fun getRegister(remoteListener: RegisterListener?) {
        registerSnapshotListener = db.collection(USERS_KEY).document(FirebaseAuth.getInstance().currentUser!!.uid)
            .addSnapshotListener(EventListener { documentSnapshot, e ->
                remoteListener?.preExecute()
                if (e != null) {
                    remoteListener?.errorMessage(e.localizedMessage)
                    return@EventListener
                }
                if (FirebaseAuth.getInstance().currentUser == null) {
                    remoteListener?.session()
                    return@EventListener
                }
                if (documentSnapshot != null) {
                    val auth = documentSnapshot[AUTH_KEY] as Boolean?
                    remoteListener?.postExecute(
                        Register(
                            FirebaseAuth.getInstance().currentUser!!.uid,
                            auth ?: false
                        )
                    )
                } else {
                    remoteListener?.postExecute(
                        Register(
                            FirebaseAuth.getInstance().currentUser!!.uid,
                            false
                        )
                    )
                }
            })
    }

    fun removeListener() {
        registerSnapshotListener?.remove()
    }

}