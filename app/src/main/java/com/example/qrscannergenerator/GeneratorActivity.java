package com.example.qrscannergenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qrscannergenerator.Generator.TextFragment;
import com.example.qrscannergenerator.MainFragment.HomeFragment;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

public class GeneratorActivity extends AppCompatActivity {
    String TAG = "GenerateQRCode";
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";

    ImageView img;
    Button btnSave, btnShare, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);
        img = (ImageView) findViewById(R.id.img_view);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnShare = (Button) findViewById(R.id.btn_share);
        btnBack = (Button) findViewById(R.id.btn_back);

        final String text = getIntent().getExtras().getString("Value");

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

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean saved;
                String result;
                try {
                    saved = QRGSaver.save(savePath, text, bitmap, QRGContents.ImageType.IMAGE_JPEG);
                    result = saved ? "Image Saved" : "Image Not Saved";
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
