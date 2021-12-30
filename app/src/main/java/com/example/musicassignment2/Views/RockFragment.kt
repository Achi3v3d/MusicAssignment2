package com.example.musicassignment2.Views

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.musicassignment2.MusicApplication
import com.example.musicassignment2.R
import com.example.musicassignment2.adaptor.RockAdapter
import com.example.musicassignment2.databinding.MusicFragmentBinding
import com.example.musicassignment2.model.rock.RockMusic
import com.example.musicassignment2.presenters.IRockView
import com.example.musicassignment2.presenters.RockPresenter
import javax.inject.Inject


class RockFragment : Fragment(),IRockView {

    private lateinit var binding: MusicFragmentBinding
    private lateinit var songsAdapter: RockAdapter

    @Inject
    lateinit var presenter: RockPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MusicApplication.musicComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DeBug","onCreate FrCl")
        presenter.initPresenter(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("DeBug","onCreateView FrCl")
        binding= MusicFragmentBinding.inflate(inflater,container,false)
        songsAdapter= RockAdapter(presenter)
        songsAdapter.stateRestorationPolicy= RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.swipe.setOnRefreshListener(refreshListener())
        // Inflate the layout for this fragment
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun refreshListener(): SwipeRefreshLayout.OnRefreshListener{
        return SwipeRefreshLayout.OnRefreshListener{
            Log.d("DeBug","Refresh")
            presenter.checkNetworkConnection()
            presenter.getMusicFromServer()
            binding.swipe.isRefreshing=false
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
        Log.d("DeBug","onResume FrCl")
        binding.recyclermusic.apply {
            adapter=songsAdapter
            layoutManager = LinearLayoutManager(activity?.baseContext, LinearLayoutManager.VERTICAL,false)
        }
        presenter.checkNetworkConnection()
        presenter.getMusicFromServer()
    }

    override fun onPause() {
        super.onPause()
        Log.d("DeBug","onPause FrCl")
        presenter.stopPreview()
    }




    override fun musicUpdated(rockMusic: List<RockMusic>) {
        songsAdapter.updateSongsRV(rockMusic as MutableList<RockMusic>)
        presenter.saveMusicIntoDatabase(rockMusic)
    }

    override fun musicUpdatedFromDataBase(rockMusic: List<RockMusic>) {
        Toast.makeText(activity, activity?.getString(R.string.noNetworkError), Toast.LENGTH_LONG).show()
        songsAdapter.updateSongsRV(rockMusic as MutableList<RockMusic>)
    }

    override fun onError(error: Throwable) {
        Toast.makeText(activity,error.localizedMessage, Toast.LENGTH_LONG).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ClassicFragment()
    }
}