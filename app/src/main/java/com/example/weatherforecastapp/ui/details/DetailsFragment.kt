package com.example.weatherforecastapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.databinding.FragmentDetailsBinding
import com.example.weatherforecastapp.domain.model.Weather
import com.example.weatherforecastapp.utils.getDayOfWeek

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
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        binding.tvCity.text = weather?.city
        binding.tvTime.text = weather?.details?.first()?.datetime?.split(" ")?.last()?.removeRange(5..7)
        binding.tvToday.text = getDayOfWeek(weather!!.date)
        binding.tvDateTime.text = weather?.date
        val temp = weather?.details?.first()?.temp
        binding.tvTemperature.text = binding.root.context.getString(R.string.celsius_details, temp)
        binding.tvMain.text = weather?.details?.first()?.main
        val description = weather?.details?.first()?.description?.split(" ")
        val secondWord = description?.getOrNull(0) ?: ""
        val thirdWord = description?.getOrNull(1) ?: ""
        val fourthWord = description?.getOrNull(2) ?: ""
        binding.tvDescription.text = binding.root.context.getString(
            R.string.description,
            secondWord, thirdWord, fourthWord
        )
        val windSpeed = weather?.details?.first()?.wind.toString()
        binding.tvWindSpeed.text = binding.root.context.getString(R.string.wind_value, windSpeed)
        val pressure = weather?.details?.first()?.pressure.toString()
        binding.tvPressureValue.text =
            binding.root.context.getString(R.string.pressure_value, pressure)
        val humidity = weather?.details?.first()?.humidity.toString()
        binding.tvHumidityValue.text =
            binding.root.context.getString(R.string.humidity_value, humidity)
    }
}