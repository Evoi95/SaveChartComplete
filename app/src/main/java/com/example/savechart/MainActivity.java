package com.example.savechart;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);


        LineDataSet lineDataSet1 = new LineDataSet(dataVallues1(), "Data Set 1");
        LineDataSet lineDataSet2 = new LineDataSet(dataVallues2(), "Data Set 2");

        final LineChart mpLineChart;
        mpLineChart = findViewById(R.id.line_char);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);

        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.invalidate();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveImage(mpLineChart,"prova2");
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private ArrayList<Entry> dataVallues1() {

        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(0, 20));
        dataVals.add(new Entry(1, 22));
        dataVals.add(new Entry(2, 34));
        dataVals.add(new Entry(3, 45));
        dataVals.add(new Entry(4, 21));
        dataVals.add(new Entry(5, 2));
        return dataVals;
    }

    private ArrayList<Entry> dataVallues2() {

        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(0, 12));
        dataVals.add(new Entry(1, 213));
        dataVals.add(new Entry(2, 31));
        dataVals.add(new Entry(3, 41));
        dataVals.add(new Entry(4, 245));
        dataVals.add(new Entry(5, 21));
        return dataVals;
    }




    private void saveImage(LineChart chart, String image_name) {
        Bitmap finalBitmap;
        int width =chart.getWidth();
        int height= chart.getHeight();
        Bitmap cBitmap =chart.getChartBitmap();
        finalBitmap =  Bitmap.createScaledBitmap(cBitmap,width, height,true);
        //String root = Environment.getExternalStorageDirectory().toString();
        //File myDir = new File(root+"myFolder");
        //myDir.mkdir()
        //File path =  getApplicationContext().getFilesDir(); //WORK!!!

        //String path = /*Environment.getExternalStorageDirectory() +*/ "/sdcard/DCIM" ;//+ Environment.DIRECTORY_DCIM + "/";
        String path = Environment.DIRECTORY_DCIM;
        String fname = "Image-" + image_name+ ".png";
        File file = new File(path, fname);
        Log.i("LOAD", path + fname);
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
