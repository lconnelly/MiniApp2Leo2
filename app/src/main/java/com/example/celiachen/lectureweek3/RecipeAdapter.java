package com.example.celiachen.lectureweek3;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
/**
 * Created by celiachen on 2/7/18.
 */
// adapter is needed when you want to do any sort of list or table view
// gets data and decides where to display in the activity
public class RecipeAdapter extends BaseAdapter {
    // adapter takes the app itself and a list of data to display
    private Context mContext;
    private ArrayList<Recipe> mRecipeList;
    private LayoutInflater mInflater;
    private OnClickNotificationListener mListener;
    // constructor
    public RecipeAdapter(Context mContext, ArrayList<Recipe> mRecipeList, OnClickNotificationListener mListener) {
        // initialize instances variables
        this.mContext = mContext;
        this.mRecipeList = mRecipeList;
        this.mListener = mListener;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    // methods
    // a list of methods we need to override
    // gives you the number of recipes in the data source
    @Override
    public int getCount() {
        return mRecipeList.size();
    }
    // returns the item at specific position in the data source
    @Override
    public Object getItem(int position) {
        return mRecipeList.get(position);
    }
    // returns the row id associated with the specific position in the list
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        // check if the view already exists
        // if yes, you don't need to inflate and findViewbyID again
        if (convertView == null) {
            // inflate
            convertView = mInflater.inflate(R.layout.list_item_recipe, parent, false);
            // add the views to the holder
            holder = new ViewHolder();
            // views
            holder.titleTextView = convertView.findViewById(R.id.food_list_title);
            holder.servingTextView = convertView.findViewById(R.id.food_list_Servings);
            holder.thumbnailImageView = convertView.findViewById(R.id.food_list_thumbnail);
            holder.timeTextView = convertView.findViewById(R.id.food_list_time);
            holder.dietLabelTextView = convertView.findViewById(R.id.food_list_diet_label);
            holder.notifcationButtonImageView = convertView.findViewById(R.id.notifcation_button_send);
            holder.notifcationButtonImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onNotificationClicked((Recipe) getItem(position));
                }
            });
            // add the holder to the view
            // for future use
            convertView.setTag(holder);
        } else {
            // get the view holder from converview
            holder = (ViewHolder) convertView.getTag();
        }



        // get relavate subview of the row view
        TextView titleTextView = holder.titleTextView;
        TextView servingTextView = holder.servingTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;
        TextView timeTextView = holder.timeTextView;
        TextView dietLabelTextView = holder.dietLabelTextView;
        ImageView notifcationButtonImageView = holder.notifcationButtonImageView;
        // get corresonpinding recipe for each row
      final  Recipe recipe = (Recipe) getItem(position);
        // update the row view's textviews and imageview to display the information
        // titleTextView
        titleTextView.setText(recipe.title);
        titleTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
        titleTextView.setTextSize(22);
        // servingTextView
        servingTextView.setText(recipe.servings + " servings");
        servingTextView.setTextSize(10);
        servingTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
        //TimeTextview
        timeTextView.setText(recipe.prepTime+" minutes");
        servingTextView.setTextSize(10);
        servingTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));

        if(recipe.prepTime == 195) {
            timeTextView.setText("3 hours and 15 minutes");
        }
        if(recipe.prepTime == 80) {
            timeTextView.setText("1 hour and 20 minutes");
        }

        if(recipe.prepTime == 360) {
            timeTextView.setText("6 hours");
        }


        //NOTIFCAITON SHIT GOES HERE

        final Intent openRecipeNotifcation = new Intent(Intent.ACTION_VIEW);
        openRecipeNotifcation.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        openRecipeNotifcation.setData(Uri.parse(recipe.instructionUrl));
        final PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, openRecipeNotifcation, 0);


        notifcationButtonImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                String text = "The instruction for " + recipe.title +" can be found here";
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext, "channel_ID")
                        /*
                        .bigText("The instruction for " + recipe.title +" can be found here")
                        .setBigContentTitle("Big title")
                        .setSummaryText("Big summary"))
                        .setContentTitle("Title")
                        .setContentText("Summary")
                        .setSmallIcon(android.R.drawable.sym_def_app_icon);
                         */

                        .setSmallIcon(R.drawable.notification_button)
                        .setContentTitle("Cooking Instruction")
                        .setContentText("The instruction for " + recipe.title +" can be found here")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
                NotificationManagerCompat manager = NotificationManagerCompat.from(mContext);
                manager.notify((int) System.currentTimeMillis(), mBuilder.build());
            }
        });

/*
        if(recipe.prepTime >= 60) {
            int hours = recipe.prepTime/60;
            int minutes = recipe.prepTime % 60;
            String neat = ("%d:%02d" + hours + minutes);
            timeTextView.setText(neat);
        }
        */

        /*
        int hours = t / 60; //since both are ints, you get an int
int minutes = t % 60;
System.out.printf("%d:%02d", hours, minutes);
         */

        //DietLabelTExtView
        dietLabelTextView.setText(recipe.label);
        dietLabelTextView.setTextSize(10);
        dietLabelTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
        // imageView
        // use Picasso library to load image from the image url
        Picasso.with(mContext).load(recipe.imageUrl).into(thumbnailImageView);
        return convertView;
    }
    // viewHolder
    // is used to customize what you want to put into the view
    // it depends on the layout design of your row
    // this will be a private static class you have to define
    private static class ViewHolder {
        TextView titleTextView;
        TextView servingTextView;
        TextView timeTextView;
        TextView dietLabelTextView;
        ImageView thumbnailImageView;
        ImageButton notifcationButtonImageView;
    }
    // intent is used to pass information between activities
    // intent -> pacakge
    // sender, receiver
}