package com.example.stationviewer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stationviewer.data.StationOutputData
import com.example.stationviewer.usecases.IGetStationsUseCase
import io.reactivex.disposables.CompositeDisposable

class StationsViewModel(private val getStationsUseCase: IGetStationsUseCase, private val lineCode: Int): ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val station: MutableLiveData<List<StationOutputData>> by lazy {
        MutableLiveData<List<StationOutputData>>().also {
            loadStations()
        }
    }

    fun getStations(): LiveData<List<StationOutputData>> {
        return station
    }

    private fun loadStations() {
        //TODO unexpected stream end
        dispose()
        val disposable = getStationsUseCase.get(lineCode).subscribe({
            station.postValue(it)
        },{
            loadStations()
        })
        compositeDisposable.add(disposable)
    }

    fun dispose() {
        compositeDisposable.clear()
    }
}