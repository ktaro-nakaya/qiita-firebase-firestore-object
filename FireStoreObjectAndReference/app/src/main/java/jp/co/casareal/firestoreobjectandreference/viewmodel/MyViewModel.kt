package jp.co.casareal.firestoreobjectandreference.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import jp.co.casareal.firestoreobjectandreference.entity.User

class MyViewModel(
       private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance(),
        val users:MutableLiveData<List<User>> = MutableLiveData()
) :ViewModel() {
    init {
        firestore.collection("users")

                .get()
            .addOnSuccessListener {
                it.toObjects(User::class.java)
            }
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        it.result?.forEach {
                           Log.e("TEST", it.data.toString())
                        }

                        it.result?.let {
                           users.postValue(it.toObjects(User::class.java))
                        }
                    }
                }
    }
}