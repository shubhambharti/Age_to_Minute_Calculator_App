package com.example.agetominutecalculator
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView?=null
    private var currentDate: TextView?=null
    private var tvInMinutes: TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        currentDate = findViewById(R.id.currentDate)
        tvInMinutes = findViewById(R.id.tvInMinutes)
        btnDatePicker .setOnClickListener {
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year= myCalender.get(Calendar.YEAR)
        val month= myCalender.get(Calendar.MONTH)
        val day= myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate?.text = selectedDate
                val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate= sdf.parse(selectedDate)
                val time = Calendar.getInstance().time
                val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val current = formatter.format(time)
                currentDate?.text = current
                theDate?.let {
                    val selectedDateInMin= theDate.time / 60000
                    val currDate= sdf. parse(sdf.format(System.currentTimeMillis()))
                    currDate?.let {
                        val currentDateInMinutes = currDate.time/60000
                        val differenceInMinutes = currentDateInMinutes-selectedDateInMin
                        tvInMinutes?.text = "$differenceInMinutes"
                    }
                }
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate= System.currentTimeMillis()-86400000
        dpd.show()
    }
}