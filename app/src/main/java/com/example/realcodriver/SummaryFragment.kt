package com.example.realcodriver

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SummaryFragment : Fragment() {

    private lateinit var carInfoCard: CardView
    private lateinit var auth: FirebaseAuth
    private lateinit var count: String


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

        val card = view.findViewById<TextView>(R.id.rapid_accel_count)
        lifecycleScope.launch {
            val response = withContext(Dispatchers.IO) {
                val connection = URL("http://10.0.2.2:4000/v1/countquery").openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val inputStream = connection.inputStream
                    convertStreamToString(inputStream)
                } else {
                    null
                }
            }

            response?.let {
                card.text = response
            } ?: run {
                Toast.makeText(context, "Failed to get response", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    private fun convertStreamToString(inputStream: InputStream): String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String? = bufferedReader.readLine()
        while (line != null) {
            stringBuilder.append(line).append("\n")
            line = bufferedReader.readLine()
        }
        bufferedReader.close()
        return stringBuilder.toString()
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

//    private fun getCounters(): String{
//        var response = StringBuilder()
//        lifecycleScope.launch {
//            val url = URL("http://10.0.2.2:4000/v1/countquery")
//
//            val connection = url.openConnection() as HttpURLConnection
//            connection.requestMethod = "GET"
//
//            val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream))
//            var inputLine: String?
//            while (bufferedReader.readLine().also { inputLine = it } != null) {
//                response.append(inputLine)
//            }
//            bufferedReader.close()
//
//            Log.d("HTTP Request Response", response.toString())
//        }
//        return response.toString()
//    }
}