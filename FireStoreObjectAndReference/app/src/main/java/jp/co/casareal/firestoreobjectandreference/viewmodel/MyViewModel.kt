package jp.co.casareal.firestoreobjectandreference.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import jp.co.casareal.firestoreobjectandreference.entity.User
import jp.co.casareal.firestoreobjectandreference.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(
    private val repository: UserRepository = UserRepository(),
    val users:Query = repository.getAllUser()
) : ViewModel()