package com.jamun.vinsol.ui.activities

import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.jamun.vinsol.R
import com.jamun.vinsol.jetpack.entites.ImageModel
import com.jamun.vinsol.ui.adapter.GridAdapter
import com.jamun.vinsol.utils.BD
import com.jamun.vinsol.utils.BaseActivity
import com.jamun.vinsol.utils.ItemAnimation
import com.jamun.vinsol.variables.ApiKeys
import com.jamun.vinsol.variables.OnImageClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : BaseActivity() {

    private var modelList: MutableList<ImageModel>? = null
    private var revertList: MutableList<ImageModel> = mutableListOf()
    private lateinit var adapter: GridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()
        initializeData()
        initializeRecyclerView()
    }

    override fun setToolbar() {
        super.setToolbar()
        setSupportActionBar(findViewById<Toolbar>(R.id.id_app_bar))
    }

    override fun initializeData() {
        super.initializeData()
        modelList = mutableListOf(
            ImageModel(0, ApiKeys.URL_IMAGE_ONE),
            ImageModel(1, ApiKeys.URL_IMAGE_TWO),
            ImageModel(2, ApiKeys.URL_IMAGE_THREE),
            ImageModel(3, ApiKeys.URL_IMAGE_FOUR),
            ImageModel(4, ApiKeys.URL_IMAGE_FIVE),
            ImageModel(5, ApiKeys.URL_IMAGE_SIX),
            ImageModel(6, ApiKeys.URL_IMAGE_SEVEN),
            ImageModel(7, ApiKeys.URL_IMAGE_EIGHT),
            ImageModel(8, ApiKeys.URL_IMAGE_NINE),
            ImageModel(9, ApiKeys.URL_IMAGE_ONE),
            ImageModel(10, ApiKeys.URL_IMAGE_TWO),
            ImageModel(11, ApiKeys.URL_IMAGE_THREE),
            ImageModel(12, ApiKeys.URL_IMAGE_FOUR),
            ImageModel(13, ApiKeys.URL_IMAGE_FIVE),
            ImageModel(14, ApiKeys.URL_IMAGE_SIX),
            ImageModel(15, ApiKeys.URL_IMAGE_SEVEN),
            ImageModel(16, ApiKeys.URL_IMAGE_EIGHT),
            ImageModel(17, ApiKeys.URL_IMAGE_NINE)
        )
    }

    override fun initializeRecyclerView() {
        super.initializeRecyclerView()
        id_recycler_view.layoutManager = GridLayoutManager(this, 3)
        id_recycler_view.itemAnimator = ItemAnimation()
        adapter = GridAdapter(this@MainActivity, modelList!!, object : OnImageClickListener {
            override fun onClick(model: ImageModel, adapterPosition: Int) {
                model.adapterPosition = adapterPosition
                revertList.add(0, model)
                adapter.notifyAdapterItemRemoved(adapterPosition)
                invalidateOptionsMenu()
            }
        })
        id_recycler_view.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu!!.findItem(R.id.id_menu_sub).isVisible = modelList!!.isNotEmpty()
        menu.findItem(R.id.id_menu_revert).isVisible = revertList.isNotEmpty()
        BD.setBadgeCount(
            this,
            revertList.size,
            R.id.ic_badge_revert,
            menu.findItem(R.id.id_menu_revert).icon as LayerDrawable
        )
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.id_menu_sub -> {
                val model = modelList!![0]
                model.adapterPosition = 0
                revertList.add(0, model)
                adapter.notifyAdapterItemRemoved(0)
                invalidateOptionsMenu()
            }
            else -> {
                adapter.notifyAdapterItemInserted(revertList[0])
                revertList.removeAt(0)
                invalidateOptionsMenu()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        closeEverything()
    }

    override fun closeEverything() {
        super.closeEverything()
    }
}
