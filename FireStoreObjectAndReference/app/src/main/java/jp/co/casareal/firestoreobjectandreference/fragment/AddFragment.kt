package jp.co.casareal.firestoreobjectandreference.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import jp.co.casareal.firestoreobjectandreference.R
import jp.co.casareal.firestoreobjectandreference.databinding.FragmentAddBinding
import jp.co.casareal.firestoreobjectandreference.viewmodel.RegisterUserViewModel

class AddFragment : Fragment() {

    val model = viewModels<RegisterUserViewModel>()
    lateinit var binding: FragmentAddBinding

    companion object{
       const val REQUEST_KEY = "ADD_REQUEST"
    }

    // メニューを一時的に消す
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    // メニューを一時的に消す
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentAddBinding>(
            inflater,
            R.layout.fragment_add,
            container,
            false
        )
        val model = viewModels<RegisterUserViewModel>()
        binding.lifecycleOwner = this
        binding.model = model.value

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.button.setOnClickListener {
            val navHost =
                requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment
            val navController = navHost.navController
            val bundle = bundleOf(
                "name" to model.value.name.value,
                "age" to model.value.age.value,
                "intro" to model.value.introduction.value
            )
            this.setFragmentResult(REQUEST_KEY, bundle)
            navController.navigateUp()
        }
    }

}