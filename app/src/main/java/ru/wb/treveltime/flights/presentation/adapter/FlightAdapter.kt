package ru.wb.treveltime.flights.presentation.adapter

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.wb.treveltime.R
import ru.wb.treveltime.flights.data.dataModel.Flight
import ru.wb.treveltime.flights.domain.utils.PriceUtils
import ru.wb.treveltime.databinding.FragmentCardFlightBinding
import ru.wb.treveltime.flights.domain.utils.dateFormatList
import ru.wb.treveltime.flights.domain.utils.differenceBetweenDates

interface FlightInteractionListener {
    fun onLike(flight: Flight)
    fun onChoose(flight: Flight)
}

class FlightAdapter(private val listener: FlightInteractionListener) :
    ListAdapter<Flight, FlightViewHolder>(FlightDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val binding =
            FragmentCardFlightBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return FlightViewHolder(listener, binding)
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

}

class FlightViewHolder(
    private val listener: FlightInteractionListener,
    private val binding: FragmentCardFlightBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(flight: Flight) {

        binding.apply {
            departureDate.text = flight.startDate.dateFormatList()
            departureCity.text = flight.startCity
            arrivalCity.text = flight.endCity
            arrivalDate.text = flight.endDate.dateFormatList()
            price.text = PriceUtils().pricePresents(flight.price)

            flightLast.text = root.context.getString(
                R.string.chronodays,
                differenceBetweenDates(flight.startDate, flight.endDate)
            )

            likeButton.isChecked = flight.liked

            likeButton.setOnClickListener {
                listener.onLike(flight)
                ObjectAnimator.ofFloat(
                    binding.likeButton,
                    View.ROTATION,
                    0F, 360F
                ).start()
            }

            oneFlight.setOnClickListener {
                listener.onChoose(flight)
            }

        }

    }

}

class FlightDiffCallback : DiffUtil.ItemCallback<Flight>() {
    override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem.searchToken == newItem.searchToken
    }

    override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem == newItem
    }

}