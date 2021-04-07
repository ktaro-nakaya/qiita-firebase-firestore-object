package jp.co.casareal.firestoreobjectandreference.utils

import com.google.firebase.firestore.FirebaseFirestore
import jp.co.casareal.firestoreobjectandreference.BuildConfig

class FirebaseUtil {
    companion object {
        /** Use emulators only in debug builds  */
        private val sUseEmulators: Boolean = BuildConfig.DEBUG

        private var FIRESTORE: FirebaseFirestore? = null
        private val host = "10.0.2.2"

        fun getFirestore(): FirebaseFirestore {
            if (FIRESTORE == null) {
                FIRESTORE = FirebaseFirestore.getInstance()

                // Connect to the Cloud Firestore emulator when appropriate. The host '10.0.2.2' is a
                // special IP address to let the Android emulator connect to 'localhost'.
                if (sUseEmulators) {
                    FIRESTORE!!.useEmulator(host, 8080)
                }
            }
            return FIRESTORE!!
        }
    }

}