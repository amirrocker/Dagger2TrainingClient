package de.amirrocker.testdagger2modules.registration

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import de.amirrocker.testdagger2modules.R
import de.amirrocker.testdagger2modules.application.TestDagger2ModulesApplication
import de.amirrocker.testdagger2modules.databinding.FragmentRegistrationEnterDetailsBinding
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EnterDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EnterDetailsFragment : Fragment() {


    @Inject lateinit var registrationViewModel: RegistrationViewModel

    @Inject lateinit var enterDetailsViewModel : EnterDetailsViewModel

    private var _binding: FragmentRegistrationEnterDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)

        ((activity).application as TestDagger2ModulesApplication)
            .applicationComponent
            .getRegistrationComponent()
            .create()
            .inject(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
////            param1 = it.getString(ARG_PARAM1)
////            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_registration_enter_details, container, false)

        _binding = FragmentRegistrationEnterDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonTermsAndConditions.setOnClickListener {
            findNavController().navigate(R.id.action_EnterDetailsRegistrationFragment_to_TermsAndConditionsFragment)
        }

        binding.buttonRegisterUser.setOnClickListener {
            println("register user")
            registrationViewModel.registerUser()
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EnterDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            EnterDetailsFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}