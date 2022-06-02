package ru.wb.treveltime.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.wb.treveltime.databinding.FragmentCardFlightBinding

class CardFlightFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentCardFlightBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

}