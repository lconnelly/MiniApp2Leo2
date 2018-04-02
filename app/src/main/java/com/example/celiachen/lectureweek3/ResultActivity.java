package com.example.celiachen.lectureweek3;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;
public class ResultActivity extends AppCompatActivity implements OnClickNotificationListener {
    public static final String KEY_URL = "key_url";
    public static final String KEY_TITLE = "key_title";
    private ListView mListView;
    public TextView ServingText;
    public TextView Timetext;
    RecipeAdapter adapter;
    String RecipeTitleFromIntent;
    String DietFromIntentParameter;
    String ServingsFromIntentParameter;
    String TimeFromIntentParameter;
    String RecipeUrl;
    Recipe theRecipe;
    Button NotificationButton;
    Recipe selectedRecipe;
    private TextView mTvRecipeFound;

   private ImageButton mImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context mContext = this;
        // data to display
        final ArrayList<Recipe> RecipeList = Recipe.getRecipesFromFile("recipes.json", this);
        final ArrayList<Recipe> SearchListResult = new ArrayList<>();
        // 1. each row should be clickable
        // when the row is clicked,
        // the intent is created and send
        mTvRecipeFound = findViewById(R.id.number_found);
        DietFromIntentParameter = getIntent().getStringExtra("DietResult");
        ServingsFromIntentParameter = getIntent().getStringExtra("ServingResult");
        TimeFromIntentParameter = getIntent().getStringExtra("TimeResult");
        //Check if whatever recipe we're looking at meets the parameters. If it does, add it new arraylist.

//if one is left blank add every
        //if the time parameter equals what's found then delete everything else without the parameter.


        Recipe stirfry = RecipeList.get(2);

        for (Recipe Re : RecipeList) {
            theRecipe = Re;

            if (DietFromIntentParameter.equalsIgnoreCase("blank")
                    && ServingsFromIntentParameter.equalsIgnoreCase("blank")
                    && TimeFromIntentParameter.equalsIgnoreCase("blank")){

                SearchListResult.addAll(RecipeList);
                mTvRecipeFound.setText(String.format(Locale.getDefault(),
                        "Found %d Recipes", SearchListResult.size()));
                break;
            }



            if (DietFromIntentParameter.equalsIgnoreCase(theRecipe.label) || DietFromIntentParameter.equalsIgnoreCase("blank")) {

                if (ServingsFromIntentParameter.contains("blank")) {
                    if (theRecipe.servings >= 4) {
                        if (TimeFromIntentParameter.contains("30 minutes or less")) {
                            if (theRecipe.prepTime <= 30) {
                                SearchListResult.add(theRecipe);
                                    //if(!SearchListResult.contains(stirfry)){
                                      //  SearchListResult.add(stirfry);
                                    //}
                            }
                        } else if (TimeFromIntentParameter.contains("less than 1 hour")) {
                            if (theRecipe.prepTime <= 60) {
                                SearchListResult.add(theRecipe);
                            }
                        } else if (TimeFromIntentParameter.contains("more than 1 hour")) {
                            if (theRecipe.prepTime > 0) {
                                SearchListResult.add(theRecipe);
                            }
                        }


                    }
                }


                if (ServingsFromIntentParameter.contains("less than 4")) {
                    if (theRecipe.servings < 4) {
                        if (TimeFromIntentParameter.contains("30 minutes or less")) {
                            if (theRecipe.prepTime <= 30) {
                                SearchListResult.add(theRecipe);
                            }
                        } else if (TimeFromIntentParameter.contains("less than 1 hour")) {
                            if (theRecipe.prepTime <= 60) {
                                SearchListResult.add(theRecipe);
                            }
                        } else if (TimeFromIntentParameter.contains("more than 1 hour")) {
                            if (theRecipe.prepTime > 0) {
                                SearchListResult.add(theRecipe);
                            }
                        } else if (TimeFromIntentParameter.contains("blank")){
                            if (theRecipe.prepTime >= 0){
                                SearchListResult.add(theRecipe);
                            }
                        }



                    }
                }




                if (ServingsFromIntentParameter.contains("4-6")) {
                    if (theRecipe.servings >= 4 && theRecipe.servings <= 6) {
                        if (TimeFromIntentParameter.contains("30 minutes or less")) {
                            if (theRecipe.prepTime <= 30) {
                                SearchListResult.add(theRecipe);
                            }
                        } else if (TimeFromIntentParameter.contains("less than 1 hour")) {
                            if (theRecipe.prepTime <= 60) {
                                SearchListResult.add(theRecipe);
                            }
                        } else if (TimeFromIntentParameter.contains("more than 1 hour")) {
                            if (theRecipe.prepTime > 0) {
                                SearchListResult.add(theRecipe);
                            }
                        }
                    }
                }


                if (ServingsFromIntentParameter.contains("7-9")) {
                    if (theRecipe.servings >= 7 && theRecipe.servings <= 9) {
                        if (TimeFromIntentParameter.contains("30 minutes or less")) {
                            if (theRecipe.prepTime <= 30) {
                                SearchListResult.add(theRecipe);
                            }
                        } else if (TimeFromIntentParameter.contains("less than 1 hour")) {
                            if (theRecipe.prepTime <= 60) {
                                SearchListResult.add(theRecipe);
                            }
                        } else if (TimeFromIntentParameter.contains("more than 1 hour")) {
                            if (theRecipe.prepTime > 0) {
                                SearchListResult.add(theRecipe);
                            }
                        }
                    }
                }


                if (ServingsFromIntentParameter.contains("more than 10")) {
                    if (theRecipe.servings >= 10) {
                        if (TimeFromIntentParameter.contains("30 minutes or less")) {
                            if (theRecipe.prepTime <= 30) {
                                SearchListResult.add(theRecipe);
                            }
                        } else if (TimeFromIntentParameter.contains("less than 1 hour")) {
                            if (theRecipe.prepTime <= 60) {
                                SearchListResult.add(theRecipe);
                            }
                        } else if (TimeFromIntentParameter.contains("more than 1 hour")) {
                            if (theRecipe.prepTime > 0) {
                                SearchListResult.add(theRecipe);
                            }
                        }
                    }
                }

                //Get's the # of recipes found

                mTvRecipeFound.setText(String.format(Locale.getDefault(),
                    "Found %d Recipes", SearchListResult.size()));
            }

            //else if (DietFromIntentParameter.equalsIgnoreCase("Blank")) {
              //  for (Recipe Temp : RecipeList) {
                   // SearchListResult.add(Temp);
                //}


//            }
        }

        RecipeAdapter adapter = new RecipeAdapter(this, SearchListResult, this);
        // create the adapter
        // find the listview in the layout
        // set the adapter to listview
        mListView = findViewById(R.id.recipe_list_view);
        mListView.setAdapter(adapter);
        mImageButton = findViewById(R.id.notifcation_button_send);


    }
    public void showNotification (Recipe recipe){
       //YEET
    }
    @Override
    public void onNotificationClicked (Recipe recipe){
        showNotification(recipe);
    }
}