package com.example.sciflare

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {


    var UserCreaateResponseModelList = ArrayList<UserCreaateResponseModel>()
    var userdataList = ArrayList<UserDataModel>()
    private lateinit var Name_EditText: EditText
    private lateinit var Email_EditText: EditText
    private lateinit var Mobile_EditText: EditText
    private lateinit var gender_spinner: Spinner
    private lateinit var Register_Button: Button
    private lateinit var Viewmap_Button: Button
    private lateinit var viewusers_Button: Button
    private lateinit var selectedGender: String
    private lateinit var name: String
    private lateinit var email: String
    private lateinit var mobile: String
    private lateinit var userDao: UserDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userDao = MyAppDatabase.getDatabase(this).userDao()


        Name_EditText = findViewById(R.id.Name_EditText)
        Email_EditText = findViewById(R.id.Email_EditText)
        Mobile_EditText = findViewById(R.id.Mobile_EditText)
        gender_spinner = findViewById(R.id.gender_spinner)
        Register_Button = findViewById(R.id.Register_Button)
        viewusers_Button = findViewById(R.id.viewusers_Button)
        Viewmap_Button = findViewById(R.id.Viewmap_Button)

        val genders = resources.getStringArray(R.array.genders_array)
        val adapter = GenderAdapter(this, genders)

        gender_spinner.adapter = adapter


        gender_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                selectedGender = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }



        Register_Button.setOnClickListener {
            name = Name_EditText.text.toString()
            email = Email_EditText.text.toString()
            mobile = Mobile_EditText.text.toString()
            createuser(name, email, mobile, selectedGender)
        }

        Viewmap_Button.setOnClickListener {
            val intent = Intent(this, ShowMap_Activity::class.java)
            startActivity(intent)
        }


        viewusers_Button.setOnClickListener {
            val intent = Intent(this, Details::class.java)
            startActivity(intent)
        }


    }


    private fun getuseroverallData() {

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
        progressDialog.show()


        val call: Call<List<UserDataModel>> = ApiClient.getClient.getusersoveralllist()

        call.enqueue(object : Callback<List<UserDataModel>> {

            override fun onResponse(
                call: Call<List<UserDataModel>>?,
                response: Response<List<UserDataModel>>?
            ) {

                userdataList.clear();
                userdataList.addAll(response!!.body()!!)

                userdataList.forEach { user ->
                    val data_teml = UserModel(
                        _id = user._id,
                        name = user.name,
                        email = user.email,
                        mobile = user.email,
                        gender = user.gender
                    )
                    GlobalScope.launch {
                        userDao.insert(data_teml)
                    }

                    println("User123: ${user.name}, ${user.email}, ${user.email}, ${user.gender}")
                }
                progressDialog.dismiss()


                showSimplePopup("HURRAY!!", "USER REGISTERED SUCCESSFULLY..")


            }


            override fun onFailure(call: Call<List<UserDataModel>>?, t: Throwable?) {
                progressDialog.dismiss()

                Toast.makeText(applicationContext, "onfailure", Toast.LENGTH_SHORT).show()

            }

        })

    }


    private fun createuser(name: String, email: String, mobile: String, selectedGender: String) {
        val userData = UserrequestData(name, email, mobile, selectedGender)

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val call = ApiClient.getClient.createUser(userData)
        call.enqueue(object : Callback<UserCreaateResponseModel> {
            override fun onResponse(
                call: Call<UserCreaateResponseModel>,
                response: Response<UserCreaateResponseModel>
            ) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    getuseroverallData()

                    progressDialog.dismiss()

                } else {
                    Log.e(TAG, "Unsuccessful response: ${response.code()}")
                }


            }


            override fun onFailure(call: Call<UserCreaateResponseModel>, t: Throwable) {
                Log.e(TAG, "API call failed", t)
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "API call failed", Toast.LENGTH_SHORT).show()
            }
        })
    }


    fun showSimplePopup(title: String, message: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}



