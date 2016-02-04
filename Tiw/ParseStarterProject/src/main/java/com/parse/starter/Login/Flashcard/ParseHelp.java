package com.parse.starter.Login.Flashcard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vamnoize on 29/8/2558.
 */
public class ParseHelp {
    String TABLE_NAME_DATA = "RememberTable";
    String TABLE_NAME_CAT = "Categories";
    String id;

    public void  AddCategory (String CatName){
        ParseObject player = new ParseObject(TABLE_NAME_CAT);
        player.put("CategoriesName", CatName);
        player.saveInBackground();
    }

    public void AddData (String wFront,String wBack){
        ParseObject player = new ParseObject(TABLE_NAME_DATA);
        player.put("TextFront", wFront);
        player.put("TextBack", wBack);
        player.saveInBackground();
    }


    public void AddData (String catName,String wFront,String pathFront,ParseFile fileFront,String wBack,String pathBack,ParseFile fileBack){

        ParseObject player = new ParseObject(TABLE_NAME_DATA);
        player.put("CategoriesName",catName);
        player.put("TextFront", wFront);
        player.put("PathImgFront", pathFront);
        player.put("FileImgFront", fileFront);

        player.put("TextBack", wBack);
        player.put("PathImgBack", pathBack);
        player.put("FileImgBack", fileBack);
        player.saveInBackground();
    }
/*
  //public List<setItem> DataList = new ArrayList<setItem>();
  private List<setItem> myDataList = new ArrayList<setItem>();

  public List<setItem> getData(){

      final DataList datalist = new DataList();

      ParseQuery<ParseObject> query = ParseQuery.getQuery("player");
      query.whereEqualTo("name", "rgxh");

      query.findInBackground(new FindCallback<ParseObject>() {
          public void done(List<ParseObject> commentList, ParseException e) {

              for (int i = 0; i < commentList.size(); i++) {

                  id = commentList.get(i).getString("objectID");
                  String name = commentList.get(i).getString("name");
                  String title = commentList.get(i).getString("title");

                  datalist.datalist(id,name,title);

                 /* setItem cWord = new setItem();

                  cWord.setItemID(id);
                  cWord.setItemName(name);
                  cWord.setItemTitle(title);
                  DataList.add(cWord);
dada

                 // Log.d("vamvamtest11", String.valueOf(DataList.size()));
              }
              Log.d("vamvamtest222", String.valueOf(datalist.DataList().size()));
              myDataList = datalist.DataList();

          }
      });
        Log.d("vamvamtest2", String.valueOf(myDataList));
      return datalist.DataList();
    }
*/





}
