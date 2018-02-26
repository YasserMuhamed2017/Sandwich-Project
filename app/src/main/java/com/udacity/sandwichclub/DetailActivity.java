package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    Sandwich sandwich = new Sandwich();
    TextView mPlaceOfOriginTV;
    TextView mDescriptionTV;
    TextView mIngredients;
    TextView mAlsoKnownAsTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        mPlaceOfOriginTV = findViewById(R.id.origin_tv);
        mDescriptionTV = findViewById(R.id.description_tv);
        mIngredients = findViewById(R.id.ingredients_tv);
        mAlsoKnownAsTV = findViewById(R.id.also_known_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        try {
            sandwich = JsonUtils.parseSandwichJson(json);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }
    // populating the extracted data from json on the DetailActivity
    private void populateUI(Sandwich sandwich) {

        // set the location of making this sandwich on its own TextView that extracted from JSON
        mPlaceOfOriginTV.setText(sandwich.getPlaceOfOrigin());
        // set the description of making this sandwich on its own TextView that extracted from JSON
        mDescriptionTV.setText(sandwich.getDescription());
        // set the ingredients of making this sandwich on its own TextView that extracted from JSON
        mIngredients.setText(getStringsFromList(sandwich.getIngredients()));
        // set the other names of this sandwich on its own TextView that extracted from JSON
        mAlsoKnownAsTV.setText(getStringsFromList(sandwich.getAlsoKnownAs()));

    }
    // this method for getting strings from List<String> using StringBuffer
    public String getStringsFromList(List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for ( int i = 0 ; i < list.size() ; i++ ){
            stringBuffer.append(list.get(i));
            if (i != list.size()-1 )
                stringBuffer.append(", ");
        }

        return stringBuffer.toString();
    }
}
