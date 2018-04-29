package com.example.ivan.softbalance


import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import com.example.ivan.softbalance.model.WeatherItem
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateActivity
import kotlinx.android.synthetic.main.content.*

class StartActivity : MvpViewStateActivity<WeatherView, WeatherPresenter, WeatherViewState>(),
                    TextWatcher,WeatherView{

    companion object {
        const val STATE = "state"
        const val DATA = "data"
    }

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

        input_search.addTextChangedListener(this)
    }

    private fun restoreData(saved:Bundle?){

        if (saved != null){
            adapter.list = saved.getParcelableArrayList(DATA)
            list.layoutManager.onRestoreInstanceState(saved.getParcelable( STATE))
        }

    }

    private val adapter: WeatherListAdapter = WeatherListAdapter()

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        outState?.putParcelable( STATE,list.layoutManager.onSaveInstanceState())
        outState?.putParcelableArrayList(DATA,adapter.list)
        super.onSaveInstanceState(outState, outPersistentState)
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
