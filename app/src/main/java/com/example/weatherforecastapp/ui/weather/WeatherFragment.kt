package com.example.weatherforecastapp.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecastapp.App
import com.example.weatherforecastapp.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val adapter = WeatherAdapter()
    private val weatherModel: WeatherViewModel by viewModels { MainViewModelFactory(App.repository) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeViewModel()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun observeViewModel() {
        weatherModel.weather.observe(viewLifecycleOwner) { weather ->
            adapter.submitList(weather)
        }
    }

    private fun initRecyclerView() {
        binding.rvWeather.adapter = adapter
        binding.rvWeather.layoutManager = LinearLayoutManager(requireContext())
        binding.rvWeather.addItemDecoration(createItemDecoration())
    }

    private fun createItemDecoration(): DividerItemDecoration {
        return DividerItemDecoration(
            binding.rvWeather.context,
            LinearLayoutManager.VERTICAL
        )
    }
}