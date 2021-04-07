package jp.co.casareal.firestoreobjectandreference.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query

import jp.co.casareal.firestoreobjectandreference.R
import jp.co.casareal.firestoreobjectandreference.databinding.RowBinding
import jp.co.casareal.firestoreobjectandreference.entity.User

class UserAdapter(
    private val lifecycleOwner: LifecycleOwner,
    query: Query,
    val onRowClick: (String?) -> Unit
) :
    FirestoreAdapter<UserAdapter.MyViewHolder>(query) {


    inner class MyViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                getSnapshot(adapterPosition)?.let {
                    onRowClick(it.reference.path)
                    Toast.makeText(
                        this.binding.root.context,
                        "detailReference:${it.reference.path}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getSnapshot(position)?.let {
            holder.binding.model = it.toObject(User::class.java)
        }
    }
}