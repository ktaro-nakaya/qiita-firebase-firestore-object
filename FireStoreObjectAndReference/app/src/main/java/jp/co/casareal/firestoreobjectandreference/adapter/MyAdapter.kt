package jp.co.casareal.firestoreobjectandreference.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView

import jp.co.casareal.firestoreobjectandreference.R
import jp.co.casareal.firestoreobjectandreference.databinding.RowBinding
import jp.co.casareal.firestoreobjectandreference.entity.User

class MyAdapter(private val lifecycleOwner: LifecycleOwner, val onRowClick: (User) -> Unit) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var list: List<User>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MyViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                list?.get(adapterPosition)?.let {
                    onRowClick(it)
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
        list?.get(position)?.let {

            holder.binding.model = it
            holder.binding.lifecycleOwner = lifecycleOwner
        }

    }

    override fun getItemCount(): Int = list?.size ?: 0
}