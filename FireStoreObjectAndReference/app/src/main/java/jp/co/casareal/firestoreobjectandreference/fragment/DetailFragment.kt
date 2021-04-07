package jp.co.casareal.firestoreobjectandreference.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import jp.co.casareal.firestoreobjectandreference.R
import jp.co.casareal.firestoreobjectandreference.databinding.FragmentDetailBinding
import jp.co.casareal.firestoreobjectandreference.entity.Detail
import jp.co.casareal.firestoreobjectandreference.entity.User
import jp.co.casareal.firestoreobjectandreference.service.MyService
import jp.co.casareal.firestoreobjectandreference.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    val detailFragmentArgs by navArgs<DetailFragmentArgs>()
    val viewModel by viewModels<DetailViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        )

        binding.lifecycleOwner = this
        binding.model = viewModel

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val service = MyService()
        val userDocumentReference = service.getUserInfo(detailFragmentArgs.userDocumentPath)

        userDocumentReference.get().addOnSuccessListener {
            it.toObject(User::class.java)?.let { user ->
                viewModel.user.postValue(user)
                Log.i(this.javaClass.simpleName, "onResume: ${user}")
                user.detailReference?.let {
                    service.getUserDetail(it.path).get().addOnSuccessListener { detail ->
                        detail.toObject(Detail::class.java)?.let {
                            viewModel.detail.postValue(it)
                        }
                    }
                }

            }
        }
    }
}