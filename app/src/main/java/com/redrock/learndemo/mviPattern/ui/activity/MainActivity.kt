package com.redrock.learndemo.mviPattern.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.flow.collect
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.redrock.learndemo.R
import com.redrock.learndemo.mviPattern.bean.User
import com.redrock.learndemo.mviPattern.network.RetrofitBuilder
import com.redrock.learndemo.mviPattern.ui.ViewModel.MainViewModel
import com.redrock.learndemo.mviPattern.ui.ViewModel.ViewModelFactory
import com.redrock.learndemo.mviPattern.ui.adapter.MainAdapter
import com.redrock.learndemo.mviPattern.ui.intent.MainIntent
import com.redrock.learndemo.mviPattern.ui.state.MainState
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.launch

/**
 * MVI architecture
 */
class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private var adapter = MainAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setupUI()
        setupViewModel()
        observeViewModel()
        setupClicks()
    }

    private fun setupClicks() {
        //用户行为通过Intent发送给ViewModel从而改变ViewModel中的uiState
        //用户订阅UiState的变化从而请求UI的改变
        buttonFetchUser.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }
        }
    }


    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        recyclerView.adapter = adapter
    }


    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                    RetrofitBuilder.apiService
            )
        ).get(MainViewModel::class.java)
    }

    //用户订阅UiState的变化从而请求UI的改变
    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {

                    }
                    is MainState.Loading -> {
                        buttonFetchUser.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }

                    is MainState.Users -> {
                        progressBar.visibility = View.GONE
                        buttonFetchUser.visibility = View.GONE
                        renderList(it.user)
                    }
                    is MainState.Error -> {
                        progressBar.visibility = View.GONE
                        buttonFetchUser.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(users: List<User>) {
        recyclerView.visibility = View.VISIBLE
        users.let { listOfUsers -> listOfUsers.let { adapter.addData(it) } }
        adapter.notifyDataSetChanged()
    }

}