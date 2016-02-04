package com.parse.starter.Login.Flashcard;

import com.parse.ParseFile;

/**
 * Created by vamnoize on 3/9/2558.
 */
public class SingleItem {
    private ParseFile imgFront;
    private String pathImgFront;
    private String name;



    SingleItem(String strName ,ParseFile strImgFront,String strPathImgFront) {
        this.imgFront = strImgFront;
        this.name = strName;
        this.pathImgFront = strPathImgFront;
    }


    public ParseFile getImgFront() {
        return imgFront;
    }

    public String getName() {
        return name;
    }
    public String getPathImgFront() {return pathImgFront;}
}
