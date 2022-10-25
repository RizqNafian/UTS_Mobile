package com.example.RizqiNafianDiraga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.RizqiNafianDiraga.databinding.FragmentMovieListBinding

// menampilkan RecyclerView dengan tombol pencarian
class MovieListFragment : Fragment() {
    // mengatur akses varible dan arah pencarian
    companion object {
        val LETTER = "letter"
        val SEARCH_PREFIX = "https://www.google.com/search?q="
    }
    // menyiapkan variable binding
    private var _binding: FragmentMovieListBinding? = null
    // mengeset variable binding
    private val binding get() = _binding!!
    private lateinit var letterId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // mengambil nilai LETTER
        arguments?.let {
            letterId = it.getString(LETTER).toString()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // mengambil dan menampilkan layout
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = MovieAdapter(letterId, requireContext())
        // menambahkan DividerItemDecoration diantara item
        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }
    // memperbarui objek terikat jika fragment hancur
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}