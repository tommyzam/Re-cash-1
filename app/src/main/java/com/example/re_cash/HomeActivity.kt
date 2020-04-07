package com.example.re_cash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*
import com.google.firebase.auth.FirebaseAuth


class HomeActivity : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

      //  var btnLogOut = findViewById<Button>(R.id.btn_signout)

      //  btnLogOut.setOnClickListener {
           // showmessage(view, "Logging Out...")
            //signOut()
      //  }

        //fbAuth.addAuthStateListener {
          //  if (fbAuth.currentUser == null) {
              //  this.finish()
            //}
            // }
            // }
            //fun signOut() {
              //  fbAuth.signOut()

                //}

                //fun showmessage(view: View, message: String){

                // }


                imageButton6.setOnClickListener() {
                   startActivity(Intent(this, Profile::class.java))
                    finish()
                }
            }

    }




