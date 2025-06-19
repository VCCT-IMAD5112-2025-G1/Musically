package za.co.varsitycollege.musically

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import za.co.varsitycollege.musically.databinding.ActivityDetailedBinding
import za.co.varsitycollege.musically.databinding.ActivityMainBinding

class DetailedActivity : AppCompatActivity() {

    // ViewBinding instance for the dashboard layout.
    lateinit var binding: ActivityDetailedBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_detailed)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.avgCard.setOnClickListener {
            // Handle click event for the average card
            getAvgofRate()
        }
        binding.backButton.setOnClickListener {
            // Navigate back to the MainActivity
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.songsCard.setOnClickListener {
            // Handle click event for the songs card
            getSongsList()
        }

    }

    private fun getAvgofRate() {
        // Calculate the average rating of all songs in the playlist using for loop
        var count = 0
        var sum = 0
        var avg = 0.0
        if (dbPlaylist.isNotEmpty()) {
            for (song in dbPlaylist) {
                sum += song.rate // Accessing the rate property of each song
                count++
            }
            avg = sum.toDouble() / count.toDouble()
            // Calculate the average rating
            binding.textAvg.text = "Avg Rating:\n %.2f".format(avg)
        } else {
            // Show error by Toast if the playlist is empty
            Toast.makeText(this, "There's not songs", Toast.LENGTH_SHORT).show()

        }
    }

    private fun getSongsList() {
        // Display the list of songs in the playlist using for loop
        if (dbPlaylist.isNotEmpty()) {
            for ((index, song) in dbPlaylist.withIndex()) {
                // Update CardView with song details
                Log.v("DetailedActivity", "Displaying song at index: ${song.songTitle}")
                findViewById<CardView>(getViewId("songCard", index + 1))?.apply {
                    visibility = CardView.VISIBLE
                    findViewById<TextView>(getViewId("textSongName", index + 1)).text = song.songTitle
                    findViewById<TextView>(getViewId("textArtistName", index + 1)).text = song.nameArtist
                    findViewById<TextView>(getViewId("textComment", index + 1)).text = song.comment
                    // Display the star rating based on the song's rate
                    val starRating = song.rate
                    if (starRating == 1) {
                        // Display all the sart iconStar2Box1
                        findViewById<ImageView>(getViewId("iconStar1Box", index + 1)).visibility =
                            ImageView.VISIBLE
                    } else if (starRating == 2) {
                        // Display two star icons
                        findViewById<ImageView>(getViewId("iconStar1Box", index + 1)).visibility =
                            ImageView.VISIBLE
                        findViewById<ImageView>(getViewId("iconStar2Box", index + 1)).visibility =
                            ImageView.VISIBLE
                    } else if (starRating == 3) {
                        // Display three star icons
                        findViewById<ImageView>(getViewId("iconStar1Box", index + 1)).visibility =
                            ImageView.VISIBLE
                        findViewById<ImageView>(getViewId("iconStar2Box", index + 1)).visibility =
                            ImageView.VISIBLE
                        findViewById<ImageView>(getViewId("iconStar3Box", index + 1)).visibility =
                            ImageView.VISIBLE
                    } else if (starRating == 4) {
                        // Display four star icons
                        findViewById<ImageView>(getViewId("iconStar1Box", index + 1)).visibility =
                            ImageView.VISIBLE
                        findViewById<ImageView>(getViewId("iconStar2Box", index + 1)).visibility =
                            ImageView.VISIBLE
                        findViewById<ImageView>(getViewId("iconStar3Box", index + 1)).visibility =
                            ImageView.VISIBLE
                        findViewById<ImageView>(getViewId("iconStar4Box", index + 1)).visibility =
                            ImageView.VISIBLE
                    } else if (starRating == 5) {
                        // Display five star icons
                        findViewById<ImageView>(getViewId("iconStar1Box", index + 1)).visibility =
                            ImageView.VISIBLE
                        findViewById<ImageView>(getViewId("iconStar2Box", index + 1)).visibility =
                            ImageView.VISIBLE
                        findViewById<ImageView>(getViewId("iconStar3Box", index + 1)).visibility =
                            ImageView.VISIBLE
                        findViewById<ImageView>(getViewId("iconStar4Box", index + 1)).visibility =
                            ImageView.VISIBLE
                        findViewById<ImageView>(getViewId("iconStar5Box", index + 1)).visibility =
                            ImageView.VISIBLE
                    }

                }
            }


        }

    }

    private fun getViewId(prefix: String, index: Int): Int {
        val resourceName = "${prefix}${index}"
        val id = resources.getIdentifier(resourceName, "id", packageName)
        Log.d("MainActivity", "Looking for ID: $resourceName, found: $id")
        return id
    }

}