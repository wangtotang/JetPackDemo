package com.tanghongtu.jetpackdemo.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tanghongtu.jetpackdemo.ui.GardenFragment
import com.tanghongtu.jetpackdemo.ui.PlantListFragment

/**
 * Created by tanghongtu on 2020/5/25.
 */

const val MY_GARDEN_PAGER_INDEX = 0
const val PLANT_LIST_PAGER_INDEX = 1

class MainViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    //谷歌此处用了高阶函数，我也不知道它为什么那么喜欢用高阶函数，然后...
    //原来是为了延迟初始化，做到调用的时候才去实例化对象
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        MY_GARDEN_PAGER_INDEX to { GardenFragment() },
        PLANT_LIST_PAGER_INDEX to { PlantListFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }


}