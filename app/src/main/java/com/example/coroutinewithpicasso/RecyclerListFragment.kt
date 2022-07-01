package com.example.coroutinewithpicasso

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutinewithpicasso.Model.RecyclerList
import com.example.coroutinewithpicasso.ViewModel.MainActivityViewModel
import com.example.coroutinewithpicasso.adapter.RecyclerAdapter
import com.example.coroutinewithpicasso.databinding.FragmentRecyclerListBinding


class RecyclerListFragment : Fragment() {

    private lateinit var binding : FragmentRecyclerListBinding
    private lateinit var recyclerAdapter : RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerListBinding.inflate(layoutInflater)
        val view =  binding.root

        initViewModel()
        viewModelProvider()
        return view
    }

    private fun initViewModel(){
        binding.recyclerview.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding.recyclerview.addItemDecoration(decoration)

        recyclerAdapter = RecyclerAdapter()
        binding.recyclerview.adapter = recyclerAdapter
    }

    private fun viewModelProvider(){
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<RecyclerList> {
            if(it != null) {
                recyclerAdapter.setUpdateData(it.items)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            RecyclerListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}