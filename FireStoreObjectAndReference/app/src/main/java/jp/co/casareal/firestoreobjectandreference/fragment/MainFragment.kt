package jp.co.casareal.firestoreobjectandreference.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.co.casareal.firestoreobjectandreference.R
import jp.co.casareal.firestoreobjectandreference.adapter.UserAdapter
import jp.co.casareal.firestoreobjectandreference.entity.Detail
import jp.co.casareal.firestoreobjectandreference.entity.User
import jp.co.casareal.firestoreobjectandreference.repository.UserRepository
import jp.co.casareal.firestoreobjectandreference.service.MyService
import jp.co.casareal.firestoreobjectandreference.utils.FirebaseUtil
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

        // 画面遷移から返ってきたらユーザ情報を登録
        setFragmentResultListener(AddFragment.REQUEST_KEY) { key, bundle ->
            bundle.getString("name")?.let { name ->
                bundle.getString("age")?.let { strAge ->
                    strAge.toIntOrNull()?.let { age ->
                        bundle.getString("intro")?.let { intro ->
                            // detailを登録
                            val service = MyService()

                            service.addUser(
                                User(name, age, null),
                                Detail(intro)
                            )
                        }
                    }
                }

            }

        }
    }

    override fun onResume() {
        super.onResume()

        // 一覧への表示処理
        val recyclerView = requireActivity().findViewById<RecyclerView>(R.id.myList)

        val myViewModel = ViewModelProvider.NewInstanceFactory().create(MyViewModel::class.java)
        val adapter = UserAdapter(this, myViewModel.users) {
            val navController = requireActivity().findNavController(R.id.fragmentHost)
            it?.let {
                navController.navigate(MainFragmentDirections.actionMainFragmentToDetailFragment( userDocumentPath =  it))
            }
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayout.VERTICAL
            )
        )
    }
}