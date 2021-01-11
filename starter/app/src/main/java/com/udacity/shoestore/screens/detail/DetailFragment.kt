package com.udacity.shoestore.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.DetailFragmentBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.listing.ListingFragmentDirections
import com.udacity.shoestore.screens.listing.ListingViewModel

class DetailFragment : Fragment() {

    private val viewModel: ListingViewModel by activityViewModels()

    private val shoe = Shoe("Sample shoe", 36.0,"Tesla", "Eletric Shoe" )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val binding: DetailFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.detail_fragment, container, false
        )

        binding.shoe = shoe

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListingFragment())
        }

        binding.saveButton.setOnClickListener {
            viewModel.add(shoe)
            findNavController().navigateUp()
        }

        return binding.root
    }
}