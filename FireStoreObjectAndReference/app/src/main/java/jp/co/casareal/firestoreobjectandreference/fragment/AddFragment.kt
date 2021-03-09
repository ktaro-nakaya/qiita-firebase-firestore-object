package jp.co.casareal.firestoreobjectandreference.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val REQUEST_KEY = "ADD_REQUEST"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
                "name" to model.value.name,
                "age" to model.value.age,
                "intro" to model.value.introduction
            )
            this.setFragmentResult(REQUEST_KEY, bundle)
            navController.navigateUp()
        }
    }

}