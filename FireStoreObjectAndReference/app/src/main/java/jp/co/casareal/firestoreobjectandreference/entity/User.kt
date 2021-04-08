package jp.co.casareal.firestoreobjectandreference.entity

import com.google.firebase.firestore.DocumentReference
import java.io.Serializable

data class User(
    val name: String = "",
    val age: Int = 0,
    var detailReference: DocumentReference? = null
) : Serializable