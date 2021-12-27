package com.example.musicassignment2.presenters

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.musicassignment2.database.MusicDataBase
import com.example.musicassignment2.model.classic.ClassicMusic
import com.example.musicassignment2.rest.MusicApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ClassicPresenter @Inject constructor(
    var context: Context,
    var networkApi: MusicApi
    var connectivityManager: ConnectivityManager,
    var database: MusicDataBase
): IClassicPresenter {

    private var IClassicView : IClassicView? =null
    private val disposables : CompositeDisposable = CompositeDisposable()
    private var isNetworkAvailable = false



    override fun initPresenter(viewContract: IClassicView) {
        TODO("Not yet implemented")
    }

    override fun getMusicFromServer() {
        TODO("Not yet implemented")
        if(isNetworkAvailable){
            val networkDisposable = networkApi
                .getClassic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {songs -> presenterViewContract?.songsUpdated(songs.classicMusics)},
                    {error -> presenterViewContract?.onError(error)}
                )
            disposable.add(networkDisposable)
        }
        else{
            Log.d("tag_ivan","getClassicFromDB")
            getSongsFromDB()
        }
    }

    override fun getMusicFromDB() {
        TODO("Not yet implemented")
    }

    override fun playPreview(previewUrl: String) {
        TODO("Not yet implemented")
    }

    override fun stopPreview() {
        TODO("Not yet implemented")
    }

    override fun saveMusicIntoDatabase(songsList: List<ClassicMusic>) {
        TODO("Not yet implemented")
    }

    override fun checkNetworkConnection() {
        TODO("Not yet implemented")
    }

    override fun destroyPresenter() {
        TODO("Not yet implemented")

    }

}

interface IClassicPresenter {
    fun initPresenter(viewContract:IClassicView)
    fun getMusicFromServer()
    fun getMusicFromDB()
    fun playPreview(previewUrl: String)
    fun stopPreview()
    fun saveMusicIntoDatabase(songsList:List<ClassicMusic>)
    fun checkNetworkConnection()
    fun destroyPresenter()

}

interface  IClassicView{
    fun songsUpdated(classicSongs:List<ClassicMusic>)
    fun songsUpdatedFromDB(classicSongs:List<ClassicMusic>)
    fun onError(error:Throwable)
}