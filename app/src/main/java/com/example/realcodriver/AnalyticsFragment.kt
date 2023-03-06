package com.example.realcodriver

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.imageview.ShapeableImageView
class AnalyticsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_analytics, container, false)
        val bundle = Bundle()

        // get references to the included card layouts
        val card1 = rootView.findViewById<CardView>(R.id.ecg_card)
        val card2 = rootView.findViewById<CardView>(R.id.gps_card)
        val card3 = rootView.findViewById<CardView>(R.id.speed_card)
        val card4 = rootView.findViewById<CardView>(R.id.fuel_metrics_card)
        val card5 = rootView.findViewById<CardView>(R.id.acceleration_card)
        val card6 = rootView.findViewById<CardView>(R.id.braking_card)
        val card7 = rootView.findViewById<CardView>(R.id.steering_card)


        // get references to the views inside each card layout
        val image1 = card1.findViewById<ShapeableImageView>(R.id.shapeableImage)
        val text1 : TextView  = card1.findViewById(R.id.cardHeaderTitle)
        val image2 = card2.findViewById<ShapeableImageView>(R.id.shapeableImage)
        val text2 : TextView = card2.findViewById(R.id.cardHeaderTitle)
        val image3 = card3.findViewById<ShapeableImageView>(R.id.shapeableImage)
        val text3 : TextView = card3.findViewById(R.id.cardHeaderTitle)
        val image4 = card4.findViewById<ShapeableImageView>(R.id.shapeableImage)
        val text4 : TextView  = card4.findViewById(R.id.cardHeaderTitle)
        val image5 = card5.findViewById<ShapeableImageView>(R.id.shapeableImage)
        val text5 : TextView = card5.findViewById(R.id.cardHeaderTitle)
        val image6 = card6.findViewById<ShapeableImageView>(R.id.shapeableImage)
        val text6 : TextView = card6.findViewById(R.id.cardHeaderTitle)
        val image7 = card7.findViewById<ShapeableImageView>(R.id.shapeableImage)
        val text7 : TextView  = card7.findViewById(R.id.cardHeaderTitle)

        // make changes to the views as needed
        image1.setImageResource(R.drawable.ecg_heart)
        text1.setText(R.string.heartRate)
        image2.setImageResource(R.drawable.gps)
        text2.setText(R.string.gps)
        image3.setImageResource(R.drawable.speed)
        text3.setText(R.string.speed)
        image4.setImageResource(R.drawable.fuel)
        text4.setText(R.string.fuelMetrics)
        image5.setImageResource(R.drawable.acceleration)
        text5.setText(R.string.acceleration)
        image6.setImageResource(R.drawable.braking)
        text6.setText(R.string.braking)
        image7.setImageResource(R.drawable.steering)
        text7.setText(R.string.steering)

        card1.setOnClickListener{
            bundle.putString("selectedAnalytics", "Heart Rate")
            val tripsListFragment = TripsFragment()
            tripsListFragment.arguments = bundle
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().replace(R.id.frameLayout, tripsListFragment).addToBackStack(null).commit()
        }
        card2.setOnClickListener{
            bundle.putString("selectedAnalytics", "GPS")
            val tripsListFragment = TripsFragment()
            tripsListFragment.arguments = bundle
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().replace(R.id.frameLayout, tripsListFragment).addToBackStack(null).commit()
        }
        card3.setOnClickListener{
            bundle.putString("selectedAnalytics", "Speed")
            val tripsListFragment = TripsFragment()
            tripsListFragment.arguments = bundle
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().replace(R.id.frameLayout, tripsListFragment).addToBackStack(null).commit()
        }
        card4.setOnClickListener{
            bundle.putString("selectedAnalytics", "Fuel Metrics")
            val tripsListFragment = TripsFragment()
            tripsListFragment.arguments = bundle
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().replace(R.id.frameLayout, tripsListFragment).addToBackStack(null).commit()
        }
        card5.setOnClickListener{
            bundle.putString("selectedAnalytics", "Acceleration")
            val tripsListFragment = TripsFragment()
            tripsListFragment.arguments = bundle
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().replace(R.id.frameLayout, tripsListFragment).addToBackStack(null).commit()
        }
        card6.setOnClickListener{
            bundle.putString("selectedAnalytics", "Braking")
            val tripsListFragment = TripsFragment()
            tripsListFragment.arguments = bundle
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().replace(R.id.frameLayout, tripsListFragment).addToBackStack(null).commit()
        }
        card7.setOnClickListener{
            bundle.putString("selectedAnalytics", "Steering")
            val tripsListFragment = TripsFragment()
            tripsListFragment.arguments = bundle
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().replace(R.id.frameLayout, tripsListFragment).addToBackStack(null).commit()
        }

        return rootView
    }
}