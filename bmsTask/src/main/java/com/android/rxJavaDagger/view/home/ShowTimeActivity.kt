package com.android.rxJavaDagger.view.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
  import com.android.rxJavaDagger.model.MovieShowTimeListData
import com.android.rxJavaDagger.view.base.BaseActivity
import com.android.rxJavaDagger.view.home.adapters.ShowTimeAdapter
import com.android.rxJavaDagger.R
import com.android.rxJavaDagger.databinding.ActivityShowtimeBinding
import kotlinx.android.synthetic.main.date_ui_normal.*
import kotlinx.android.synthetic.main.toolbar_normal.view.*
import javax.inject.Inject

class ShowTimeActivity : BaseActivity() {

    private lateinit var binding: ActivityShowtimeBinding

    private lateinit var showTimeAdapter: ShowTimeAdapter

    private var title: String = ""
    private var date: String = ""

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_showtime)
        progressLytVisibility(true)
        title = intent.getStringExtra("title").toString()
        date = intent.getStringExtra("date").toString()
        txtDate.text = date
        initToolbar(getToolbar(), title)
        renderVenueData(MainActivity.ShowTimeListData)
    }

    private fun setupUI(empList: ArrayList<MovieShowTimeListData>?) {
        showTimeAdapter = empList?.let { ShowTimeAdapter(this, it) }!!
        val gridLayoutManager = GridLayoutManager(this, 3)
        binding.recyclerShowtimeList.layoutManager = gridLayoutManager
        binding.recyclerShowtimeList.setHasFixedSize(true)
        binding.recyclerShowtimeList.adapter = showTimeAdapter
    }

    private fun initToolbar(toolbar: Toolbar?, title: String) {
        toolbar?.run {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            invalidateOptionsMenu()
            txt_toolbar_title.text = title
        }
    }

    private fun progressLytVisibility(isShow: Boolean) {
        if (isShow) {
            binding.lytProgress.visibility = View.VISIBLE
        } else {
            binding.lytProgress.visibility = View.GONE
        }
    }

    private fun getToolbar(): Toolbar? {
        return findViewById(R.id.toolbar_normal)
    }

    private fun renderVenueData(list: ArrayList<MovieShowTimeListData>?) {
        list.run {
            if (list?.size!! > 0) {
                setupUI(list)
                progressLytVisibility(false)

            }
        }
    }

}
