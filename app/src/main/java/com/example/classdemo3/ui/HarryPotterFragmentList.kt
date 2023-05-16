package com.example.classdemo3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.classdemo3.R
import com.example.classdemo3.ui.adapter.HarryPotterAdapter
import com.example.classdemo3.viewmodel.HarryPotterViewModel
import com.example.classdemo3.databinding.FragmentHarrypotterListBinding
import com.example.classdemo3.viewmodel.HarryPotterViewModel.HarryPotterEvent.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HarryPotterFragmentList : Fragment() {
    private val harryPotterViewModel: HarryPotterViewModel by activityViewModels()

    private var _binding: FragmentHarrypotterListBinding? = null
    private val binding get() = _binding!!
    val harryPotterAdapter = HarryPotterAdapter{ harryPotter, _ ->

        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(
                R.id.fragment_container_view,
               HarryPotterDetailFragment.newInstance(harryPotter.id))
            addToBackStack(null)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHarrypotterListBinding.inflate(inflater, container, false)
        setupObservers()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.characterRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = harryPotterAdapter
        }
        harryPotterViewModel.fillData()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            harryPotterViewModel.harryPotterCharacters.collect { event ->
                when (event) {
                    Failure -> {
                        binding.progressBar.isVisible = false
                        binding.characterRecyclerView.isVisible = false
                        binding.errorMessage.isVisible = true
                    }
                    Loading -> {
                        binding.progressBar.isVisible = true
                        binding.characterRecyclerView.isVisible = false
                        binding.errorMessage.isVisible = false
                    }
                    is Success -> {
                        harryPotterAdapter.refreshData(event.harryPotterCharacters)
                        binding.progressBar.isVisible = false
                        binding.errorMessage.isVisible = false
                        binding.characterRecyclerView.isVisible = true
                    }

                    else -> {}
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
