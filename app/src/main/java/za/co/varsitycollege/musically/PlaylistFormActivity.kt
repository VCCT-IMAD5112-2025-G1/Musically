package za.co.varsitycollege.musically

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import za.co.varsitycollege.musically.databinding.ActivityPlaylistFormBinding

class PlaylistFormActivity : AppCompatActivity() {

    // ViewBinding instance for the dashboard layout.
    lateinit var binding: ActivityPlaylistFormBinding
    // Enum class for rating songs
    enum class rate{
        1,
        2,
        3,
        4,
        5
    }

    var count = 0
    // Database for the playlist
    private lateinit var playlist : MainActivity.DBSong

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_playlist_form)
        binding = ActivityEditValuesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weather = (intent.getSerializableExtra("weather") as? SplashActivity.DBWeather)!!


        updateUi()
        updateSpinner()

        binding.cardviewButton.setOnClickListener {
            updateDatabase()
        }
        binding.backButton.setOnClickListener {
            // Navigate back to the Dashboard activity
            intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }
    private fun updateUi(){
        binding.nameOfCity.text = weather.city
        binding.nameOfDay.text = weather.days[count]
    }

    private fun updateDatabase() {

        if (binding.editTextNumberHighTemp.text.isNotBlank() and binding.editTextNumberLowTemp.text.isNotBlank()) {
            weather.highTemp[count] = binding.editTextNumberHighTemp.text.toString().toInt()
            weather.lowTemp[count] = binding.editTextNumberLowTemp.text.toString().toInt()
            weather.condition[count] = binding.spinnerCondition.selectedItem.toString()
            binding.errorMsg.visibility = View.INVISIBLE

            count++

            checkStatus()

        } else {
            binding.errorMsg.visibility = View.VISIBLE
            Toast.makeText(this, "Please enter a valid temperature", Toast.LENGTH_SHORT).show()
        }


    }


    private fun updateSpinner() {
        // Set up the spinner with the EditConditions enum values
        val spinner = binding.spinnerCondition
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
            EditConditions.entries.toTypedArray()
        )
        spinner.setSelection(0) // Set default selection to the first item
    }

    private fun checkStatus() {
        if (count >= weather.days.size) {
            Toast.makeText(this, "Weather data updated successfully!", Toast.LENGTH_SHORT).show()
            saveWeatherData()
            intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        else{
            // Clear the input fields after updating
            clearInputFields()
            // Update the UI with the new day
            updateUi()
        }
    }

    private fun clearInputFields() {
        binding.editTextNumberHighTemp.text.clear()
        binding.editTextNumberLowTemp.text.clear()
        binding.spinnerCondition.setSelection(0) // Reset spinner to default selection
    }

    private fun saveWeatherData() {
        // Save the updated weather data to the database
        // This is a placeholder for actual saving logic
        databaseWeather[weather.city] = weather
    }

}
