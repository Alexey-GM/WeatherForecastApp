package com.example.weatherforecastapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.databinding.FragmentDetailsBinding
import com.example.weatherforecastapp.databinding.FragmentWeatherBinding
import com.example.weatherforecastapp.domain.model.Weather
import java.io.Serializable

private const val ARG_PARAM = "Weather"
class DetailsFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(weather: Weather) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM, weather)
                }
            }
    }

    private var weather: Weather? = null
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            weather = it.getSerializable(ARG_PARAM) as Weather
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}