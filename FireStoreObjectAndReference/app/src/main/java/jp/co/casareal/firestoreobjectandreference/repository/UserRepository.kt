package jp.co.casareal.firestoreobjectandreference.repository

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import jp.co.casareal.firestoreobjectandreference.entity.Detail
import jp.co.casareal.firestoreobjectandreference.entity.User
import jp.co.casareal.firestoreobjectandreference.utils.FirebaseUtil

class UserRepository {
    private val db = FirebaseUtil.getFirestore()

    fun insertUser(user: User, detail: Detail) {

        db.runTransaction {
            val userCollection = db.collection("users")
            val detailCollection = db.collection("details")

            val newDetailDocumentReference = detailCollection.add(detail)

            newDetailDocumentReference.addOnSuccessListener {
                user.detailReference = it
                userCollection.add(user)
            }
        }.addOnSuccessListener {
            Log.d(this.javaClass.simpleName, "Transaction success!")
        }.addOnFailureListener { e ->
            Log.w(
                this.javaClass.simpleName,
                "Transaction failure.",
                e
            )
        }
    }

    fun getAllUser() = db.collection("users").orderBy("name")

    fun getAnyUserDocument(userPath:String) =
         db.document(userPath)

    fun getAnyDetailDocument(detail:String) = db.document(detail)

}


