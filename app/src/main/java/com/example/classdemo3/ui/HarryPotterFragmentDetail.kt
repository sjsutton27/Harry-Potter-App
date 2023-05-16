
package com.example.classdemo3.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.classdemo3.R
import com.example.classdemo3.databinding.FragmentHarrypotterDetailBinding
import com.example.classdemo3.ui.adapter.HarryPotterAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.example.classdemo3.viewmodel.HarryPotterCharacterDetailViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HarryPotterDetailFragment : Fragment() {

    private var _binding: FragmentHarrypotterDetailBinding? = null
    private val binding get() = _binding!!
    private val harryPotterDetailViewModel: HarryPotterCharacterDetailViewModel by activityViewModels()
    val harryPotterAdapter = HarryPotterAdapter{ harryPotter, _ ->
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(
                R.id.fragment_container_view,
                newInstance(harryPotter.id)
            )
            addToBackStack(null)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        _binding = FragmentHarrypotterDetailBinding.inflate(inflater, container, false)
        setUp()
        return binding.root
    }

    private fun setUp(){
        harryPotterDetailViewModel.fetchById(requireArguments().getString(BUNDLE_ID).orEmpty())
        lifecycleScope.launch {
            harryPotterDetailViewModel.harryPotterCharacters.collect { event ->
                when (event) {
                    is HarryPotterCharacterDetailViewModel.HarryPotterCharacterEvent.Success -> {
                        val harryPotter = event.harryPotterCharacters?.firstOrNull()
                        binding.harryPotterName.text = "Name: ${harryPotter?.name}"
                        binding.harryPotterSpecies.text = "Species: ${harryPotter?.species}"
                        binding.harryPotterGender.text = "Gender: ${harryPotter?.gender}"
                        binding.harryPotterHouse.text = "Hogwart's House: ${harryPotter?.house}"
                        binding.harryPotterDateOfBirth.text = "DOB: ${harryPotter?.dateOfBirth}"
                        binding.harryPotterHogwartsStudent.text = "Hogwarts Student: ${if (harryPotter?.hogwartsStudent == true) "True" else "False"}"
                        binding.harryPotterHogwartsStaff.text = "Hogwarts Staff: ${if (harryPotter?.hogwartsStaff == true) "True" else "False"}"
                        binding.harryPotterWizard.text = "Wizard: ${if (harryPotter?.wizard == true) "True" else "False"}"
                        binding.harryPotterAlive.text = "Alive: ${if (harryPotter?.alive == true) "True" else "False"}"

                        Glide.with(this@HarryPotterDetailFragment)
                            .load(harryPotter?.image)
                            .into(binding.harryPotterImage)
                    }
                    HarryPotterCharacterDetailViewModel.HarryPotterCharacterEvent.Failure -> {
                        binding.harryPotterName.text = "Error"
                        binding.harryPotterSpecies.text = "Error"
                        binding.harryPotterGender.text = "Error"
                        binding.harryPotterHouse.text = "Error"
                        binding.harryPotterDateOfBirth.text = "Error"
                        binding.harryPotterHogwartsStudent.text = "Error"
                        binding.harryPotterHogwartsStaff.text = "Error"
                        binding.harryPotterWizard.text = "Error"
                        binding.harryPotterAlive.text = "Error"
                    }
                    HarryPotterCharacterDetailViewModel.HarryPotterCharacterEvent.Loading -> {
                        binding.harryPotterName.text = "Loading"
                        binding.harryPotterSpecies.text = "Loading"
                        binding.harryPotterGender.text = "Loading"
                        binding.harryPotterHouse.text = "Loading"
                        binding.harryPotterDateOfBirth.text = "Loading"
                        binding.harryPotterHogwartsStudent.text = "Loading"
                        binding.harryPotterHogwartsStaff.text = "Loading"
                        binding.harryPotterWizard.text = "Loading"
                        binding.harryPotterAlive.text = "Loading"
                    }
                    else -> {}
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
          private const val BUNDLE_ID = "id"
          //can add a bunch of bundles like BUNDLE_NAME = "name"
          fun newInstance(id: String)= HarryPotterDetailFragment().apply {
             arguments = bundleOf(BUNDLE_ID to id)
          }
      }
}





