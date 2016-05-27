package com.example.app.digitalizebillscustomer.MainScreen;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.app.digitalizebillscustomer.AppService;
import com.example.app.digitalizebillscustomer.Models.Bill;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import rx.Observable;

/**
 * Created by vikkycorner on 27/05/16.
 */
public class MainScreenModelImpl implements MainScreenModel {
    AppService service;

    public MainScreenModelImpl(AppService service) {
        this.service = service;
    }

    @Override
    public Observable<LinkedList<Bill>> bills() {
/*
        File sdcard = Environment.getExternalStorageDirectory();

//        URI uri = sdcard.toURI();



        File file = new File(sdcard,"test.txt");
//        File file1 = new File(sdcard.toURI());

        Log.i("Notes", file.getPath());


        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";

            Log.i("Notes","try");
            Log.i("Notes",br.readLine());
            while ((line = br.readLine()) != null) {
//                text.append(line);
                Log.i("Notes",line);
//                text.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
            Log.i("Notes",e.toString());
        }

        Log.i("Notes", "reached");
*/
        return null;
    }
}
