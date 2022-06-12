package com.aliosmanarslan.itunessearchapi.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aliosmanarslan.itunessearchapi.R
import com.aliosmanarslan.itunessearchapi.data.ItemListData
import com.aliosmanarslan.itunessearchapi.databinding.ItemListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemListFragment : Fragment(R.layout.item_list_fragment) , ItemListPagingAdapter.OnItemClickListener{

    private var _binding : ItemListFragmentBinding? = null
    private val binding get() = _binding!!


    private val viewModel by viewModels<ItemListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backgroundColor()
        _binding = ItemListFragmentBinding.bind(view)

        val adapter = ItemListPagingAdapter(this)

        binding.cSearchBar.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                if(text?.trim()?.length!! >= 2){
                    viewModel.searchItems(text.trim().toString(),binding.sbCategories.position)
                    binding.rvItemList.scrollToPosition(0)
                } else {
                    viewModel.searchItems("",binding.sbCategories.position)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.sbCategories.setOnPositionChangedListener { position ->
            viewModel.searchItems(binding.cSearchBar.getSearchedText(), position)
            binding.rvItemList.scrollToPosition(0)
        }

        binding.apply {
            rvItemList.setHasFixedSize(true)
            rvItemList.adapter = adapter.withLoadStateHeaderAndFooter(
                header = ItemListLoadStateAdapter { adapter.retry() },
                footer = ItemListLoadStateAdapter { adapter.retry() }
            )
        }

        viewModel.listItems.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(item: ItemListData) {
        val action = ItemListFragmentDirections.goToDetailPage(item, viewModel.getCurrentQuery())
        findNavController().navigate(action)
    }

    private fun backgroundColor() {
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        activity?.window?.navigationBarColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        activity?.window?.setBackgroundDrawableResource(R.drawable.itunes_gradient_color)
    }
}