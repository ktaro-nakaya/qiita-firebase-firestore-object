package jp.co.casareal.firestoreobjectandreference.fragment

import android.os.Bundle
import android.provider.Telephony
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.co.casareal.firestoreobjectandreference.R
import jp.co.casareal.firestoreobjectandreference.adapter.MyAdapter
import jp.co.casareal.firestoreobjectandreference.viewmodel.MyViewModel

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener(AddFragment.REQUEST_KEY){
            key, bundle->
            // 登録処理を行う
        }
    }

    override fun onResume() {
        super.onResume()
        val recyclerView = requireActivity().findViewById<RecyclerView>(R.id.myList)

        val myViewModel = ViewModelProvider.NewInstanceFactory().create(MyViewModel::class.java)
        val adapter = MyAdapter(this) {
            // TODO 画面遷移をあとで登録
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))

        myViewModel.users.observe(this) {
            adapter.list = it
        }
    }
}