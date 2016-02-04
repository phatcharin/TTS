package com.parse.starter.Login.Flashcard;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.ParseAnalytics;
import com.parse.ParseFile;
import com.parse.starter.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by vamnoize on 29/10/2558.
 */
public class FlashcardsActivity extends ActionBarActivity implements View.OnClickListener {
    EditText etName,etTitle,etPic;
    Button btAdd,btpic,btshow;
    String name,title;

    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    String picturePath;
    ParseHelp parseHelp = new ParseHelp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_main);

        etName = (EditText) findViewById(R.id.etName);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etPic = (EditText) findViewById(R.id.edPic);

        btAdd = (Button) findViewById(R.id.btnAdd);
        btpic = (Button) findViewById(R.id.pic);
        btshow = (Button) findViewById(R.id.btnShow);

        btAdd.setOnClickListener(this);
        btpic.setOnClickListener(this);
        btshow.setOnClickListener(this);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());


        //ParseObject player = new ParseObject("Player");



    }

    @Override
    public void onClick(View v) {
        if (v == btpic) {
            loadImagefromGallery(v);
        }
        if (v == btAdd) {

            name=etName.getText().toString();
            title=etTitle.getText().toString();

            if (picturePath!=null) {

                // Locate the image in res >
                Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
                // Convert it to

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // Compress image to lower quality scale 1 - 100
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                Object image = null;
                try {
                    //String path = null;
                    image = readInFile(picturePath);
                    //Log.d("this",readInFile(picturePath).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Log.d("this",image.toString());

                // Create the ParseFile
                ParseFile file = new ParseFile(name+".png", (byte[]) image);
                // Upload the image into Parse Cloud
                file.saveInBackground();

                //parseHelp.AddData(name,title,picturePath,file);
            }
            else {

                parseHelp.AddData(name,title);
            }
            setText();
        }
        if (v == btshow) {
            Intent Intent = new Intent(this, CategoriesActivity.class);
            startActivity(Intent);
        }
    }

    public void setText(){
        etPic.setText("");
        etTitle.setText("");
        etName.setText("");
    }


    public void loadImagefromGallery(View view) {
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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

            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
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

