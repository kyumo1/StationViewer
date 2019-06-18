package com.example.stationviewer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stationviewer.data.StationOutputData
import com.example.stationviewer.usecases.IGetStationsUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import kotlin.properties.Delegates

class StationsViewModel @Inject constructor(private val getStationsUseCase: IGetStationsUseCase) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val station: MutableLiveData<List<StationOutputData>> by lazy {
        MutableLiveData<List<StationOutputData>>().also {
            loadStations(code)
        }
    }
    private var code: Int by Delegates.notNull()

    fun getStations(lineCode: Int): LiveData<List<StationOutputData>> {
        code = lineCode
        return station
    }

    private fun loadStations(lineCode: Int) {
        //TODO unexpected stream end
        dispose()
        val disposable = getStationsUseCase.get(lineCode).subscribe({
            station.postValue(it)
        }, {
            loadStations(lineCode)
        })
        compositeDisposable.add(disposable)
    }

    fun dispose() {
        compositeDisposable.clear()
    }
}