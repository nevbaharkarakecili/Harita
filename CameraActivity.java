package com.example.harita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        final Button camBtn = (Button)findViewById(R.id.camBtn);
        camBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(kamera,10);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent imgData)
    {
        if(requestCode==10)
        {
            Bitmap resim = (Bitmap)imgData.getExtras().get("data");
            ImageView camImg = (ImageView)findViewById(R.id.camImg);
            camImg.setImageBitmap(resim);
        }
    }
}
