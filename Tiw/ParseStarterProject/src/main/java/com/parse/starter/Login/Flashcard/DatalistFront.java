package com.parse.starter.Login.Flashcard;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piyapong on 27/12/2558.
 */
public class DatalistFront {

    List<setItem> datalist_f = new ArrayList<setItem>();

    public List<setItem> DatalistFront() {
        Log.d("data front", String.valueOf(datalist_f.size()));
        return datalist_f;
    }

    public List<setItem> datalist_f(String strId,String strName, String strTitle) {
        setItem fWord = new setItem();
        fWord.setItemID(strId);
        fWord.setItemWordFront(strName);
        fWord.setItemWordBack(strTitle);
        datalist_f.add(fWord);
        Log.d("data list size front", String.valueOf(datalist_f.size()));
        return datalist_f;
    }

}
