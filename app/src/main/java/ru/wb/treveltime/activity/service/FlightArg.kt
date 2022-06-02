package ru.wb.treveltime.activity.service

import android.os.Bundle
import ru.wb.treveltime.activity.dto.Flight
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object FlightArg : ReadWriteProperty<Bundle, Flight?> {

    override fun getValue(thisRef: Bundle, property: KProperty<*>): Flight? =
        thisRef.getParcelable(property.name)

    override fun setValue(thisRef: Bundle, property: KProperty<*>, value: Flight?) {
        thisRef.putParcelable(property.name, value)
    }

}