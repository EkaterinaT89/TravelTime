package ru.wb.treveltime.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.wb.treveltime.R
import ru.wb.treveltime.activity.adapter.FlightAdapter
import ru.wb.treveltime.activity.adapter.FlightInteractionListener
import ru.wb.treveltime.activity.dto.Flight
import ru.wb.treveltime.activity.fragment.FlightFragment.Companion.showFlight
import ru.wb.treveltime.activity.viewModel.FlightViewModel
import ru.wb.treveltime.databinding.FragmentListFlightsBinding

class ListFlightsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentListFlightsBinding.inflate(
            inflater,
            container,
            false
        )

        val flightViewModel: FlightViewModel by viewModels(
            ownerProducer = ::requireParentFragment
        )

        val flightAdapter = FlightAdapter(object : FlightInteractionListener {
            override fun onLike(flight: Flight) {

            }

            override fun onChoose(flight: Flight) {
                findNavController().navigate(R.id.flightFragment,
                    Bundle().apply
                    {
                        showFlight = flight
                    })
            }

        })

        flightViewModel.listFlights.observe(viewLifecycleOwner, { flights ->
            flightAdapter.submitList(flights)
        })

        flightViewModel.dataState.observe(viewLifecycleOwner, { state ->
            with(binding) {
                progress.isVisible = state.loading
                if (state.error) {
                    serverError.isVisible = state.error
                    serverErrorButton.setOnClickListener {
                        flightViewModel.tryAgain()
                        serverError.visibility = View.GONE
                    }
                }
            }
        })

        binding.listFlights.adapter = flightAdapter

        return binding.root
    }

}