package za.co.varsitycollege.musically

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import za.co.varsitycollege.musically.databinding.ActivityMainBinding
import java.io.Serializable
import java.util.Arrays

// Database to store the playlist songs
var dbPlaylist = ArrayList<MainActivity.DBSong>()

class MainActivity : AppCompatActivity() {

    // ViewBinding instance for the dashboard layout.
    lateinit var binding: ActivityMainBinding
    // Data class to represent a song in the playlist
    data class DBSong(
        val songTitle: String,
        val nameArtist: String,
        val rate: Int,
        val comment: String,
    ) : Serializable

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)

        // Enable edge-to-edge display for modern Android UI
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }

        getSongs()
        // Navigate to the Playlist activity
        binding.addPlaylistButton.setOnClickListener{
            // Navigate to the Dashboard activity
            val intent = Intent(this, PlaylistFormActivity::class.java)
            startActivity(intent)
        }
        // Navigate to the Detailed activity
        binding.navigateDetailedActButton.setOnClickListener {
            // Navigate to the Dashboard activity
            val intent = Intent(this, DetailedActivity::class.java)
            startActivity(intent)
        }
        // Close button to finish the activity
        binding.closeButton.setOnClickListener {
            finish()
        }

    }
    // Function to get the average rating of all songs in the playlist
    private fun getSongs() {
        dbPlaylist.add(DBSong(
            songTitle = "Song 1",
            nameArtist = "Artist 1",
            rate = 5,
            comment = "Great song!"
        ))
        dbPlaylist.add(DBSong(
            songTitle = "Song 2",
            nameArtist = "Artist 2",
            rate = 4,
            comment = "Nice beat!"
        ))
        dbPlaylist.add(DBSong(
            songTitle = "Song 3",
            nameArtist = "Artist 3",
            rate = 3,
            comment = "Good rhythm!"
        ))
        dbPlaylist.add(DBSong(
            songTitle = "Song 4",
            nameArtist = "Artist 4",
            rate = 2,
            comment = "Not bad!"
        ))
    }


}