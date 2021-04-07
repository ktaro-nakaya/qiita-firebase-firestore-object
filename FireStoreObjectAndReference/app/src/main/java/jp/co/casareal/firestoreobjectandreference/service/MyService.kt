package jp.co.casareal.firestoreobjectandreference.service

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import jp.co.casareal.firestoreobjectandreference.entity.Detail
import jp.co.casareal.firestoreobjectandreference.entity.User
import jp.co.casareal.firestoreobjectandreference.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MyService {
    val repository = UserRepository()

    fun addUser(user: User,detail: Detail){

        repository.insertUser(user,detail)

    }

    fun getUserInfo(userPath:String) = repository.getAnyUserDocument(userPath)

    fun getUserDetail(detailPath:String) = repository.getAnyDetailDocument(detailPath)

}