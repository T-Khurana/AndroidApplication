package com.example.realcodriver

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.cardview.widget.CardView

class TripsFragment : Fragment() {


    private var fileNames = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_trips, container, false)

        val bundle = Bundle()
        val linearLayout = view.findViewById<LinearLayout>(R.id.linearLayoutTrips)

        fileNames = mutableListOf("22-February-2023-14-55.csv", "23-February-2023-15-05.csv", "24-February-2023-16-13.csv")

        Log.d("TAG", "${arguments?.getString("selectedAnalytics")}")

        for (name in fileNames){
            val cardViewLayout = inflater.inflate(R.layout.card_trips, linearLayout, false)
            val dateTextView = cardViewLayout.findViewById<TextView>(R.id.tripDate)
            val timeTextView = cardViewLayout.findViewById<TextView>(R.id.tripTime)
            val components = name.split("-")

            cardViewLayout.setOnClickListener {
                bundle.putString("selectedTrip", name)
                bundle.putString("selectedAnalytics", arguments?.getString("selectedAnalytics"))
                val dataVisFragment = DataVisFragment()
                dataVisFragment.arguments = bundle
                val fragmentManager = parentFragmentManager
                fragmentManager.beginTransaction().replace(R.id.frameLayout, dataVisFragment).addToBackStack(null).commit()

            }

            val dateString = getString(R.string.date_format, components[0], components[1], components[2])
            dateTextView.text = dateString

            val timeString = getString(R.string.time_format, components[3], components[4].removeSuffix(".csv"))
            timeTextView.text = timeString

            linearLayout.addView(cardViewLayout)
        }
        return view
    }
}