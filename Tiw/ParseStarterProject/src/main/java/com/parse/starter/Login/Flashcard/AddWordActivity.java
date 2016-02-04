package com.parse.starter.Login.Flashcard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.starter.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vamnoize on 7/9/2558.
 */
public class AddWordActivity extends ActionBarActivity implements View.OnClickListener {

    Button btnAdd;
    Button btnImgFront,btnGelleryF,btnCameraF;
    Button btnImgBack,btnGelleryB,btnCameraB;
    ImageView imgFront,imgBack;
    EditText edWordFront,edWordBack;

    private static int RESULT_LOAD_IMG = 1;
    String receive_catName;

    ParseHelp parseHelp = new ParseHelp();

    Boolean checkPicFront = false;
    Boolean checkPicBack = false;
    Boolean havePicFront = false;
    Boolean havePicBack = false;
    Boolean havePic = false;

    String catName;
    String wFront;
    String wBack;
    String picturePath;
    String picturePathFront,picturePathBack;
    ParseFile fileFornt,fileBack;

    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        edWordFront = (EditText) findViewById(R.id.etWordFront);
        btnImgFront = (Button) findViewById(R.id.btnImgFront);

        edWordBack = (EditText) findViewById(R.id.etWordBack);
        btnImgBack = (Button) findViewById(R.id.btnImgBack);

        btnAdd = (Button) findViewById(R.id.btnAdd);

        imgFront = (ImageView) findViewById(R.id.ivImgFront);
        btnGelleryF = (Button) findViewById(R.id.btnImgGelF);
        btnCameraF = (Button) findViewById(R.id.btnImgCamF);

        imgBack = (ImageView) findViewById(R.id.ivImgBack);
        btnCameraB = (Button) findViewById(R.id.btnImgCameBack);
        btnGelleryB = (Button) findViewById(R.id.btnImgGelBack);



        btnImgFront.setOnClickListener(this);
        btnImgBack.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

        btnGelleryF.setOnClickListener(this);
        btnCameraF.setOnClickListener(this);

        btnGelleryB.setOnClickListener(this);
        btnCameraB.setOnClickListener(this);


        btnGelleryF.setVisibility(View.GONE);
        btnCameraF.setVisibility(View.GONE);
        imgFront.setVisibility((View.GONE));

        btnGelleryB.setVisibility(View.GONE);
        btnCameraB.setVisibility(View.GONE);
        imgBack.setVisibility(View.GONE);

        //btnGelleryF.setVisibility(View.GONE);

        Log.d("ERRORLOG","time = "+timeStamp);

       // Intent Intent = new Intent(getApplicationContext(), CategoriesActivity.class);
        //Intent.putExtra("SENT_CAT_NAME",receive_catName );
       // Log.d("SENT_CAT_ID FROM Add cat", receive_catName);
       // startActivity(Intent);

        Bundle bundle = getIntent().getExtras();
        receive_catName =  bundle.getString("SENT_CatName");

        catName = receive_catName;

        Log.d("test name",receive_catName);
    }



    public void onClick(View v) {
        if (v == btnImgFront) {
            btnGelleryF.setVisibility(View.VISIBLE);
            btnCameraF.setVisibility(View.VISIBLE);
        }
        if (v == btnCameraF){

        }
        if (v == btnGelleryF) {
            havePic = true;
            havePicFront = true;
            checkPicFront = true;
            loadImagefromGallery(v);
            imgFront.setVisibility(View.VISIBLE);
            //imgFront.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }

        if (v == btnImgBack) {
            btnGelleryB.setVisibility(View.VISIBLE);
            btnCameraB.setVisibility(View.VISIBLE);
        }
        if (v == btnGelleryB){
            havePic = true;
            havePicBack = true;
            checkPicBack = true;
            loadImagefromGallery(v);
            imgBack.setVisibility(View.VISIBLE);
        }
        if (v == btnCameraB){

        }
        if (v == btnAdd) {

           // catName = receive_catName;
            Log.d("catName",catName);

            Log.d("ERRORLOG",havePic.toString());
            if (edWordFront.getText()!= null || havePic) {
                ParseObject player = new ParseObject(parseHelp.TABLE_NAME_DATA);
                player.put("CategoriesName", catName);

                // Game true false
                player.put("Priority", 50);
                player.put("Probability_TF", 50);
                player.put("CountPlay_TF", 0);
                player.put("CountCorrect_TF", 0);
                player.put("TimeAvr_TF",0);

                // Game answer
                player.put("Probability_Ans", 50);
                player.put("CountPlay_Ans", 0);
                player.put("CountCorrect_Ans", 0);
                player.put("TimeAvr_Ans",0);

                Log.d("ERRORLOG", "This is loop");
                Log.d("ERRORLOG","IMG : "+havePicFront.toString()+" Back : "+havePicBack.toString());

                if (edWordFront.getText().length() != 0) {
                    wFront = edWordFront.getText().toString();
                    player.put("TextFront", wFront);
                    Log.d("ERRORLOG", "have text front");

                    if (edWordBack.getText().length() != 0) {
                        wBack = edWordBack.getText().toString();
                        player.put("TextBack", wBack);
                        Log.d("ERRORLOG", "have text back");
                    }
                    if (havePicBack) {
                        haveImgBack();
                        player.put("PathImgBack", picturePathBack);
                        player.put("FileImgBack", fileBack);
                        havePicBack = false;
                        Log.d("ERRORLOG","have pic Back");
                    }

                }
                if (havePicFront) {
                    haveImgFront();
                    player.put("PathImgFront", picturePathFront);
                    player.put("FileImgFront", fileFornt);
                    Log.d("ERRORLOG","have img front");

                    if (edWordBack.getText().length() != 0) {
                        wBack = edWordBack.getText().toString();
                        player.put("TextBack", wBack);
                    }
                    if (havePicBack) {
                        haveImgBack();
                        player.put("PathImgBack", picturePathBack);
                        player.put("FileImgBack", fileBack);
                        havePicBack = false;
                        Log.d("ERRORLOG","have img back");
                    }
                    havePicFront = false;
                }
                player.saveInBackground();

                intentShowActivity();
            } else {
                edWordFront.setText("error!!!");
                Log.d("ERRORLOG", "Error");
            }

            setText();
        }

    }

    public void intentShowActivity(){
        Intent Intent = new Intent(getApplicationContext(), ShowActivity.class);
        Intent.putExtra("SENT_CatName",catName );
        Log.d("SENT_CAT_ID FROM Add cat", catName);
        startActivity(Intent);
    }

    public void setText(){
        edWordFront.setText("");
        edWordBack.setText("");
        picturePath = "";
        picturePathBack = "";
        picturePathFront = "";

    }
    public void haveImgFront() {
        Bitmap bitmap = BitmapFactory.decodeFile(picturePathFront);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        Object image = null;
        try {
            image = readInFile(picturePathFront);

        } catch (Exception e) {
            e.printStackTrace();
        }

        fileFornt = new ParseFile("F"+timeStamp+".png", (byte[]) image);
        fileFornt.saveInBackground();
    }
    public void haveImgBack() {
        Bitmap bitmap = BitmapFactory.decodeFile(picturePathBack);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        Object image = null;
        try {
            image = readInFile(picturePathBack);

        } catch (Exception e) {
            e.printStackTrace();
        }


        fileBack = new ParseFile("B"+timeStamp+".png", (byte[]) image);
        fileBack.saveInBackground();
    }


    public void loadImagefromGallery(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();

            if(checkPicFront) {
                picturePathFront = picturePath;
                // ImageView imageView = (ImageView) findViewById(R.id.imageView);
                //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
              //  imgFront = (ImageView) findViewById(R.id.ivImgFront);
                imgFront.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                checkPicFront = false;
                //havePicFront = false;
            }
            if(checkPicBack){
                picturePathBack = picturePath;
               // imgBack = (ImageView) findViewById(R.id.ivImgBack);
                imgBack.setImageBitmap(BitmapFactory.decodeFile(picturePathBack));
                checkPicBack = false;
                //havePicBack = false;
            }

           // Log.d("test",picturePathFront+" "+picturePathBack);
        }
    }
    private byte[] readInFile(String path) throws IOException {
        // TODO Auto-generated method stub
        byte[] data = null;
        File file = new File(path);
        InputStream input_stream = new BufferedInputStream(new FileInputStream(
                file));
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        data = new byte[16384]; // 16K
        int bytes_read;
        while ((bytes_read = input_stream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytes_read);
        }
        input_stream.close();
        //Log.d("this",buffer.toByteArray().toString());
        return buffer.toByteArray();

    }

}
