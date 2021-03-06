package com.example.re_cash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*


class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        btn_sign_up_login.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

        btn_log_in.setOnClickListener {
            doLogin()
        }

    }

    private fun doLogin() {
        if(tv_username1.text.toString().isEmpty()) {
           tv_username1.error="Please enter email"
           tv_username1.requestFocus()
           return
    }

        if(!Patterns.EMAIL_ADDRESS.matcher(tv_username1.text.toString()).matches()){
            tv_username1.error = "Please enter valid email"
            tv_username1.requestFocus()
            return
        }

        if(tv_password1.text.toString().isEmpty()){
            tv_password1.error = "Please enter password"
            tv_password1.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(tv_username1.text.toString(), tv_password1.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(
                        baseContext, "Login Failed",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)

                }

            }
    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?){

        if(currentUser !=null){
            if(currentUser.isEmailVerified) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }else{
                Toast.makeText(
                    baseContext, "Please verify email address.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            }else{
                Toast.makeText(
                    baseContext, "Login Failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}
