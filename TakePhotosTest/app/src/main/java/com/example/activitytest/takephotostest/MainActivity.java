package com.example.activitytest.takephotostest;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_CROP_PHOTO = 2;
    ImageView mImageView;
    Button bt_takePhoto;
    Button chooseFromAlbum;
    private String mCurrentPhotoPath;
    private static Uri mPhotoUri;
    private static File mPhoto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.iv_thumbnail);
        bt_takePhoto = (Button) findViewById(R.id.bt_take_photo);
        bt_takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
        chooseFromAlbum = (Button) findViewById(R.id.choose_from_album);
        chooseFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File outputImage = null;
                try {
                    outputImage = createImageFile("cropped");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mPhotoUri = Uri.fromFile(outputImage);
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                intent.putExtra("crop", true);
                intent.putExtra("scale", true);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri);
                startActivityForResult(intent, REQUEST_CROP_PHOTO);

            }
        });

    }

    ///create image file in external dir///
    private File createImageFile(String name) throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = name + "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        mPhoto = image;
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.d("MainActivity", "New File in " + mCurrentPhotoPath + " Create Success");
        return image;
    }

    ///take photo///
    public void dispatchTakePictureIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            try {
                photoFile = createImageFile("Original");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.activitytest.takephotostest.fileprovider",
                        photoFile);
                mPhotoUri = photoURI;
                Log.d("MainActivity", "File url = " + Uri.fromFile(mPhoto));
                Log.d("MainActivity", "Content url = " + mPhotoUri);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    ///crop photo///
    public void dispatchCropPhotoIntent(){

        Uri imageUri = Uri.fromFile(mPhoto);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent,1008);

//        Intent intent = new Intent("com.android.camera.action.CROP");
//        //Intent intent = new Intent("android.intent.action.GET_CONTENT");
//        File outputFile = null;
//        try {
//            outputFile = createImageFile("Cropped");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        if (outputFile != null){
//            //mPhotoUri = Uri.fromFile(mPhoto);
//            intent.setType("image/*");
//            intent.putExtra("crop", true);
//            intent.putExtra("scale", true);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mPhoto));
//            startActivityForResult(intent, REQUEST_CROP_PHOTO);
//        }else{
//            Log.d("MainActivity", "Crop:Output File is null");
//        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Log.d("MainActivity", "Take_Photo_OK");
            dispatchCropPhotoIntent();
        }
        else if (requestCode == REQUEST_CROP_PHOTO && resultCode == RESULT_OK){
            Log.d("MainActivity", "Crop_OK");
            Toast.makeText(MainActivity.this, "Crop OK", Toast.LENGTH_SHORT);
        }
        else if(requestCode == REQUEST_CROP_PHOTO && resultCode == RESULT_CANCELED){
            Log.d("MainActivity", "Crop_Canceled");
            Toast.makeText(MainActivity.this, "Crop Canceled", Toast.LENGTH_SHORT);
        }
    }

}

