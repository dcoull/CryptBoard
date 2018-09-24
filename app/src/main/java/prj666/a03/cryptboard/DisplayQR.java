package prj666.a03.cryptboard;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.client.android.CaptureActivity;

public class DisplayQR extends AppCompatActivity {

    Button doneButton, scanButton;
    ImageView qrDisplay;
    String scanResult, tag;
    TextView textContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_qr);

        doneButton = findViewById(R.id.doneButton);
        scanButton = findViewById(R.id.scanQR);
        qrDisplay = findViewById(R.id.qrDisplay);
        textContent = findViewById(R.id.textView);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            //textContent.setText("Permissions failed");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    1);
        }
        else{
            //textContent.setText("Permissions granted");
        }

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CaptureActivity.class);
                intent.setAction("com.google.zxing.client.android.SCAN");
                intent.putExtra("SAVE_HISTORY", false);


                startActivityForResult(intent, 0); //might need to include permission request handle here
            }
        });



        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Need to add functionality to pass back scanned key
                finish();
            }
        });

    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 0){
            if (resultCode == RESULT_OK){
                scanResult = data.getStringExtra("SCAN_RESULT");
                Log.d(tag, "contents: " + scanResult);
                textContent.setText(scanResult);
            } else if (resultCode == RESULT_CANCELED){
                //add functionality for cancelled results
                Log.d(tag, "RESULT_CANCELLED");
            }
        }
    }
}

/*

TODO: QR code generation/receiving from intent
TODO: Check camera permissions and request


Zxing QR scanning and generation library (open source)
https://stackoverflow.com/questions/29159104/how-to-integrate-zxing-barcode-scanner-without-installing-the-actual-zxing-app
https://opensource.google.com/projects/zxing
* */

