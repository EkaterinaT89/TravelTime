package ru.wb.treveltime.flights.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.wb.treveltime.flights.data.dataModel.Flight
import ru.wb.treveltime.flights.domain.utils.FlightArg
import ru.wb.treveltime.flights.domain.utils.PriceUtils
import ru.wb.treveltime.databinding.FragmentFlightDetailsBinding
import ru.wb.treveltime.flights.domain.utils.dateFormatList
import ru.wb.treveltime.flights.presentation.viewModel.FlightDetailsViewModel

@AndroidEntryPoint
class FlightDetailsFragment : Fragment() {

    private val detailsViewModel: FlightDetailsViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    companion object {
        var Bundle.showFlight: Flight? by FlightArg
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentFlightDetailsBinding.inflate(
            inflater,
            container,
            false
        )

        arguments?.showFlight?.let { flight: Flight ->
            with(binding) {
                departureDate.text = flight.startDate.dateFormatList()
                departureCity.text = flight.startCity
                arrivalCity.text = flight.endCity
                arrivalDate.text = flight.endDate.dateFormatList()
                price.text = PriceUtils().pricePresents(flight.price)
                arrivalCityCode.text = flight.endCityCode
                departureCityCode.text = flight.startCityCode

                likeButton.isChecked = flight.liked

                    likeButton.setOnClickListener {
                        detailsViewModel.like(flight)
                    }
                }

            }

        return binding.root
    }

}