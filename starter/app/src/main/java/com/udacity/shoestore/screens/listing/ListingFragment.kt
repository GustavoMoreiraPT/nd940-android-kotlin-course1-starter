package com.udacity.shoestore.screens.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseBindingAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ListingFragmentBinding
import androidx.lifecycle.Observer
import com.udacity.shoestore.databinding.DetailFragmentBinding
import androidx.fragment.app.activityViewModels

class ListingFragment: Fragment() {

    private val viewModel: ListingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: ListingFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.listing_fragment, container, false)

        binding.addItemToInventory.setOnClickListener{
            findNavController().navigate(ListingFragmentDirections.actionListingFragmentToDetailFragment())
        }

        viewModel.shoes.observe(viewLifecycleOwner, Observer { list ->
            binding.inventory.removeAllViews()
            list.forEach { shoe ->
                val inflater = LayoutInflater.from(binding.inventory.context)
                val binding: DetailFragmentBinding =
                        DetailFragmentBinding.inflate(inflater, binding.inventory, true)
                binding.shoe = shoe
            }

        })
        setHasOptionsMenu(true)
        return binding.root
    }
}