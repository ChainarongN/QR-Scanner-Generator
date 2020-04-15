package com.bestpro.qrscannergenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GeneratorActivity extends AppCompatActivity {
    private Context mContext = GeneratorActivity.this;
    private static final int REQUEST = 112;
    String TAG = "GenerateQRCode";
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    String ImagePath;
    Uri URI;

    ImageView img;
    Button btnSave, btnBack;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);
        img = (ImageView) findViewById(R.id.img_view);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnBack = (Button) findViewById(R.id.btn_back);

        text = getIntent().getExtras().getString("Value");

        if (text != null && !text.equals("")) {
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallerDimension = width < height ? width : height;
            smallerDimension = smallerDimension * 3 / 4;

            qrgEncoder = new QRGEncoder(
                    text, null,
                    QRGContents.Type.TEXT,
                    smallerDimension);
            try {
                bitmap = qrgEncoder.encodeAsBitmap();
                img.setImageBitmap(bitmap);
            } catch (WriterException e) {
                Log.v(TAG, e.toString());
            }
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                        Log.v(TAG, "Permission is granted");
                        save();

                    } else {
                        Log.v(TAG, "Permission is revoked");
                        ActivityCompat.requestPermissions(GeneratorActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                } else { //permission is automatically granted on sdk<23 upon installation
                    Log.v(TAG, "Permission is granted");
                    save();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GeneratorActivity.this, MainActivity.class));
            }
        });

    }

    private void save() {
        ImagePath = MediaStore.Images.Media.insertImage(
                getContentResolver(),
                bitmap,
                "image_qrCode",
                "image_qrCode"
        );
        URI = Uri.parse(ImagePath);
        Toast.makeText(GeneratorActivity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();
    }
}
