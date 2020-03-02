package com.example.qrscannergenerator.MainFragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qrscannergenerator.MainActivity;
import com.example.qrscannergenerator.R;
import com.example.qrscannergenerator.ScanCodeActivity;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Hashtable;

import me.dm7.barcodescanner.core.BarcodeScannerView;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.CLIPBOARD_SERVICE;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class ScannerFragment extends Fragment {

    View v;
    Button btn_share, btn_copy, btn_scanNew, btn_image;

    private static final int SELECT_PHOTO = 1000;
    private static final int PERMISSION_CODE = 1001;
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    public static TextView result_qr;
    boolean check;
    public String barcode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_scanner, container, false);
        result_qr = (TextView) v.findViewById(R.id.result_qr);
        btn_scanNew = (Button) v.findViewById(R.id.btn_scanNew);
        btn_image = (Button) v.findViewById(R.id.btn_image);
        btn_share = (Button) v.findViewById(R.id.btn_share);
        btn_copy = (Button) v.findViewById(R.id.btn_copy);

        check_btn();
        scanNew_btn();
        scanIMG_btn();
        copy_btn();
        share_btn();

        result_qr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                check = true;
                check_btn();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return v;
    }

    private void scanNew_btn() {
        btn_scanNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
                    } else {
                        startActivity(new Intent(getContext(), ScanCodeActivity.class));
                    }
                } else {
                    startActivity(new Intent(getContext(), ScanCodeActivity.class));
                }

            }
        });
    }

    private void scanIMG_btn() {
        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);

                    } else {
                        pickImageFromGallery();
                    }
                } else {
                    pickImageFromGallery();
                }
            }
        });
    }

    private void copy_btn() {
        btn_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager myClipboard = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
                ClipData myClip;
                myClip = ClipData.newPlainText("text", result_qr.getText().toString());
                myClipboard.setPrimaryClip(myClip);

                AlertDialog.Builder builder_copy = new AlertDialog.Builder(getActivity());
                builder_copy.setTitle("Result");
                builder_copy.setIcon(R.mipmap.ic_launcher);
                builder_copy.setMessage("Copy success");
                AlertDialog alert_copy = builder_copy.create();
                alert_copy.setButton(DialogInterface.BUTTON_POSITIVE, "Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something
                    }
                });
                alert_copy.setCanceledOnTouchOutside(false);
                alert_copy.show();
            }
        });
    }

    private void share_btn() {

    }

    private void check_btn() {
        if (!check) {
            btn_share.setEnabled(false);
            btn_copy.setEnabled(false);
            btn_share.setBackgroundResource(R.drawable.bg_black);
            btn_copy.setBackgroundResource(R.drawable.bg_black);
        }
        if (check) {
            btn_share.setEnabled(true);
            btn_copy.setEnabled(true);
            btn_share.setBackgroundResource(R.drawable.hover_bg3_bg2);
            btn_copy.setBackgroundResource(R.drawable.hover_bg5_bg2);
        }
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    InputStream imageStream = null;
                    try {
                        imageStream = getActivity().getContentResolver().openInputStream(selectedImage);
                        Bitmap bMap = BitmapFactory.decodeStream(imageStream);
                        String contents = null;

                        int[] intArray = new int[bMap.getWidth() * bMap.getHeight()];
                        bMap.getPixels(intArray, 0, bMap.getWidth(), 0, 0, bMap.getWidth(), bMap.getHeight());
                        LuminanceSource source = new RGBLuminanceSource(bMap.getWidth(), bMap.getHeight(), intArray);
                        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                        MultiFormatReader reader = new MultiFormatReader();
                        Result result = reader.decode(bitmap);
                        contents = result.getText();

                        if (contents != null) {
                            result_qr.setText(contents);
                            check = true;
                            check_btn();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle("Scan Result");
                            builder.setIcon(R.mipmap.ic_launcher);
                            builder.setMessage("Nothing found try a different image or try again");
                            AlertDialog alert1 = builder.create();
                            alert1.setButton(DialogInterface.BUTTON_POSITIVE, "Done", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Do something
                                }
                            });
                            alert1.setCanceledOnTouchOutside(false);
                            alert1.show();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "File not found", Toast.LENGTH_SHORT).show();
                    } catch (NotFoundException e) {
                        e.printStackTrace();
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setTitle("Scan Result");
                        builder1.setIcon(R.mipmap.ic_launcher);
                        builder1.setMessage("Nothing Found. Try again");
                        AlertDialog alert2 = builder1.create();
                        alert2.setButton(DialogInterface.BUTTON_POSITIVE, "Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Do something
                            }
                        });
                        alert2.setCanceledOnTouchOutside(false);
                        alert2.show();
                    }
                }
        }
    }

}
