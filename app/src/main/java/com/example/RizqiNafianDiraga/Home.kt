package com.example.RizqiNafianDiraga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.RizqiNafianDiraga.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
    // menyiapkan variable binding
    private var _binding: FragmentHomeBinding? = null
    // mngeset variable binding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // mengambil dan menampilkan layout
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // menghubungkan Home ke Genrelist
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_Home_to_GenreListFragment)
        }
    }
    // memperbarui objek terikat jika fragment hancur
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}