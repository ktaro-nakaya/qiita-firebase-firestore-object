package jp.co.casareal.firestoreobjectandreference.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*

abstract class FirestoreAdapter<VH : RecyclerView.ViewHolder>(
    query: Query,
    private var mRegistration: ListenerRegistration? = null,
    private var mSnapshots: MutableList<DocumentSnapshot> = mutableListOf<DocumentSnapshot>()
) : RecyclerView.Adapter<VH>(), EventListener<QuerySnapshot> {

    var mQuery: Query? = null
        set(value) {
            field = value
            // Stop listening
            stopListening()

            // Clear existing data
            mSnapshots.clear()
            notifyDataSetChanged()

            // Listen to new query
            startListening()
        }

    init {
        mQuery = query

    }

    companion object {

        val TAG = "Firestore Adapter"
    }

    override fun onEvent(documentSnapshots: QuerySnapshot?, e: FirebaseFirestoreException?) {

        // Handle errors
        if (e != null) {
            Log.w(FirestoreAdapter.TAG, "onEvent:error", e)
            return
        }
        Log.w(FirestoreAdapter.TAG, documentSnapshots.toString())
        // Dispatch the event
        for (change in documentSnapshots!!.documentChanges) {
            // Snapshot of the changed document
            val snapshot: DocumentSnapshot = change.document
            when (change.type) {
                DocumentChange.Type.ADDED -> onDocumentAdded(change) // Add this line
                DocumentChange.Type.MODIFIED -> onDocumentModified(change) // Add this line
                DocumentChange.Type.REMOVED -> onDocumentRemoved(change) // Add this line
            }
        }
        onDataChanged()
    }

    protected open fun onDocumentAdded(change: DocumentChange) {
        mSnapshots.add(change.newIndex, change.document)
        notifyItemInserted(change.newIndex)
    }

    protected open fun onDocumentModified(change: DocumentChange) {
        if (change.oldIndex == change.newIndex) {
            // Item changed but remained in same position
            mSnapshots[change.oldIndex] = change.document
            notifyItemChanged(change.oldIndex)
        } else {
            // Item changed and changed position
            mSnapshots.removeAt(change.oldIndex)
            mSnapshots.add(change.newIndex, change.document)
            notifyItemMoved(change.oldIndex, change.newIndex)
        }
    }

    protected open fun onDocumentRemoved(change: DocumentChange) {
        mSnapshots.removeAt(change.oldIndex)
        notifyItemRemoved(change.oldIndex)
    }

    open fun startListening() {
        if (mQuery != null && mRegistration == null) {
            mRegistration = mQuery!!.addSnapshotListener(this)
        }
    }

    open fun stopListening() {
        if (mRegistration != null) {
            mRegistration!!.remove()
            mRegistration = null
        }
        mSnapshots.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mSnapshots.size
    }

    protected fun getSnapshot(index: Int): DocumentSnapshot? {
        return mSnapshots[index]
    }

    protected fun onError(e: FirebaseFirestoreException?) {}

    protected fun onDataChanged() {}

}