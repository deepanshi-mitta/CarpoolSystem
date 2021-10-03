package com.example.carpoolsystem

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.util.*

class AddRideScreen : AppCompatActivity() {
    private val SOURCE_ERROR = "invalid source format"
    private val DESTINATION_ERROR = "invalid destination format"
    private val TIME_ERROR = "invalid time format"
    private lateinit var source: EditText
    private lateinit var destination: EditText
    private lateinit var time: EditText
    private lateinit var addRide: TextView
    private lateinit var addDetails: Button
    private lateinit var tvDatePicker:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ride_screen)
        source = findViewById(R.id.editTextSource)
        destination = findViewById(R.id.editTextDestination)
        time = findViewById(R.id.editTextTime)
        addRide = findViewById(R.id.textViewAddRide)
        addDetails = findViewById(R.id.buttonSubmit)
        try {
            tvDatePicker = findViewById(R.id.date)
        }
        catch( e:NullPointerException ){

        }
            val cal = Calendar.getInstance()
            val myYear = cal.get(Calendar.YEAR)
            val myMonth = cal.get(Calendar.MONTH)
            val day = cal.get((Calendar.DAY_OF_MONTH))
            tvDatePicker.setOnClickListener {
                val datePickerDialog = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        tvDatePicker.text = "Date:" + dayOfMonth + "/" + (month + 1) + "/" + year

                    },
                    myYear,
                    myMonth,
                    day
                )
                datePickerDialog.show()
            }




            source.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int, count: Int, after: Int
                ) {

                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int, before: Int, count: Int
                ) {
                    s?.apply {
                        if (RideUtils.isValidSource(s.toString())) {
                            source.error = null
                        } else {
                            source.error = SOURCE_ERROR
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })

            destination.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    p0: CharSequence?,
                    p1: Int, p2: Int, p3: Int
                ) {
                }

                override fun onTextChanged(
                    p0: CharSequence?,
                    p1: Int, p2: Int, p3: Int
                ) {
                    p0?.apply {
                        if (RideUtils.isValidDestination(p0.toString())) {
                            destination.error = null
                        } else {
                            destination.error = DESTINATION_ERROR
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}
            })


            time.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    p0: CharSequence?,
                    p1: Int, p2: Int, p3: Int
                ) {
                }

                override fun onTextChanged(
                    p0: CharSequence?,
                    p1: Int, p2: Int, p3: Int
                ) {
                    p0?.apply {
                        if (RideUtils.isValidTime(p0.toString())) {
                            time.error = null
                        } else {
                            time.error = TIME_ERROR
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}
            })


        }




}

