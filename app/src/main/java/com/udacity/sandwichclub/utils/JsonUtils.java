package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    // Declaration of constant attributes which are defined in JSON data file
    private static final String ROOT = "name" ;
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    // this method for parsing data from string-array in strings res file
    public static Sandwich parseSandwichJson(String json) throws JSONException {
         List<String> alsoKonwnSandwiches ;
         List<String> listOfIngredients ;

        JSONObject sandwichData = new JSONObject(json);
        JSONObject nameOfSandwiches = sandwichData.getJSONObject(ROOT);

        String mainName = nameOfSandwiches.optString (MAIN_NAME);
        JSONArray alsoKnownAs = nameOfSandwiches.getJSONArray(ALSO_KNOWN_AS);
        alsoKonwnSandwiches = convertJSONArrayToArrayList(alsoKnownAs);

        String placeOfOrigin = sandwichData.optString (PLACE_OF_ORIGIN);

        String description = sandwichData.optString (DESCRIPTION);

        String image = sandwichData.optString (IMAGE);

        JSONArray ingredients = sandwichData.getJSONArray(INGREDIENTS);

        listOfIngredients = convertJSONArrayToArrayList(ingredients);

        Sandwich sandwich = new Sandwich(mainName , alsoKonwnSandwiches , placeOfOrigin , description , image,listOfIngredients);
        return sandwich;
    }
    private static List<String> convertJSONArrayToArrayList (JSONArray jsonArray) throws JSONException {
        List<String> JSONArrayDataExtracted = new ArrayList<>();
        if (jsonArray.length() != 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                String currentName = jsonArray.optString (i);
                JSONArrayDataExtracted.add(currentName);
            }
        }
        return JSONArrayDataExtracted;
    }
}
