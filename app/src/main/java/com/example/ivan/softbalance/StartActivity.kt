package com.example.ivan.softbalance


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.ivan.softbalance.model.WeatherItem
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateActivity
import kotlinx.android.synthetic.main.content.*

class StartActivity : MvpViewStateActivity<WeatherView, WeatherPresenter, WeatherViewState>(),
                    TextWatcher,WeatherView, View.OnClickListener{
    override fun onClick(v: View?) {
        presenter.search(input_search.text.toString())
    }

    companion object {
        const val STATE = "state"
        const val DATA = "data"
    }

    private val adapter: WeatherListAdapter = WeatherListAdapter()

    override fun onNewViewStateInstance() {

    }

    override fun createPresenter() = WeatherPresenter()

    override fun createViewState() = WeatherViewState()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        restoreData(savedInstanceState)
        search_button.setOnClickListener(this)
        //input_search.addTextChangedListener(this)
    }

    private fun restoreData(saved:Bundle?){

        if (saved != null){
            adapter.list = saved.getParcelableArrayList(DATA)
            list.layoutManager.onRestoreInstanceState(saved.getParcelable(STATE))
        }

    }



    override fun onSaveInstanceState(outState: Bundle?) {
        Log.d("Test","onSaveInstanceState")
        outState?.putParcelable(STATE,list.layoutManager.onSaveInstanceState())
        outState?.putParcelableArrayList(DATA,adapter.list)
        Log.d("Test","save list is null - "+(adapter.list == null))
        super.onSaveInstanceState(outState)
    }

    override fun afterTextChanged(s: Editable?) {
        presenter.search(input_search.text.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun showNewData(data:ArrayList<WeatherItem>){
        adapter.list = data
    }
}
