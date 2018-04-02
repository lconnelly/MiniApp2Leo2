package com.example.celiachen.lectureweek3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leoconnelly on 3/17/18.
 */

public class SearchActivity extends AppCompatActivity {

    Button searchButton;
    Context mContext;
    Spinner mDietSpinner;
    Spinner mTimeSpinner;
    Spinner mServingsSpinner;

    public String SpinnerSearchResultDiet;
    public String SpinnerSearchResultTime;
    public String SpinnerSearchResultServings;
    public ArrayList<String> spinnerSearchResult = new ArrayList<String>();
    public ArrayList<String> dietLableList = new ArrayList<String>();
    private Spinner dietDropdown;
    private Spinner servingDropdown;
    private Spinner prepTimeDropdown;
    private static List<String> StringsDiet = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        mContext = this;
        searchButton = findViewById(R.id.searchButton);


        final ArrayList<Recipe> RecipeList = Recipe.getRecipesFromFile("recipes.json", this);


        //This is going to have to take an algorithmn of the three results and then display a speicifed list based off of that.

        //DIET spinner
        this.StringsDiet.add("blank");
        for (Recipe Re : RecipeList) {
            if(!this.StringsDiet.contains(Re.label)) {
                this.StringsDiet.add(Re.label);
            }

        }
        //get the spinner from the xml.
        dietDropdown = findViewById(R.id.DietDropdown);
        //create a list of items for the spinner.
        String[] items = {"blank", "Low-Sodium","Low-Carb","Balanced","Medium-Carb","Low-Fat","Vegetarian","High-Carb"};

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, StringsDiet);
        //set the spinners adapter to the previously created one.
        dietDropdown.setAdapter(adapter);

        System.out.println(SpinnerSearchResultDiet);
        spinnerSearchResult.add(SpinnerSearchResultDiet);


        //Servings spinner
        //get the spinner from the xml.
        servingDropdown = findViewById(R.id.ServingDropdown);
        //create a list of items for the spinner.
        String[] items2 = new String[]{"blank", "less than 4", "4-6", "7-9", "more than 10"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        //set the spinners adapter to the previously created one.
        servingDropdown.setAdapter(adapter2);
        spinnerSearchResult.add(SpinnerSearchResultServings);


        //Servings spinner
        //get the spinner from the xml.
        prepTimeDropdown = findViewById(R.id.PrepTimeDropdown);
        //create a list of items for the spinner.
        //ALL ITEMS IN LESS THAN 1 HOUR ALSO MUST INCLUDE 30 MINUTES OR LESS
        String[] items3 = new String[]{"blank", "30 minutes or less", "less than 1 hour", "more than 1 hour"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        //set the spinners adapter to the previously created one.
        prepTimeDropdown.setAdapter(adapter3);

        spinnerSearchResult.add(SpinnerSearchResultTime);

        searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openResultActivity();
            }
        });

    }


    public void openResultActivity() {
        SpinnerSearchResultDiet = dietDropdown.getSelectedItem().toString();
        SpinnerSearchResultServings = servingDropdown.getSelectedItem().toString();
        SpinnerSearchResultTime = prepTimeDropdown.getSelectedItem().toString();

        Intent SearchActivtiytoResult = new Intent(this, ResultActivity.class); // we are going
        // from the context of this page which is reffered to as this to the next activity wich is the search activity
        SearchActivtiytoResult.putExtra("DietResult", SpinnerSearchResultDiet);
        SearchActivtiytoResult.putExtra("ServingResult", SpinnerSearchResultServings);
        SearchActivtiytoResult.putExtra("TimeResult", SpinnerSearchResultTime);

        startActivity(SearchActivtiytoResult);


    }

}
