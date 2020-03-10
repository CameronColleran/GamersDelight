package edu.miracosta.cs134.gamersdelight;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import edu.miracosta.cs134.gamersdelight.model.Game;

/**
 * Helper class to provide custom adapter for the <code>Game</code> list.
 */
public class GameListAdapter extends ArrayAdapter<Game> {

    private Context mContext;
    private List<Game> mGamesList;
    private int mResourceId;

    /**
     * Creates a new <code>GameListAdapter</code> given a context, resource id and list of games.
     *
     * @param c The context for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param games The list of games to display
     */
    public GameListAdapter(Context c, int rId, List<Game> games) {
        super(c, rId, games);
        mContext = c;
        mResourceId = rId;
        mGamesList = games;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the Game selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        Game selectedGame = mGamesList.get(pos);

        LinearLayout gameListLinearLayout = view.findViewById(R.id.gameListLinearLayout);
        gameListLinearLayout.setTag(selectedGame);

        TextView gameListNameTextView = view.findViewById(R.id.gameListNameTextView);
        TextView gameListDescriptionTextView = view.findViewById(R.id.gameListDescriptionTextView);
        RatingBar gameListRatingBar = view.findViewById(R.id.gameListRatingBar);
        ImageView gameListImageView = view.findViewById(R.id.gameListImageView);

        gameListNameTextView.setText(selectedGame.getName());
        gameListDescriptionTextView.setText(selectedGame.getDescription());
        gameListRatingBar.setRating(selectedGame.getRating());

        AssetManager am = mContext.getAssets();

        try
        {
            InputStream stream = am.open(selectedGame.getImageName());
            Drawable image = Drawable.createFromStream(stream, selectedGame.getName());
            gameListImageView.setImageDrawable(image);
        }
        catch (IOException e)
        {
            Log.e("Gamers Delight", "Error loading image from assets : ", e);
        }
        //TODO:  Code for getting the view of a list item correctly inflated.
        //TODO:  This should inflate every part of the game_list_item layout
        //TODO:  Be sure to set the tag of the view to its position.

        return view;
    }
}
