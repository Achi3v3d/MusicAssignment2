package com.example.musicassignment2.presenters

import android.content.Context
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.musicassignment2.R
import com.example.musicassignment2.database.MusicDataBase
import com.example.musicassignment2.model.classic.ClassicMusic
import com.example.musicassignment2.rest.MusicApi
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject

class ClassicPresenter @Inject constructor(
    var context: Context,
    var networkApi: MusicApi,
    var connectivityManager: ConnectivityManager,
    var database: MusicDataBase
): IClassicPresenter {

    private var classicPresenterViewContract : IClassicView? =null
    private val disposable by lazy {
        CompositeDisposable()
    }
    private var isNetworkAvailable = false

private lateinit var mediaPlayer: MediaPlayer
/**
 * In the initialization the MediaPlayer for
 */

    override fun initPresenter(viewContract: IClassicView) {

        classicPresenterViewContract = viewContract
        mediaPlayer = MediaPlayer()
    }

    override fun getMusicFromServer() {

        if(isNetworkAvailable){
            val networkDisposable = networkApi
                .getClassic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {songs -> classicPresenterViewContract?.musicUpdated(songs.classicMusics)},
                    {error -> classicPresenterViewContract ?.onError(error)}
                )
            disposable.add(networkDisposable)
        }
        else{
            Log.d("DeBug","getClassicFromDB")
            getMusicFromDB()
        }
    }

    override fun getMusicFromDB() {

        val disposableDB=database.getMusic()
            .getClassicAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {song -> classicPresenterViewContract?.musicUpdatedFromDataBase(song)},
                {error -> classicPresenterViewContract?.onError(error)}
            )
        disposable.add(disposableDB)
    }

    override fun playPreview(previewUrl: String) {

        val disposablePlay = playPreviewBackThread(previewUrl)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.d("DeBug", "Playing preview") },
                { error -> classicPresenterViewContract?.onError(error) }
            )
        disposable.add(disposablePlay)
    }

    override fun stopPreview() {

        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.reset()
        }
    }

    /**
     * Play preview
     */


    private fun playPreviewBackThread(previewUrl: String): Completable {
            Log.d("DeBug",previewUrl)
            if (mediaPlayer.isPlaying){
                mediaPlayer.stop()
                mediaPlayer.reset()
            }
            else{
                mediaPlayer.reset()
            }
            if(isNetworkAvailable){
                return try {
                    Log.d("DeBug","inside try mediaplayer")
                    mediaPlayer.setDataSource(previewUrl)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                    Completable.complete()

                } catch (e: Exception) {
                    //Log.d("DeBug",e.localizedMessage)
                    Completable.error(Throwable(context.getString(R.string.playPreviewError)))
                }
            }
            else{
                return Completable.error(Throwable(context.getString(R.string.noNetworkError)))
            }

        }







    override fun saveMusicIntoDatabase(songsList: List<ClassicMusic>) {

        val databaseDisposable= this.database.getMusic()
            .insertClassicSong(songsList)
            .subscribeOn(Schedulers.io())
            .subscribe (
                { Log.d("DeBug","Classic Data Saved")},
                { Log.d("DeBug",it?.localizedMessage?:context.getString(R.string.databaseError))}
            )
        disposable.add(databaseDisposable)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun checkNetworkConnection() {

        isNetworkAvailable = getActiveNetworkCapabilities()?.let {
            it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    it.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        } ?: false
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun getActiveNetworkCapabilities(): NetworkCapabilities? {
        return connectivityManager.activeNetwork.let {
            connectivityManager.getNetworkCapabilities(it)
        }
    }
    override fun destroyPresenter() {
        disposable.clear()
        classicPresenterViewContract=null
        mediaPlayer.release()
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
    fun musicUpdated(classicMusic:List<ClassicMusic>)
    fun musicUpdatedFromDataBase(classicMusic:List<ClassicMusic>)
    fun onError(error:Throwable)
}