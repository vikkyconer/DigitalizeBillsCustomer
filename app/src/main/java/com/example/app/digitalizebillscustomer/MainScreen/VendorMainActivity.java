package com.example.app.digitalizebillscustomer.MainScreen;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app.digitalizebillscustomer.R;

import java.io.File;

public class VendorMainActivity extends Activity implements View.OnClickListener {

    private NfcAdapter adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_main);

        sendData();
    }

    private void sendData() {
        adapter = NfcAdapter.getDefaultAdapter(this);

        if (!adapter.isNdefPushEnabled()) {
            Toast.makeText(this, "Not Sent", Toast.LENGTH_LONG).show();
            finish();
        } else {
            String fileName = "test.txt";
            File fileDirectory = Environment
                    .getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES);
            Log.i("Notes", String.valueOf(fileDirectory));

            // Create a new file using the specified directory and name
            File fileToTransfer = new File(fileDirectory, fileName);
            fileToTransfer.setReadable(true, false);

            adapter.setBeamPushUris(
                    new Uri[]{Uri.fromFile(fileToTransfer)}, this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            adapter.setBeamPushUris(new Uri[]{data.getData()}, this);

            Button btn = new Button(this);

            btn.setText("Over");
            btn.setOnClickListener(this);
            setContentView(btn);
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }

}
