package com.example.myapplication.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.activity.viewModels
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.data.model.Content
import com.example.myapplication.data.network.Resource
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.adapters.ContentListAdapter
import com.example.myapplication.utiliity.GridSpaceLayoutmanager
import com.example.myapplication.utiliity.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val viewModel : MainActivityViewModel by viewModels()
    private val adapterMain = ContentListAdapter(ArrayList<Content>()){

    }

    private var page = 1

    override fun installTheme() {
        // Enable support for Splash Screen API for
        // proper Android 12+ support
//        installSplashScreen()
    }

    override fun setUpViews() {

        lifecycle.addObserver(viewModel)

        val columns = resources.getInteger(R.integer.gallery_columns)
        binding.contentLiist.apply {
            layoutManager = GridLayoutManager(this@MainActivity,columns)
            addItemDecoration(GridSpaceLayoutmanager(columns,40,true))
            adapter = adapterMain

            addOnScrollListener(object : OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager: GridLayoutManager =
                        recyclerView.getLayoutManager() as GridLayoutManager
                    val visibleItemCount: Int = layoutManager.getChildCount()
                    val totalItemCount: Int = layoutManager.getItemCount()
                    val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()
                    if (totalItemCount - visibleItemCount <= firstVisibleItemPosition + 9) {
                        // Load more data
                        page++
                        if (page>3) {
                            toast("You are at the end of this page")
                        } else {
                            viewModel.callApiLogin(page.toString())
                        }
                    }
                }
            })
        }

        viewModel.loginResponse.observe(this){
            when(it.status){
                Resource.Status.LOADING->{

                }
                Resource.Status.SUCCESS -> {
                    adapterMain.updateList(it.data?.page?.contentItems?.content!! as ArrayList<Content>, false)
                }
                Resource.Status.ERROR -> {

                }
            }
        }

        binding.searchBtn.setOnClickListener(){
            if (binding.editSearch.visibility == View.GONE) {
                binding.imageView2.visibility = View.GONE
                binding.editSearch.visibility = View.VISIBLE
                binding.titleTxt.visibility = View.GONE
                binding.searchBtn.setImageResource(R.drawable.search_cancel)
            } else  {
                binding.imageView2.visibility = View.VISIBLE
                binding.titleTxt.visibility = View.VISIBLE
                binding.editSearch.visibility = View.GONE
                binding.searchBtn.setImageResource(R.drawable.search)
            }
        }

        binding.editSearch.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!! == ""){
                    adapterMain.updateListWithMain()
                } else if(s.length>3){
                    adapterMain.filter(s.toString())
                } else {
                    adapterMain.updateListWithMain()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflator = menuInflater
        inflator.inflate(R.menu.searchmenu,menu)

        val searchViewItem: MenuItem = menu!!.findItem(R.id.search_bar)
        val searchView = MenuItemCompat.getActionView(searchViewItem) as SearchView
        searchView.setOnQueryTextListener(object: OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapterMain.filter(query)
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapterMain.filter(newText);
                return false;
            }

        })

        return super.onCreateOptionsMenu(menu)
    }



}