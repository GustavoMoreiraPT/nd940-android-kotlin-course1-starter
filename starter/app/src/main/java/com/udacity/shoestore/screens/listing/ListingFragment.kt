package com.udacity.shoestore.screens.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ListingFragmentBinding
import androidx.lifecycle.Observer
import com.udacity.shoestore.databinding.DetailFragmentBinding

class ListingFragment: Fragment() {

    private lateinit var viewModel: ListingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: ListingFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.listing_fragment, container, false)

        binding.addShoe.setOnClickListener{
            findNavController().navigate(ListingFragmentDirections.actionListingFragmentToDetailFragment())
        }

        viewModel.shoes.observe(
            viewLifecycleOwner,
            { shoes ->
                shoes.forEach { shoe ->
                    val shoesBinding = DataBindingUtil.inflate<DetailFragmentBinding>(
                        inflater,
                        R.layout.detail_fragment,
                        binding.titleLayout,
                        false
                    )
                    shoesBinding.nameText.text = shoe.name
                    shoesBinding.companyText.text = shoe.company
                    shoesBinding.sizeText.text = shoe.size.toInt().toString()
                    binding.titleLayout.addView(shoesBinding.root)
                }
            }
        )
        setHasOptionsMenu(true)
        return binding.root
    }
}