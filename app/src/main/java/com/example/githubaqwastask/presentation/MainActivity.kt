package com.example.githubaqwastask.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubaqwastask.R
import com.example.githubaqwastask.databinding.ActivityMainBinding
import com.example.githubaqwastask.utils.Resource
import com.example.githubaqwastask.utils.isInternetConnected
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var adapter: ItemAdapter? = null

    private val viewModel by viewModels<ItemViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbar)


        adapter = ItemAdapter {
            viewModel.handleVisibility()
        }


        binding?.apply {
            activity = this@MainActivity
            rvItems.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                adapter = this@MainActivity.adapter
            }
        }

        observeResponse()

        handleSwipeToRefresh()

        checkInternetAndRefresh()


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sort_by_name -> {
                viewModel.sortBy(ItemViewModel.SortOption.NAME)
                true
            }
            R.id.action_sort_by_stars -> {
                viewModel.sortBy(ItemViewModel.SortOption.STARS)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun observeResponse() {
        viewModel.itemsLD.observe(this@MainActivity) {
            when (it) {
                is Resource.Success -> {
                    Log.e("Items ", "Fired Success")
                    binding?.swipeToRefresh?.isRefreshing = false

                    adapter?.submitList(it.data ?: emptyList())
                }
                is Resource.Error -> {
                    Log.e("Items ", "Fired Error")

                    binding?.swipeToRefresh?.isRefreshing = false

                    Toast.makeText(this@MainActivity, "error", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    Log.e("Items ", "Fired Loading")
                    binding?.swipeToRefresh?.isRefreshing = true
                }

                else -> {
                }
            }
            binding?.progressBar?.isVisible = it is Resource.Loading
            binding?.rvItems?.isVisible = it is Resource.Success && !it.data.isNullOrEmpty()
            binding?.includeError?.root?.isVisible = it is Resource.Error
            if(it is Resource.Error) binding?.includeError?.tvError?.text = it.message

        }
    }

    private fun handleSwipeToRefresh() {
        binding?.swipeToRefresh?.setOnRefreshListener {
            binding?.swipeToRefresh?.isRefreshing = checkInternetAndRefresh()
        }
    }

    fun checkInternetAndRefresh(): Boolean {
        val internetConnected = isInternetConnected(this)
        binding?.includeNoInternet?.root?.isVisible = !internetConnected
        binding?.rvItems?.isVisible = internetConnected
        if (internetConnected) {
            viewModel.handleItems()
        }
        return internetConnected
    }


}