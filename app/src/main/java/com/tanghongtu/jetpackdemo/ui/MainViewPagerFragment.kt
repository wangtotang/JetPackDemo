package com.tanghongtu.jetpackdemo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator

import com.tanghongtu.jetpackdemo.R
import com.tanghongtu.jetpackdemo.adapter.MY_GARDEN_PAGER_INDEX
import com.tanghongtu.jetpackdemo.adapter.MainViewPagerAdapter
import com.tanghongtu.jetpackdemo.adapter.PLANT_LIST_PAGER_INDEX
import com.tanghongtu.jetpackdemo.databinding.FragmentMainViewPagerBinding

/**
 * A simple [Fragment] subclass.
 */
class MainViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMainViewPagerBinding.inflate(inflater, container, false)

        binding.vpMain.adapter = MainViewPagerAdapter(this)

        TabLayoutMediator(binding.tabMain, binding.vpMain) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            MY_GARDEN_PAGER_INDEX -> R.drawable.garden_tab_selector
            PLANT_LIST_PAGER_INDEX -> R.drawable.plant_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String {
        return when (position) {
            MY_GARDEN_PAGER_INDEX -> getString(R.string.garden_title)
            PLANT_LIST_PAGER_INDEX -> getString(R.string.plant_list_title)
            else -> throw IndexOutOfBoundsException()
        }
    }

}
