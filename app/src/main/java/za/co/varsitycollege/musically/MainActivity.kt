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

var databasePlaylist = ArrayList<MainActivity.DBSong>()

class MainActivity : AppCompatActivity() {

    // ViewBinding instance for the dashboard layout.
    lateinit var binding: ActivityMainBinding

    data class DBSong(
        val songTitle: String,
        val nameArtist: String,
        val rate: Int,
        val comment: String,
    ) : Serializable

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


//        getSongs()
        binding.closeButton.setOnClickListener {finish()}
//        binding.addPlaylistButton.setOnClickListener{
//            // Navigate to the Dashboard activity
//            val intent = Intent(this, Dashboard::class.java)
//            startActivity(intent)
//        }
//        binding.Head2MainPage.setOnClickListener {
//            // Navigate to the Dashboard activity
//            val intent = Intent(this, Dashboard::class.java)
//            startActivity(intent)
//        }

    }

    private fun getSongs() {
        databasePlaylist.add(DBSong(
            songTitle = "Song 1",
            nameArtist = "Artist 1",
            rate = 5,
            comment = "Great song!"
        ))
        databasePlaylist.add(DBSong(
            songTitle = "Song 2",
            nameArtist = "Artist 2",
            rate = 4,
            comment = "Nice beat!"
        ))
        databasePlaylist.add(DBSong(
            songTitle = "Song 3",
            nameArtist = "Artist 3",
            rate = 3,
            comment = "Good rhythm!"
        ))
        databasePlaylist.add(DBSong(
            songTitle = "Song 4",
            nameArtist = "Artist 4",
            rate = 2,
            comment = "Not bad!"
        ))
    }


}