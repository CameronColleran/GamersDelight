package edu.miracosta.cs134.gamersdelight;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

public class GameDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        // TODO:  Use the Intent object sent from MainActivity to populate the Views on
        // TODO:  this layout, including the TextViews, RatingBar and ImageView with the Game details.

        Intent intent = getIntent();

        TextView gameDetailsNameTextView = findViewById(R.id.gameDetailsNameTextView);
        TextView gameDetailsDescriptionTextView = findViewById(R.id.gameDetailsDescriptionTextView);
        RatingBar gameDetailsRatingBar = findViewById(R.id.gameDetailsRatingBar);
        ImageView gameDetailsImageView = findViewById(R.id.gameDetailsImageView);

        String name = intent.getStringExtra("Name");
        String description = intent.getStringExtra("Description");
        float rating = intent.getFloatExtra("Rating", 0.0f);
        String imageName = intent.getStringExtra("ImageName");

        gameDetailsNameTextView.setText(name);
        gameDetailsDescriptionTextView.setText(description);
        gameDetailsRatingBar.setRating(rating);

        AssetManager am = this.getAssets();

        try
        {
            InputStream stream = am.open(imageName);
            Drawable image = Drawable.createFromStream(stream, imageName);
            gameDetailsImageView.setImageDrawable(image);
        }
        catch (IOException e)
        {
            Log.e("Gamers Delight", "Error loading image from assets : ", e);
        }
    }
}
