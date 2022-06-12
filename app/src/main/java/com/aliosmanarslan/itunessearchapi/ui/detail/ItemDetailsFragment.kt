package com.aliosmanarslan.itunessearchapi.ui.detail

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.aliosmanarslan.itunessearchapi.R
import com.aliosmanarslan.itunessearchapi.databinding.ItemDetailsFragmentBinding
import com.aliosmanarslan.itunessearchapi.utils.network.request.iTunesSearchKeys
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ItemDetailsFragment : Fragment() {

    private val args: ItemDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewWithBinding: ItemDetailsFragmentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_details_fragment,
            container,
            false
        )

        backgroundColor()

        if (args.itemCategory == iTunesSearchKeys.BOOKS) {
            if (!args.selectedItem.genreList.isNullOrEmpty()) {
                args.selectedItem.genreName = args.selectedItem.genreList!![0]
            }
        }

        viewWithBinding.item = args.selectedItem

        viewWithBinding.category = args.itemCategory

        viewWithBinding.clDescripton.visibility = if (args.itemCategory != iTunesSearchKeys.MOVIES && args.itemCategory != iTunesSearchKeys.BOOKS)
            View.GONE else View.VISIBLE

        viewWithBinding.tvLongDescripton.movementMethod = ScrollingMovementMethod()

        return viewWithBinding.root
    }

    private fun backgroundColor() {
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        activity?.window?.navigationBarColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        activity?.window?.setBackgroundDrawableResource(R.drawable.details_fragment_background)
    }
}