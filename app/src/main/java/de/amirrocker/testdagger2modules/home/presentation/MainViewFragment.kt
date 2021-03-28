package de.amirrocker.testdagger2modules.home.presentation

import android.app.Activity
import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import de.amirrocker.testdagger2modules.application.TestDagger2ModulesApplication
import de.amirrocker.testdagger2modules.base.presentation.recyclerview.RecyclerViewAdapter
import de.amirrocker.testdagger2modules.databinding.FragmentMainViewBinding
import de.amirrocker.testdagger2modules.home.data.trainingSession.TrainingSession
import de.amirrocker.testdagger2modules.registration.RegistrationActivity
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainViewFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: RecyclerViewAdapter

    private var _binding: FragmentMainViewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val viewModel:MainViewFragmentViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewFragmentViewModel::class.java)
    }

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

        _binding = FragmentMainViewBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        val viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewFragmentViewModel::class.java)

        viewModel.trainingSessionListLiveData.observe(viewLifecycleOwner, this::updateListView)

    }

    private fun updateListView(items:List<TrainingSession>) {
        adapter.update(items)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()

        binding.buttonStartTraining.setOnClickListener {
            viewModel.startNewTraining()

        }

        // use the textfield for now to start the activity.
        binding.tvRegisterNow.setOnClickListener {
            startActivity(Intent(activity?.applicationContext, RegistrationActivity::class.java))
        }
    }

    private fun configureRecyclerView() {
        binding.recyclerViewTrainingSessionList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.recyclerViewTrainingSessionList.adapter = adapter

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}