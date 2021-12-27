package com.example.musicassignment2.presenters

import com.example.musicassignment2.model.pop.PopMusic

class PopPresenter {
}

interface IPopPresenter{
    fun initPresenter(viewContract:IPopView)
    fun getSongsFromServer()
    fun getSongsFromDB()
    fun playPreview(previewUrl: String)
    fun stopPreview()
    fun saveSongsIntoDatabase(songsList:List<PopMusic>)
    fun checkNetworkConnection()
    fun destroyPresenter()
}

interface IPopView{
    fun songsUpdated(popSongs:List<PopMusic>)
    fun songsUpdatedFromDB(popSongs:List<PopMusic>)
    fun onError(error:Throwable)
}