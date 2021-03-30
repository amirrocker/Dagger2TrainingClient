package de.amirrocker.testdagger2modules.training.sessiondetails.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import de.amirrocker.testdagger2modules.application.TestDagger2ModulesApplication
import de.amirrocker.testdagger2modules.base.presentation.recyclerview.RecyclerViewAdapter
import de.amirrocker.testdagger2modules.databinding.FragmentMainViewBinding
import de.amirrocker.testdagger2modules.databinding.FragmentTrainingSessionDetailsBinding
import de.amirrocker.testdagger2modules.home.data.trainingSession.TrainingSession
import de.amirrocker.testdagger2modules.home.presentation.MainViewFragmentViewModel
import de.amirrocker.testdagger2modules.registration.RegistrationActivity
import javax.inject.Inject

class TrainingSessionDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: RecyclerViewAdapter

    val viewModel: TrainingSessionDetailsFragmentViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(TrainingSessionDetailsFragmentViewModel::class.java)
    }

    private var _binding: FragmentTrainingSessionDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /**
     * inside the fragment
     */
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        (requireActivity().application as TestDagger2ModulesApplication)
            .applicationComponent
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTrainingSessionDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        val viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewFragmentViewModel::class.java)

        viewModel.trainingSessionListLiveData.observe(viewLifecycleOwner, this::updateView)

    }

    private fun updateView(items:TrainingSession) {
        adapter.update(listOf(items))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()

    }

    private fun configureRecyclerView() {
        binding.rvTrainingSessionDetailsTrainingsList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.rvTrainingSessionDetailsTrainingsList.adapter = adapter

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}