package prj666.a03.cryptboard;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

//import android.util.Base64;

//import com.google.zxing.client.android.CaptureActivity;

public class KeyExchange extends AppCompatActivity {

    Button doneButton, scanButton;
    ImageView qrDisplay;
    String scanResult, publicKey;
    TextView textContent;
    Bitmap generatedQR;
    //@RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_exchange);

        final Activity activity = this;

        doneButton = findViewById(R.id.doneButton);
        scanButton = findViewById(R.id.scanQR);
        qrDisplay = findViewById(R.id.qrDisplay);
        textContent = findViewById(R.id.textView);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    1);
        }

        //TODO: Wire this up properly when parent activity is implemented
        //publicKey = getIntent().getStringExtra("KEY");

        //Hardcode for debugging purposes
        publicKey = "rPNZysAKBD4HBzr9CxDD46E00dLZUjQ/QFNk+82PwvkWoCLBy5fFDjWA+JE1hGcMXs1qAXe75r1R/VHWkIG1L3XkwD0rCLKYGiR5oDlTQ922/CiMpH/ZUnFzDZmtgpl0TZnQx0e8RdxOqG7XgBydi86+kWukI1v4LZ0yqROdGpdW6YnDFOziXAAdH0IvuwcNSBIzCZjbjjsN2DiVV/NcCaLFVzMz6poPTfb9AX/DsbFVUKcxBxm4uJ+PD+cEwSKfsmZ4/QAm1e/MAVQPMiHlxh6+cLGYNGSg7CkKfP9T8Pp0+GXcY1UORGGRLEqZjsJ1mY3IHaRg4yRCDlKRgK3rCw==";

        //Converts Strings into QRCodes
        try {
            generatedQR = QRCodeGenerator.encodeAsBitmap(publicKey);
            qrDisplay.setImageBitmap(generatedQR);
        } catch (WriterException e) {
            e.printStackTrace();
        }



        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan Contact QR");
                integrator.setCameraId(0);
                integrator.initiateScan();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (scanResult != null){
                    Intent result = new Intent();
                    result.putExtra("KEY", scanResult);
                    setResult(Activity.RESULT_OK, result);
                } else {
                    setResult(Activity.RESULT_CANCELED);
                }
                finish();
            }
        });
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                textContent.setText(result.getContents());
                scanResult = result.getContents();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

/*
Zxing QR scanning and generation library (open source)
https://stackoverflow.com/questions/29159104/how-to-integrate-zxing-barcode-scanner-without-installing-the-actual-zxing-app
https://opensource.google.com/projects/zxing
* */
