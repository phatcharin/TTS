package com.parse.starter.Login.Flashcard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piyapong on 24/12/2558.
 */
public class ChooseGames extends ActionBarActivity implements OnClickListener{
    private Context context = this;

    String receive_catName;
    String return_game;

    ImageButton btnGameMatching;
    ImageButton btnGameTrueOrFalse;
    ImageButton btnGameAnswer;
    Button btnBackCat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_all);

        btnGameMatching = (ImageButton) findViewById(R.id.btnGameMatching);
        btnGameMatching.setOnClickListener(this);
        btnGameTrueOrFalse = (ImageButton) findViewById(R.id.btnGameTrueOrFalse);
        btnGameTrueOrFalse.setOnClickListener(this);
        btnGameAnswer = (ImageButton) findViewById(R.id.btnGameAnswer);
        btnGameAnswer.setOnClickListener(this);
        btnBackCat = (Button) findViewById(R.id.btnBackCat);
        btnBackCat.setOnClickListener(this);



        Bundle bundle = getIntent().getExtras();
        receive_catName =  bundle.getString("SENT_Game");



        //Log.d("name11",receive_catName);

        String receive = receive_catName;
        Log.d("chk recv catName@ChsGm",receive+"");
        final List<setItem> datalist = new ArrayList<>();
        ParseHelp PHelp = new ParseHelp();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(PHelp.TABLE_NAME_DATA);
        query.whereEqualTo("CategoriesName", receive);



    }

    @Override
    public void onClick(View v) {
        if (v == btnGameMatching){
            selectMatching();
        }
        if (v == btnGameTrueOrFalse){
            selectTrueOrFalse();
        }
        if (v == btnBackCat){
            backToCardset();
        }
        if (v == btnGameAnswer){
            selectAnswer();
        }
    }


    public void selectMatching() {
        String sentGameMatching = receive_catName;
        Intent Intent = new Intent(getApplicationContext(), GameMatching.class);
        Intent.putExtra("Matching", sentGameMatching);
//        Log.d("SENT_CAT_ID FROM Add cat", sentCatName);
        startActivity(Intent);
    }
    public void selectTrueOrFalse() {
        String sentGameTrueOrFalse = receive_catName;
        Intent Intent = new Intent(getApplicationContext(), GameTrueOrFalse.class);
        Intent.putExtra("TrueFalse", sentGameTrueOrFalse);
//        Log.d("SENT_CAT_ID FROM Add cat", sentCatName);
        startActivity(Intent);
    }
    public void selectAnswer() {
        String sentGameAnswer = receive_catName;
        Intent Intent = new Intent(getApplicationContext(), GameAnswer.class);
        Intent.putExtra("Game_Answer", sentGameAnswer);
//        Log.d("SENT_CAT_ID FROM Add cat", sentCatName);
        startActivity(Intent);
    }
    public void backToCardset() {
        String backCard = receive_catName;
        Intent Intent = new Intent(getApplicationContext(), CategoriesActivity.class);
        Intent.putExtra("back_card_set", backCard);
//        Log.d("SENT_CAT_ID FROM Add cat", sentCatName);
        startActivity(Intent);
    }


}
