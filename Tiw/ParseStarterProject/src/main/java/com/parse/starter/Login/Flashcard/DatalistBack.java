package com.parse.starter.Login.Flashcard;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piyapong on 27/12/2558.
 */
public class DatalistBack {

    List<setItem> datalist_b = new ArrayList<setItem>();

    public List<setItem> DatalistBack() {
        Log.d("data back", String.valueOf(datalist_b.size()));
        return datalist_b;
    }

    public List<setItem> datalist_b(String strId,String strName, String strTitle) {
        setItem bWord = new setItem();
        bWord.setItemID(strId);
        bWord.setItemWordFront(strName);
        bWord.setItemWordBack(strTitle);
        datalist_b.add(bWord);
        Log.d("data list size back", String.valueOf(datalist_b.size()));
        return datalist_b;
    }

}
