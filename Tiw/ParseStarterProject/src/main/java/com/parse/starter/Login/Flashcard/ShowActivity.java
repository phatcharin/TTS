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

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vamnoize on 29/8/2558.
 */
public class ShowActivity extends ActionBarActivity implements OnClickListener{
   // ImageView ivShow;
    private Context context = this;
    RecyclerView recyclerView;
    String receive_catName;

    Button btnGotoPageAddWord;
    Button btnGotoGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showwords);

        btnGotoPageAddWord = (Button) findViewById(R.id.btnGotoAddWord);
        btnGotoPageAddWord.setOnClickListener(this);

        btnGotoGame = (Button) findViewById(R.id.btnGotoGame);
        btnGotoGame.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        receive_catName =  bundle.getString("SENT_CatName");


        //Log.d("name11",receive_catName);

        String receive = receive_catName;
        Log.d("check receive catName",receive);
        final List<setItem> datalist = new ArrayList<>();
        ParseHelp PHelp = new ParseHelp();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(PHelp.TABLE_NAME_DATA);
        query.whereEqualTo("CategoriesName", receive);

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objectslist, ParseException e) {
                Log.d("check objectslist.size", String.valueOf(objectslist.size()));
                if (e == null) {
                    Log.d("name111111", String.valueOf(objectslist.size()));
                    for (int i = 0; i < objectslist.size(); i++) {
                        String catName = objectslist.get(i).getString("CategoriesName");
                        String wFront = objectslist.get(i).getString("TextFront");
                        ParseFile picF = objectslist.get(i).getParseFile("FileImgFront");
                        String pathImgF = objectslist.get(i).getString("PathImgFront");

                        String wBack = objectslist.get(i).getString("TextBack");
                        ParseFile picB = objectslist.get(i).getParseFile("FileImgBack");
                        String pathImgB = objectslist.get(i).getString("PathImgBack");

                        Log.d("check catName", catName + " " + wFront + " "+wBack);

                        setItem cWord = new setItem();
                        // cWord.setItemID(id);
                        cWord.setItemcatName(catName);
                        cWord.setItemWordFront(wFront);
                        cWord.setItemImgFileFront(picF);
                        cWord.setItemPathImgFront(pathImgF);
                        cWord.setItemWordBack(wBack);
                        cWord.setItemImgFileBack(picB);
                        cWord.setItemPathImgBack(pathImgB);

                        datalist.add(cWord);
                    }
                }



                recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                recyclerView.setAdapter(new Adapters(context, datalist));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                // recyclerView.setLayoutManager(new LinearLayoutManager(context));
                Log.d("vamvam", "test");

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == btnGotoPageAddWord){
            intent();
        }if (v == btnGotoGame){
            chooseGame();
        }
    }
    public void intent() {
        String sentCatName = receive_catName;
        Intent Intent = new Intent(getApplicationContext(), AddWordActivity.class);
        Intent.putExtra("SENT_CatName",sentCatName );
//        Log.d("SENT_CAT_ID FROM Add cat", sentCatName);
        startActivity(Intent);
    }
    public void chooseGame() {
        String sentGame = receive_catName;
        Intent Intent = new Intent(ShowActivity.this, ChooseGames.class);
        Intent.putExtra("SENT_Game", sentGame);
//        Log.d("SENT_CAT_ID FROM Add cat", sentCatName);
        startActivity(Intent);
    }
}
