package edu.miracosta.cs134.gamersdelight;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

import edu.miracosta.cs134.gamersdelight.model.Game;
import edu.miracosta.cs134.gamersdelight.model.JSONLoader;

public class MainActivity extends AppCompatActivity {


    private List<Game> gamesList;
    private GameListAdapter gamesListAdapter;
    private ListView gamesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try
        {
            gamesList = JSONLoader.loadJSONFromAsset(this);
        }
        catch (IOException e)
        {
            Log.e("Gamers Delight", "Error loading data from JSON file : ", e);
        }

        gamesListView = findViewById(R.id.gameListView);

        gamesListAdapter = new GameListAdapter(this, R.layout.game_list_item, gamesList);

        gamesListView.setAdapter(gamesListAdapter);
        // TODO: Connect the ListView with the layout
        // TODO:  Populate all games list using the JSONLoader
        // TODO:  Create a new ListAdapter connected to the correct layout file and list
        // TODO:  Connect the ListView with the ListAdapter

        for (int i = 0; i < gamesList.size(); i++)
        {
            System.out.println(gamesList.get(i).toString());
        }
    }

    public void viewGameDetails(View view) {

        // TODO: Use an Intent to start the GameDetailsActivity with the data it needs to correctly inflate its views.

        Game game = (Game) view.getTag();

        Intent intent = new Intent(this, GameDetailsActivity.class);

        intent.putExtra("Name", game.getName());
        intent.putExtra("Description", game.getDescription());
        intent.putExtra("Rating", game.getRating());
        intent.putExtra("ImageName", game.getImageName());

        startActivity(intent);
    }

    public void addGame(View view)
    {
        // TODO:  Read information from EditTexts and RatingBar,
        // TODO:  Create a new Game object then add it to the list
        // TODO:  Make sure the list adapter is notified
        // TODO:  Clear all entries the user made (edit text and rating bar)

        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText descriptionEditText = findViewById(R.id.descriptionEditText);
        RatingBar gameRatingBar = findViewById(R.id.gameRatingBar);

        String name = nameEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        float rating = gameRatingBar.getRating();

        gamesList.add(new Game(name, description, rating));

    }



}
