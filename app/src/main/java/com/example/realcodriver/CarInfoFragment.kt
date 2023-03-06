package com.example.realcodriver

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import com.example.realcodriver.databinding.FragmentCarInfoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class CarInfoFragment : Fragment() {

    private lateinit var binding : FragmentCarInfoBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference : DatabaseReference

    private lateinit var firstNameTextView : TextView
    private lateinit var lastNameTextView : TextView
    private lateinit var carNameTextView : TextView
    private lateinit var carYearTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCarInfoBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_car_info, container, false)

        firstNameTextView = view.findViewById(R.id.etFirstName)
        lastNameTextView = view.findViewById(R.id.etLastName)
        carNameTextView = view.findViewById(R.id.etCarName)
        carYearTextView = view.findViewById(R.id.etCarModel)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid

        databaseReference = FirebaseDatabase.getInstance().reference.child("Users/${uid}")
        databaseReference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userInfo = dataSnapshot.getValue(UserInformation::class.java)
                if (userInfo != null){
                    populateView(userInfo)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("TAG", "onCancelled: ${databaseError.message}")
            }
        })
    }

    private fun populateView(userInfo: UserInformation){
        firstNameTextView.text = userInfo.firstName
        lastNameTextView.text = userInfo.lastName
        carNameTextView.text = userInfo.carName
        carYearTextView.text = userInfo.carYear
    }

}