package com.strikelines.app.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.gson.GsonBuilder
import com.strikelines.app.*
import com.strikelines.app.domain.models.Chart
import com.strikelines.app.domain.models.Charts
import com.strikelines.app.ui.shopadapter.ShopAdapter
import com.strikelines.app.ui.shopadapter.ShopListener
import kotlinx.android.synthetic.main.recycler_list_fragment.*

abstract class PurchaseSqliteDbFilesFragment : Fragment() {

    companion object {
        const val TITLE = 0
    }

    private val gson by lazy { GsonBuilder().setLenient().create() }
    private val url = "https://strikelines.com/api/charts/?key=A3dgmiOM1ul@IG1N=*@q"
    private val chartsList = mutableListOf<Chart>()

    lateinit var recycleView: RecyclerView
    lateinit var adapter: ShopAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

//        val activity = activity
        val view = inflater.inflate(R.layout.recycler_list_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleView = view.findViewById(R.id.recycler_view)
        adapter = ShopAdapter(listener)
        recycleView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
    }

    override fun onResume() {
        super.onResume()
    }

    val listener: ShopListener? = object : ShopListener {
        override fun onDetailsClicked(item: Chart) {
            openDetailsFragment(item)
        }

        override fun onDownloadClicked(url: String) {
            openBrowser(url)
        }
    }

    fun openDetailsFragment(item: Chart) {
        //todo open details view for selected item
    }

    fun openBrowser(url: String) {
        startActivity(AndroidUtils.getIntentForBrowser(url))
    }

    val cardListener = object : OnRequestResultListener {
        override fun onRequest(status: Boolean) =
                if (status) progress_bg.visibility = View.VISIBLE
                else progress_bg.visibility = View.GONE

        override fun onResult(result: String) {
            onRequestResult(result = result)
        }
    }

    fun requestChartsFromApi() {
        GetRequestAsync(url, cardListener).execute()


    }

    private fun onRequestResult(result: String?) {
        Log.i("Fragment", result)
//        chartsList.addAll(parseJson(result))
//        adapter.setData(chartsList)
    }

    private fun parseJson(response: String?){//: List<Chart> {

        if (response != null) {
//            val charts: Charts = gson.fromJson(response, Charts::class.java)
//            return charts.charts
        } //else return emptyList()

    }


}