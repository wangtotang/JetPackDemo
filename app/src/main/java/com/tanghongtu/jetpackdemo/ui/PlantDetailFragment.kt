package com.tanghongtu.jetpackdemo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ShareCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.tanghongtu.jetpackdemo.R
import com.tanghongtu.jetpackdemo.databinding.FragmentPlantDetailBinding
import com.tanghongtu.jetpackdemo.utilities.InjectorUtils
import com.tanghongtu.jetpackdemo.viewmodels.PlantDetailViewModel

class PlantDetailFragment : Fragment() {

    companion object {
        private const val TAG = "PlantDetailFragment"
    }

    private val args: PlantDetailFragmentArgs by navArgs()

    private val plantDetailViewModel: PlantDetailViewModel by viewModels {
        InjectorUtils.providePlantDetailViewModelFactory(requireActivity(), args.plantId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlantDetailBinding.inflate(inflater, container, false)
            .apply {

                viewModel = plantDetailViewModel
                lifecycleOwner = viewLifecycleOwner

                toolbar.setNavigationOnClickListener {
                    it.findNavController().navigateUp()
                }

                toolbar.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.action_share -> {
                            createShareIntent()
                            true
                        }
                        else -> false
                    }
                }

                setClickListener {
                    hideFab(fab)
                    plantDetailViewModel.addPlantToGarden()
                    Snackbar.make(root, R.string.add_plant_tips, Snackbar.LENGTH_LONG).show()
                }

                var isToolbarShown = false

                nsvPlantDetail.setOnScrollChangeListener(
                    NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
                        Log.d(TAG, "scrollY:$scrollY")
                        val shouldShowToolbar = scrollY > toolbar.height

                        if (isToolbarShown != shouldShowToolbar) {
                            isToolbarShown = shouldShowToolbar
                            appbar.isActivated = shouldShowToolbar
                            toolbarLayout.isTitleEnabled = shouldShowToolbar
                        }

                    })
            }

        return binding.root
    }

    private fun hideFab(fab: FloatingActionButton) {
        val layoutParams = fab.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = layoutParams.behavior as FloatingActionButton.Behavior
        behavior.isAutoHideEnabled = false
        fab.hide()
    }

    private fun createShareIntent() {
        val shareText = plantDetailViewModel.plant.value.let {
            if (it == null) {
                ""
            } else {
                getString(R.string.share_text, it.name)
            }
        }

        val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
            .setText(shareText)
            .setType("text/plain")
            .createChooserIntent()
            .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        startActivity(shareIntent)
    }

}
