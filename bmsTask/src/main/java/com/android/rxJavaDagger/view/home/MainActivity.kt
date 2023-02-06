package com.android.rxJavaDagger.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
 import com.android.rxJavaDagger.model.MovieShowTimeListData
import com.android.rxJavaDagger.model.VenueShowFeedItem
import com.android.rxJavaDagger.view.base.BaseActivity
import com.android.rxJavaDagger.view.home.adapters.HomeImageAdapter
import com.android.rxJavaDagger.view.home.viewmodel.HomeViewModel
import com.android.rxJavaDagger.R
import kotlinx.android.synthetic.main.toolbar_normal.*
import kotlinx.android.synthetic.main.toolbar_normal.view.*
import javax.inject.Inject


class MainActivity : BaseActivity() {

    private lateinit var binding: com.android.rxJavaDagger.databinding.ActivityMainBinding

    private lateinit var venuesAdapter: HomeImageAdapter
    private val s: String = "abd"


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        initToolbar(getToolbar())
        initView()
        progressLytVisibility(true)
        s.length
        Log.e("Pint l", s.length.toString())

    }

    private fun initView() {
        getFeedData()
    }

    private fun setupUI(empList: ArrayList<VenueShowFeedItem>) {
        venuesAdapter = HomeImageAdapter(this, empList)
        val gridLayoutManager = GridLayoutManager(this, 1)
        binding.recyclerNewsList.layoutManager = gridLayoutManager
        binding.recyclerNewsList.setHasFixedSize(true)
        binding.recyclerNewsList.adapter = venuesAdapter

        venuesAdapter.onShowTimeClicked = {
            it?.let { showTimeData ->
                openMovieDetails(
                    showTimeData.title,
                    showTimeData.showDate,
                    showTimeData.showTimeList
                )
            }
        }
    }

    private fun openMovieDetails(
        title: String,
        date: String,
        showTime: List<MovieShowTimeListData>
    ) {
        ShowTimeListData = showTime as ArrayList<MovieShowTimeListData>
        Toast.makeText(this, showTime.size.toString(), Toast.LENGTH_LONG).show()
        val intent = Intent(this, ShowTimeActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("date", date)
        this.startActivity(intent)
    }

    private fun initToolbar(toolbar: Toolbar?) {
        toolbar?.run {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            invalidateOptionsMenu()
            txt_toolbar_title.text = getString(R.string.title)
        }
    }

    private fun getFeedData() {
        getViewModel().getFeedData()
    }


    private fun progressLytVisibility(isShow: Boolean) {
        if (isShow) {
            binding.lytProgress.visibility = View.VISIBLE
        } else {
            binding.lytProgress.visibility = View.GONE
        }
    }


    override fun getViewModel(): HomeViewModel {
        return ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }


    private fun getToolbar(): Toolbar? {
        return findViewById(R.id.toolbar_normal)
    }


    override fun startObservingLiveData() {
        super.startObservingLiveData()
        getFeedData()
        getViewModel().newsData.observe(this) {
            progressLytVisibility(false)
            it.let { response ->
                getToolbar().let {
                    txt_toolbar_title.text = getString(R.string.home_title)
                }
                renderVenueData(response.venues)
            }
        }

    }

    private fun renderVenueData(venueListData: ArrayList<VenueShowFeedItem>) {
        venueListData.run {
            if (venueListData.size > 0) {
                setupUI(venueListData)
                progressLytVisibility(false)

            }
        }
    }

    companion object {
        var ShowTimeListData: ArrayList<MovieShowTimeListData> = ArrayList()
    }

}
