package com.example.kamera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button cameraBtn = (Button)findViewById(R.id.cameraBtn);
    ImageView imageView=findViewById(R.id.imageView);
    Uri dosya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
        {

            cameraBtn.setEnabled(false);
                ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},0);

        }

    }

    public void takeImage (View view)
    {
        Intent kameraNiyet = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri dosya = Uri.fromFile(getTakenImage());
        kameraNiyet.putExtra(MediaStore.EXTRA_OUTPUT,dosya);
        startActivityForResult(kameraNiyet,10);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions ,int[] grantResults)
    {
       if (requestCode==0)
       {
           if     (grantResults.length>0
                   && grantResults[0]== PackageManager.PERMISSION_GRANTED
                   && grantResults[1]== PackageManager.PERMISSION_GRANTED )
           {
               cameraBtn.setEnabled(true);
           }
       }

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent imageData) {
        // GEREKLI MI  super.onActivityResult(requestCode, resultCode, imageData);
        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                // Bitmap image = (Bitmap)imageData.getExtras().get("data");
                imageView.setImageURI(dosya);
            }
        }

    }

    private File getTakenImage()
    {
        File imageFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"kamera");

        if(!imageFileDir.exists())
        {
            if(!imageFileDir.mkdirs())
            {
                return null;
            }
        }

        String zamanDamgaasi =new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(imageFileDir.getPath()+File.separator+"FOTOGRAF_"+zamanDamgaasi+".jpg");

    }

}