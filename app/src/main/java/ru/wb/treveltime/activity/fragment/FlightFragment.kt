package ru.wb.treveltime.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.wb.treveltime.activity.dto.Flight
import ru.wb.treveltime.activity.service.FlightArg
import ru.wb.treveltime.activity.service.PriceService
import ru.wb.treveltime.databinding.FragmentFlightBinding

class FlightFragment : Fragment() {

    companion object {
        var Bundle.showFlight: Flight? by FlightArg
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentFlightBinding.inflate(
            inflater,
            container,
            false
        )

        arguments?.showFlight?.let { flight: Flight ->
            with(binding) {
                departureDate.text = flight.startDate
                departureCity.text = flight.startCity
                arrivalCity.text = flight.endCity
                arrivalDate.text = flight.endDate
                price.text = PriceService().pricePresents(flight.price)
                arrivalCityCode.text = flight.endCityCode
                departureCityCode.text = flight.startCityCode

                likeButton.isChecked = flight.liked

                if (flight.liked) {
                    likeButton.setOnClickListener {
                        flight.liked = false
                    }
                } else {
                    likeButton.setOnClickListener {
                        flight.liked = true
                    }
                }

            }
        }

        return binding.root
    }

}