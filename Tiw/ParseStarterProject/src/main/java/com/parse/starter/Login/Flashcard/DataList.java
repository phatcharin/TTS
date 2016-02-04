package com.parse.starter.Login.Flashcard;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vamnoize on 4/9/2558.
 */
public class DataList {

    List<setItem> datalist = new ArrayList<setItem>();

    public List<setItem> DataList() {
        Log.d("data list size", String.valueOf(datalist.size()));
        return datalist;
    }

    public List<setItem> datalist(String strId,String strName, String strTitle) {
        setItem cWord = new setItem();
        cWord.setItemID(strId);
        cWord.setItemWordFront(strName);
        cWord.setItemWordBack(strTitle);
        datalist.add(cWord);
        Log.d("data list size all", String.valueOf(datalist.size()));
        return datalist;
    }
}
