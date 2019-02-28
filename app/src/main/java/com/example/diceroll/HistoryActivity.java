package com.example.diceroll;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diceroll.Model.Dice;
import com.example.diceroll.Model.DiceHistory;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HistoryActivity extends Activity {


    ArrayList<DiceHistory> diceHistories;
    private DiceAdapter da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_parent);
        final ListView diceList = this.findViewById(R.id.diceRollList);

        diceHistories = (ArrayList<DiceHistory>) getIntent().getSerializableExtra("history");
        da = new DiceAdapter(this, R.layout.activity_history, diceHistories);
        Log.d("tag", "" + diceHistories.size());
        diceList.setAdapter(da);



        Button btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceList.setAdapter(null);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("shouldDelete", "yes");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });




    }

/*
    @Override
    public void onResume() {super.onResume();}
*/

    private class DiceAdapter extends ArrayAdapter<DiceHistory> {

        private ArrayList<DiceHistory> diceHistorieList = new ArrayList<>();
        private int[] imageRefs= {R.id.imageView7, R.id.imageView8, R.id.imageView9, R.id.imageView10, R.id.imageView11, R.id.imageView12};

        private final int[] colours = {
                Color.parseColor("#AAAAAA"),
                Color.parseColor("#EEEEEE")
        };
        public DiceAdapter(Context context, int activity_history, ArrayList<DiceHistory> diceHistories) {
            super(context, activity_history, diceHistories);
            this.diceHistorieList.addAll(diceHistories);
        }

        @Override
        public View getView(int position, View v, ViewGroup parent)
        {

            if (v == null) {
                LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = li.inflate(R.layout.activity_history, null);
                Log.d("LIST", "Position: " + position + " View created");
            }
            else {
                Log.d("LIST", "Position: " + position + " View Reused");
            }


                v.setBackgroundColor(colours[position % colours.length]);

                DiceHistory diceHistory = diceHistorieList.get(position);

                TextView timestamp = v.findViewById(R.id.timeStamp);
                TextView diceSum = v.findViewById(R.id.diceSum);

                SimpleDateFormat format = new SimpleDateFormat("HH-mm");
                String time = format.format(diceHistory.getTime());
                timestamp.setText(time);


                diceSum.setText(diceHistory.getDiceSum() + "");

                for(int i = 0; i < imageRefs.length; i++)
                {
                    ImageView imageView = v.findViewById(imageRefs[i]);
                    imageView.setVisibility(View.INVISIBLE);
                }

                for(int i = 1; i <= diceHistory.getSavedRoll().size(); i++)
                {
                    ImageView imageView = (ImageView) v.findViewById(imageRefs[i-1]);
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageResource(diceHistory.getSavedRoll().get(i-1).getImage());
                }





            return v;


        }
    }
}
