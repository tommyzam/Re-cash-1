package com.example.re_cash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*

class Profile : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        auth = FirebaseAuth.getInstance()

        back_btn.setOnClickListener {
            val intent = Intent(baseContext, HomeActivity::class.java)
            startActivity(intent)
        }


        btn_change_password.setOnClickListener {
            changePassword()
        }
    }

    private fun changePassword() {

        if (et_current_password.text.isNotEmpty() &&
            et_new_password.text.isNotEmpty() &&
            et_confirm_password.text.isNotEmpty()
        ) {

            if(et_new_password.text.toString().equals(et_confirm_password.text.toString())) {

                val user = auth.currentUser
                if (user !=null&&user.email!= null ){
                    val credential = EmailAuthProvider
                        .getCredential(user.email!!, et_current_password.text.toString())

// Prompt the user to re-provide their sign-in credentials
                    user.reauthenticate(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(this, "Re-Authintication success", Toast.LENGTH_SHORT)
                                    .show()

                                user.updatePassword(et_new_password.text.toString())
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(this, "Passowrd Changed Successfully", Toast.LENGTH_SHORT)
                                                .show()
                                            auth.signOut()
                                            startActivity(Intent(this, LoginActivity::class.java))
                                            finish()
                                        }
                                    }

                            } else {
                                Toast.makeText(this, "Re-Authintication failed.", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                }else{
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()
                }

            }else{
                Toast.makeText(this,"Password mismatching.",Toast.LENGTH_SHORT)
                    .show()
            }

            }else{
            Toast.makeText(this,"Please enter all the fields.",Toast.LENGTH_SHORT)
                .show()
        }
    }
}
