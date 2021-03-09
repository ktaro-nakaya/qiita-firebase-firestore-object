package jp.co.casareal.firestoreobjectandreference.entity

import com.google.firebase.firestore.DocumentReference

data class User(val name:String, val age:Int){
    constructor():this("",0)
}
