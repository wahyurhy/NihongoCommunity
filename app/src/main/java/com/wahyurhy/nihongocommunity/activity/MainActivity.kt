package com.wahyurhy.nihongocommunity.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.wahyurhy.nihongocommunity.R
import com.wahyurhy.nihongocommunity.databinding.ActivityMainBinding
import com.wahyurhy.nihongocommunity.fragment.CommunityFragment
import com.wahyurhy.nihongocommunity.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTabs()
        initClick()
    }

    private fun initClick() {
        binding.btnAddPost.setOnClickListener {
            startActivity(Intent(this, AddPostActivity::class.java))
        }
    }

    private fun initTabs() {
        val communityFragment = CommunityFragment()
        val profileFragment = ProfileFragment()
        makeCurrentFragment(communityFragment)
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.community_fragment -> {
                    makeCurrentFragment(communityFragment)
                    it.isChecked = true
                    it.setIcon(R.drawable.ic_community_state)
                    false
                }
                R.id.profile_fragment -> {
                    makeCurrentFragment(profileFragment)
                    it.isChecked = true
                    it.setIcon(R.drawable.ic_profile_state)
                    false
                }
                else -> { false }
            }
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }
}