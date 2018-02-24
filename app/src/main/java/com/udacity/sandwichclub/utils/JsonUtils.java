package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static List<String> alsoKonwnSandwiches = new ArrayList<>();
    private static List<String> listOfIngredients =new ArrayList<>();
    private static Sandwich sandwich ;

    // this method for parsing data from string-array in strings res file
    public static Sandwich parseSandwichJson(String json) throws JSONException {

        JSONObject sandwichData = new JSONObject(json);
        JSONObject nameOfSandwiches = sandwichData.getJSONObject("name");
        String mainName = nameOfSandwiches.getString("mainName");
        JSONArray alsoKnownAs = nameOfSandwiches.getJSONArray("alsoKnownAs");

        if (alsoKnownAs.length() != 0) {
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                String currentName = alsoKnownAs.getString(i);
                alsoKonwnSandwiches.add(currentName);
            }
        }

        String placeOfOrigin = sandwichData.getString("placeOfOrigin");

        String description = sandwichData.getString("description");

        String image = sandwichData.getString("image");

        JSONArray ingredients = sandwichData.getJSONArray("ingredients");
        if (ingredients.length() != 0) {
            for (int i = 0; i < ingredients.length(); i++) {
                String ingredient = ingredients.getString(i);
                listOfIngredients.add(ingredient);
            }
        }
        sandwich = new Sandwich(mainName , alsoKonwnSandwiches , placeOfOrigin , description , image,listOfIngredients);
        return sandwich;
    }
}
