package com.example.firebase.manager

import com.example.firebase.`interface`.AuthHandler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FireBaseManager {
    companion object {
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser

        fun isSignedIn(): Boolean {
            return firebaseUser != null
        }

        fun signIn(email: String, password: String, handler: Any) {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        handler.onSuccess()
                    }else {
                        handler.onError(task.exception)
                    }

                }
        }

        fun signOut() {
            firebaseAuth.signOut()
        }
    }
}