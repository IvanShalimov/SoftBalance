package com.example.ivan.softbalance.ui


import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.example.ivan.softbalance.App
import com.example.ivan.softbalance.R
import com.example.ivan.softbalance.presenter.WeatherPresenter
import com.example.ivan.softbalance.model.WeatherItem
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateActivity
import kotlinx.android.synthetic.main.content.*

class StartActivity : MvpViewStateActivity<WeatherView, WeatherPresenter, WeatherViewState>(),
                     WeatherView, View.OnClickListener{

    override fun onClick(v: View?) {
        viewState.state = list.layoutManager.onSaveInstanceState()
        presenter.search(input_search.text.toString())
    }

    private val adapter: WeatherListAdapter by lazy { WeatherListAdapter() }

    private val dialog:MaterialDialog by lazy {
        MaterialDialog.Builder(this)
                .title(R.string.wait)
                .content(R.string.work_doing)
                .progress(true, 0)
                .show()
    }

    override fun onNewViewStateInstance() {
        viewState.state = list.layoutManager.onSaveInstanceState()
    }

    //Гавнокод
    override fun createPresenter() = (applicationContext as App).component.getPresenter()

    override fun createViewState() = WeatherViewState()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        //шило на мыло
        viewState.saveScreenData(adapter.list,list.layoutManager.onSaveInstanceState())
        super.onSaveInstanceState(outState)
    }

    override fun showNewData(data:ArrayList<WeatherItem>,state: Parcelable?){
        adapter.list = data
        list.layoutManager.onRestoreInstanceState(viewState.state)
    }

    override fun showProgressDialog(flag:Boolean) {
        if (flag) dialog.show() else dialog.dismiss()
    }
}
