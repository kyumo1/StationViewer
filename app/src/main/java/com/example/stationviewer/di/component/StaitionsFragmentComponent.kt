package com.example.stationviewer.di.component

import com.example.stationviewer.di.module.Module
import com.example.stationviewer.ui.main.StationsFragment
import dagger.Component
import dagger.android.AndroidInjector

@Component(modules = [Module::class])
interface StationsFragmentComponent: AndroidInjector<StationsFragment> {
    override fun inject(fragment: StationsFragment)
}