package com.udacity.shoestore.screens.listing

import android.os.Bundle
import android.view.*
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
import com.udacity.shoestore.databinding.ShoeDetailBinding

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
                val binding: ShoeDetailBinding =
                        ShoeDetailBinding.inflate(inflater, binding.inventory, true)
                binding.shoe = shoe
            }

        })
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                findNavController().navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    @BindingAdapter
    fun bindDoubleInText(tv: EditText, value: Double) {
        tv.setText(value.toString())
    }

    @InverseBindingAdapter(attribute = "android:text")
    fun getDoubleFromBinding(view: TextView): Double {
        val string = view.text.toString()
        return if (string.isEmpty()) 0.0 else string.toDouble()
    }

}