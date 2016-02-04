package com.parse.starter.Login.Flashcard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vamnoize on 10/9/2558.
 */
public class CategoriesActivity  extends ActionBarActivity implements OnClickListener{

    EditText edCatName;
    Button bntAddCat;

    ParseHelp parseHelp = new ParseHelp();

    String sentCatName;
    String strCatName;

    String from_game = "wait";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcategoise);

//        Bundle bundle = getIntent().getExtras();
//        from_game =  bundle.getString("back_card_set");

        edCatName = (EditText) findViewById(R.id.edCatname);
        bntAddCat = (Button) findViewById(R.id.bntAddCat);

        bntAddCat.setOnClickListener(this);

        showCategoriesList();
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onClick(View v) {
        if (v == bntAddCat){

            strCatName = edCatName.getText().toString();
          //  addCategories(strCatName);
            ParseObject player = new ParseObject(parseHelp.TABLE_NAME_CAT);
            player.put("CategoriesName", strCatName);
            player.saveInBackground();

            setText();

            sentCatName = strCatName;
            Intent Intent = new Intent(getApplicationContext(), ShowActivity.class);
            Intent.putExtra("SENT_CatName",sentCatName );
            Log.d("SENT_CAT_ID FROM Add cat", sentCatName);
            startActivity(Intent);

        }

    }
    public void setText() {
        edCatName.setText("");
    }
    public void showCategoriesList(){

        final ArrayList<String> datalist = new ArrayList();
        ParseQuery<ParseObject> query = ParseQuery.getQuery(parseHelp.TABLE_NAME_CAT);
        query.whereNotEqualTo("CategoriesName", "");

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> categoryList, ParseException e) {
                Log.d("Check", String.valueOf(datalist.size()));
                if (e == null) {
                    for (int i = 0; i < categoryList.size(); i++) {
                        String catName = categoryList.get(i).getString("CategoriesName");
                        datalist.add(catName);
                    }
                    Log.d("Check num of cat", String.valueOf(datalist.size()));

                    final ListView listView1 = (ListView) findViewById(R.id.listView);
                    ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.inlistview,R.id.Itemname, datalist);
                    listView1.setAdapter(itemsAdapter);
                    listView1.setOnItemClickListener(new OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selected = listView1.getItemAtPosition(position).toString();
                            sentCatName = selected;
                            Intent Intent = new Intent(getApplicationContext(), ShowActivity.class);
                            Intent.putExtra("SENT_CatName",sentCatName );
                            Log.d("SENT_CAT_ID FROM Add cat", sentCatName);
                            startActivity(Intent);

                            Log.d("check", selected);
                        }
                    });
                }
            }
        });
    }


}
