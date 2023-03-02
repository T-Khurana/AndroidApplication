package com.example.realcodriver

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

//    private lateinit var mGoogleSignInClient: GoogleSignInClient
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.login_page)
//
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken("335730825699-2b978t22ktgs233c341mjn8pc8bp5p9d.apps.googleusercontent.com")
//            .requestEmail()
//            .build()
//
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
//
//        val googleLoginButton = findViewById<Button>(R.id.google_signIn)
//        googleLoginButton.setOnClickListener {
//            signIn()
//        }
//    }
//
//    private fun signIn() {
//        val signInIntent = mGoogleSignInClient.signInIntent
//        startActivityForResult(
//            signInIntent, RC_SIGN_IN
//        )
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == RC_SIGN_IN) {
//            val task =
//                GoogleSignIn.getSignedInAccountFromIntent(data)
//            handleSignInResult(task)
//        }
//    }
//
//    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
//        try {
////            val account = completedTask.getResult(
////                ApiException::class.java
////            )
//            // Signed in successfully
////            val googleId = account?.id ?: ""
////            Log.i("Google ID",googleId)
////            val googleFirstName = account?.givenName ?: ""
////            Log.i("Google First Name", googleFirstName)
////            val googleLastName = account?.familyName ?: ""
////            Log.i("Google Last Name", googleLastName)
////            val googleEmail = account?.email ?: ""
////            Log.i("Google Email", googleEmail)
////            val googleProfilePicURL = account?.photoUrl.toString()
////            Log.i("Google Profile Pic URL", googleProfilePicURL)
////            val googleIdToken = account?.idToken ?: ""
////            Log.i("Google ID Token", googleIdToken)
//            val intent = Intent(this@LoginActivity, MainActivity::class.java)
//            startActivity(intent)
//        } catch (e: ApiException) {
//            // Sign in was unsuccessful
//            Log.e(
//                "failed code=", e.statusCode.toString()
//            )
//        }
//    }
//
//    companion object {
//        const val RC_SIGN_IN = 9001
//
//
//}

    lateinit var mGoogleSignInClient: GoogleSignInClient
    private val Req_Code:Int=123
    private var firebaseAuth= FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()
         // getting the value of gso inside the GoogleSigninClient
        mGoogleSignInClient=GoogleSignIn.getClient(this,gso)
        // initialize the firebaseAuth variable
        firebaseAuth= FirebaseAuth.getInstance()

        var Signin = findViewById<Button>(R.id.google_signIn)
        Signin.setOnClickListener{ view: View? ->
            signInGoogle()
        }
    }

    private fun signInGoogle(){

        val signInIntent:Intent=mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent,Req_Code)
    }
    // onActivityResult() function : this is where we provide the task and data for the Google Account
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==Req_Code){
            val task:Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }
    // handleResult() function -  this is where we update the UI after Google signin takes place
    private fun handleResult(completedTask: Task<GoogleSignInAccount>){
        try {
            val account: GoogleSignInAccount? =completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e:ApiException){
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show()
        }
    }
    // UpdateUI() function - this is where we specify what UI updation are needed after google signin has taken place.
    private fun UpdateUI(account: GoogleSignInAccount){
        val credential= GoogleAuthProvider.getCredential(account.idToken,null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {task->
            if(task.isSuccessful) {
                //SavedPreference.setEmail(this,account.email.toString())
                //SavedPreference.setUsername(this,account.displayName.toString())
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                //finish()
            }
        }
    }
//    override fun onStart() {
//        super.onStart()
////        if(GoogleSignIn.getLastSignedInAccount(this)!=null){
////            startActivity(Intent(this, MainActivity::class.java))
////            finish()
////        }
//        startActivity(Intent(this, MainActivity::class.java))
//        //finish()
//    }
}