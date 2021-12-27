package com.example.musicassignment2.presenters

import com.example.musicassignment2.model.pop.PopMusic

/**
mvp presenter for handeling rock music
 */
class RockPresenter {
}

interface IRock{

}
interface IRockPresenter{
    fun initPresenter(viewContract:IRockView)
    fun getSongsFromServer()
    fun getSongsFromDB()
    fun playPreview(previewUrl: String)
    fun stopPreview()
    fun saveSongsIntoDatabase(songsList:List<PopMusic>)
    fun checkNetworkConnection()
    fun destroyPresenter()
}

interface IRockView{
    fun songsUpdated(popSongs:List<PopMusic>)
    fun songsUpdatedFromDB(popSongs:List<PopMusic>)
    fun onError(error:Throwable)
}