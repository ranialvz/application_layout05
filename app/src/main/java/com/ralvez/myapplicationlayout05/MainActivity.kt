package com.ralvez.myapplicationlayout05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.ralvez.myapplicationlayout05.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fragmentsList = arrayListOf(FirstFragment(), SecondFragment(), ThirdFragment())

        binding.apply{
            viewPager.adapter = ViewPagerAdapter(fragmentsList, this@MainActivity.supportFragmentManager, lifecycle)
            TabLayoutMediator(tabView, viewPager){ tab, position ->

                when(position){

                    0 -> { tab.text = "Inventory"
                        tab.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_home_24)
                             }
                    1 -> {tab.text = "Search"
                        tab.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_search_24)
                    }
                    2 -> { tab.text = "Order"
                        tab.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_cart_24)
                        }
                }
            }.attach()



        }
    }
}