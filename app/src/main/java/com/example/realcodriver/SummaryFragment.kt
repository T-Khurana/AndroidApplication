package com.example.realcodriver

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.w3c.dom.Text
import java.net.HttpURLConnection
import java.net.URL

class SummaryFragment : Fragment() {

    private lateinit var carInfoCard: CardView
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_summary, container, false)
        carInfoCard = view.findViewById(R.id.carInfoCard)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        val databaseReference = FirebaseDatabase.getInstance().reference.child("Users/${uid}")
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot){
                val userInfo = dataSnapshot.getValue(UserInformation::class.java)
                if (userInfo != null){
                    populateCardView(userInfo)
                }
            }
            override fun onCancelled(databaseError: DatabaseError){
                Log.d("TAG", "onCancelled: ${databaseError.message}")
            }

        })
    }

    private fun populateCardView(userInfo: UserInformation){
        Log.d("INFO", "Car info car Name${userInfo.carName}")
        val carNameTextView = carInfoCard.findViewById<TextView>(R.id.carName)
        carNameTextView.text = userInfo.carName

        Log.d("INFO", "Car info car Year${userInfo.carYear}")
        val carYearTextView = carInfoCard.findViewById<TextView>(R.id.carYear)
        carYearTextView.text = userInfo.carYear
    }

    private fun getCounters(){
        val url = URL("http://localhost:4000/v1/countquery")

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"  // optional default is GET

            println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")

            inputStream.bufferedReader().use {
                it.lines().forEach { line ->
                    println(line)
                }
            }
        }
    }

}