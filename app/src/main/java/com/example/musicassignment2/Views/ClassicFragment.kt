package com.example.musicassignment2.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicassignment2.R
import com.example.musicassignment2.adaptor.ClassicAdaptor
import com.example.musicassignment2.model.classic.ClassicMusic
import com.example.musicassignment2.presenters.ClassicPresenter
import com.example.musicassignment2.presenters.IClassicView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ClassicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

/**
 * THE FRAGMENT IS IMPLEMENTING [IClassicView] to update the UI
 */

class ClassicFragment : Fragment(), IClassicView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: ClassicFragment
    private lateinit var classicAdapter: ClassicAdaptor

    private val presenter : ClassicPresenter by lazy{
        ClassicPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classic, container, false)
    }

    override fun onResume() {
        super.onResume()

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ClassicFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ClassicFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun songsUpdated(classicSongs: List<ClassicMusic>) {
        TODO("Not yet implemented")

    }

    override fun songsUpdatedFromDB(classicSongs: List<ClassicMusic>) {
        TODO("Not yet implemented")
    }

    override fun onError(error: Throwable) {
        TODO("Not yet implemented")
    }
}