package com.example.realcodriver

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.model.GradientColor
import org.w3c.dom.Text

class DataVisFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_data_vis, container, false)
        val analysis : String? = arguments?.getString("selectedAnalytics")
        val trip : String? = arguments?.getString("selectedTrip")
        val graph1 : ImageView = view.findViewById(R.id.imageViewGraph)
        val graph2 : ImageView = view.findViewById(R.id.imageViewGraph2)
        val graph3 : ImageView = view.findViewById(R.id.imageViewGraph3)
        val text1 : TextView = view.findViewById(R.id.textViewTop)
        val text2 : TextView = view.findViewById(R.id.textViewBottom)
        val text3 : TextView = view.findViewById(R.id.textViewBottom2)
        Log.d("TAG", "Selected Analysis: ${arguments?.getString("selectedAnalytics")}")
        Log.d("TAG", "Selected Trip: ${arguments?.getString("selectedTrip")}")

        if (trip == "04-March-2023-23-22.csv"){
            if (analysis == "GPS") {Log.d("TAG", "GPS")}
            else if (analysis == "Fuel Metrics"){
                text1.text = "Engine (RPM)"
                graph1.setImageResource(R.drawable.engine_4th_march)
                text2.text = "Engine Load (%)"
                graph2.setImageResource(R.drawable.engine_load_4th_march)
            }
            else if (analysis == "Speed"){
                text1.text = "Speed (km/h)"
                graph1.setImageResource(R.drawable.speed_4th_march)
                text2.text = "Throttle(%)"
                graph2.setImageResource(R.drawable.throttle_4th_march)
            }
            else if (analysis == "Steering"){
                text1.text = "Steering Wheel Angle (deg)"
                graph1.setImageResource(R.drawable.steering_wheel_angle_4th_march)
                text2.text = "Roll Rate (deg)"
                graph2.setImageResource(R.drawable.roll_rate_4th_march)
                text3.text = "Lateral Acceleration (m/s^2)"
                graph3.setImageResource(R.drawable.lateral_acc_4th_march)
            }
            else if (analysis == "Acceleration"){
                text1.text = "Acceleration (m/s^2)"
                graph1.setImageResource(R.drawable.acceleration_4th_march)
                text2.text = "Pitch Rate (deg)"
                graph2.setImageResource(R.drawable.pitch_rate_4th_march)
            }
            else if(analysis == "Braking"){
                text1.text = "Pitch Rate (deg)"
                graph1.setImageResource(R.drawable.pitch_rate_4th_march)
            }

        }else if(trip == "17-February-2023-21-27.csv"){
            if (analysis == "GPS") {Log.d("TAG", "GPS")}
            else if (analysis == "Fuel Metrics"){
                text1.text = "Engine (RPM)"
                graph1.setImageResource(R.drawable.engine_17th_feb)
//                text2.text = "Engine Load (%)"
//                graph2.setImageResource(R.drawable.engine)
            }
            else if (analysis == "Speed"){
                text1.text = "Speed (km/h)"
                graph1.setImageResource(R.drawable.speed_17th_feb)
                text2.text = "Throttle(%)"
                graph2.setImageResource(R.drawable.throttle_17th_feb)
            }
            else if (analysis == "Steering"){
                text1.text = "Steering Wheel Angle (deg)"
                graph1.setImageResource(R.drawable.steering_wheel_angle_17th_feb)
                text2.text = "Roll Rate (deg)"
                graph2.setImageResource(R.drawable.roll_rate_17th_feb)
                text3.text = "Lateral Acceleration (m/s^2)"
                graph3.setImageResource(R.drawable.lateral_accel_17th_feb)
            }
            else if (analysis == "Acceleration"){
                text1.text = "Acceleration (m/s^2)"
                graph1.setImageResource(R.drawable.acceleration_17th_feb)
                text2.text = "Pitch Rate (deg)"
                graph2.setImageResource(R.drawable.pitch_rate_17th_feb)
            }
            else if(analysis == "Braking"){
                text1.text = "Pitch Rate (deg)"
                graph1.setImageResource(R.drawable.pitch_rate_17th_feb)
            }

        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}