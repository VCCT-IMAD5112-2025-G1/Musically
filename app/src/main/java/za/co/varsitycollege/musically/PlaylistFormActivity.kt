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
    enum class rate {
        One,
        Two,
        Three,
        Four,
        Five,
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_playlist_form)
        binding = ActivityPlaylistFormBinding.inflate(layoutInflater)
        setContentView(binding.root)


        updateSpinner()

        binding.cardviewButton.setOnClickListener {
            updateDatabase()
        }
        binding.backButton.setOnClickListener {
            // Navigate back to the Dashboard activity
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateDatabase() {

        if (binding.editTextQuestion1.text.isNotBlank() and binding.editTextQuestion2.text.isNotBlank() and binding.editTextQuestion4.text.isNotBlank()) {
            dbPlaylist.add(
                MainActivity.DBSong(
                    songTitle = binding.editTextQuestion1.text.toString(),
                    nameArtist = binding.editTextQuestion2.text.toString(),
                    rate = binding.spinnerQuestion3.selectedItemPosition + 1, // Convert spinner position to rate
                    comment = binding.editTextQuestion4.text.toString()
                )
            )

            // Go back to the main activity after adding the song
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        } else {
            binding.errorMsg.visibility = View.VISIBLE
            Toast.makeText(this, "Please enter a valid input", Toast.LENGTH_SHORT).show()
        }


    }


    private fun updateSpinner() {
        // Set up the spinner with the EditConditions enum values
        val spinner = binding.spinnerQuestion3
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
            rate.entries.map { it.name.capitalize() } // Convert enum values to a list of strings
        )
        spinner.setSelection(0) // Set default selection to the first item
    }





}
