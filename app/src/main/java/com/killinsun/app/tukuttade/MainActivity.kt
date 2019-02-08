package com.killinsun.app.tukuttade

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.GridLayout
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TopItemViewHolder.ItemInterface {

    private var okazuArray:ArrayList<Okazu> = arrayListOf<Okazu>()
    private lateinit var auth: FirebaseAuth
    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    //lateinit var mGoogleSignInOptions: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        FirebaseFirestore.setLoggingEnabled(true)

        /*
        top_recycler.layoutManager = GridLayoutManager(this,5)
        top_recycler.adapter = TopItemAdapter(okazuArray, this)
        */

        fab_add.setOnClickListener{
            onClickFAB()
        }


    }

    override fun onStart(){
        super.onStart()

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if(currentUser != null){
            val name = currentUser.displayName
            Log.v("test","${name}")

        }else{
            signIn()
        }
    }

    override fun onClickItem(position: Int) {
        Toast.makeText(this, "Item name: " + okazuArray[position].name + " date: " + okazuArray[position].expireDate, Toast.LENGTH_SHORT ).show()
    }

    private fun onClickFAB(){
        val intent = Intent(this, NewPostActivity::class.java)
        startActivity(intent)
    }


    private fun signIn(){
        val gso:GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.m_default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent:Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        if (requestCode == RC_SIGN_IN && data != null){
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = GoogleSignIn.getSignedInAccountFromIntent(data)
                    .getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)

                auth.signInWithCredential(credential)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.v("test", "Login successful!")
                        } else {
                            Log.v("test", "Login failure")
                        }
                    }

            }catch(e: ApiException){
                Log.e("test","API Exception",e)
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}


