package com.example.challenge2_foodapp.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge2_foodapp.R
import com.example.challenge2_foodapp.adapter.FoodAdapter
import com.example.challenge2_foodapp.data.entity.FoodEntity
import com.example.challenge2_foodapp.data.repository.FoodViewModelFactory
import com.example.challenge2_foodapp.data.sharedpreferences.RecyclerViewSettings
import com.example.challenge2_foodapp.data.sharedpreferences.RecyclerViewViewModel
import com.example.challenge2_foodapp.data.sharedpreferences.RecyclerViewViewModelFactory
import com.example.challenge2_foodapp.data.sharedpreferences.dataStore
import com.example.challenge2_foodapp.databinding.FragmentHomeBinding
import com.example.challenge2_foodapp.ui.activity.detail.DetailActivity
import com.example.challenge2_foodapp.utils.GridSpacingItemDecoration

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodAdapter
    private var whatAppearance = "list"

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(
            this,
            FoodViewModelFactory(requireContext())
        )[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.rvMakanan

        val pref = RecyclerViewSettings.getInstance(requireActivity().application.dataStore)
        val rvViewModel = ViewModelProvider(this, RecyclerViewViewModelFactory(pref)).get(
            RecyclerViewViewModel::class.java
        )

        rvViewModel.rvAppearance().observe(viewLifecycleOwner) { appearance ->
            whatAppearance = appearance

            recyclerView.setHasFixedSize(true)

            // Hapus dekorasi yang ada
            val itemDecorations = recyclerView.itemDecorationCount
            for (i in 0 until itemDecorations) {
                recyclerView.removeItemDecorationAt(0)
            }

            // Hapus adapter lama
            recyclerView.adapter = null

            // Set tampilan yang baru
            if (whatAppearance == "grid") {
                recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                val spacing = resources.getDimensionPixelSize(R.dimen.grid_spacing)
                recyclerView.addItemDecoration(GridSpacingItemDecoration(spacing, true))
            } else {
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
            }

            homeViewModel.food.observe(viewLifecycleOwner) { dataMakanan ->

                if (dataMakanan == null) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.animationView.visibility = View.VISIBLE
                    binding.rvMakanan.visibility = View.GONE
                } else {

                    binding.progressBar.visibility = View.GONE
                    binding.animationView.visibility = View.GONE
                    binding.rvMakanan.visibility = View.VISIBLE

                    adapter = FoodAdapter(dataMakanan, requireContext())
                    adapter.isListView = whatAppearance == "list"
                    binding.rvMakanan.adapter = adapter

                    adapter.setOnItemClickCallback(object : FoodAdapter.OnItemClickCallback {
                        override fun onItemClicked(data: FoodEntity) {
                            sendSelectedFood(data)
                        }
                    })
                }
            }

            updateListButtonImage()
        }

        binding.listButton.setOnClickListener {
            rvViewModel.setRVAppearance(
                if (whatAppearance == "list") "grid" else "list"
            )

            // Update the adapter without recreating it
            adapter.isListView = whatAppearance == "list"
            adapter.notifyDataSetChanged()

            updateListButtonImage()
        }
    }

    private fun sendSelectedFood(data: FoodEntity) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra("food", data)
        startActivity(intent)
    }

    private fun updateListButtonImage() {
        // Ubah gambar pada listButton berdasarkan status isListView
        val imageResource = if (whatAppearance == "list") R.drawable.baseline_list_24 else R.drawable.icons8_grid
        binding.listButton.setImageResource(imageResource)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}