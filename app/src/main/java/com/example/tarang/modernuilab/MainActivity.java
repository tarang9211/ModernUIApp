package com.example.tarang.modernuilab;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener {

    //Create view variables
    private View viewOne, viewTwo, viewThree, viewFour, viewFive;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewOne =  findViewById(R.id.viewOne);
        viewTwo = findViewById(R.id.viewTwo);
        viewThree = findViewById(R.id.viewThree);
        viewFour = findViewById(R.id.viewFour);
        viewFive = findViewById(R.id.viewFive);
        seekBar = (SeekBar)findViewById(R.id.myseekbar);
        seekBar.setOnSeekBarChangeListener(this);
        setRandomColor();
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
        if (id == R.id.action_more_info) {
            moreInfo();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void moreInfo()
    {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("More Information");
        dialogBuilder.setMessage("Click on any of the two links below");

        dialogBuilder.setPositiveButton("Visit MOMA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org/"));
                startActivity(intent);
            }
        });
        dialogBuilder.setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialogBuilder.create().show();
    }

    private void setRandomColor(){
        setBgColor(viewOne, getRandomColor());
        setBgColor(viewTwo, getRandomColor());
        setBgColor(viewThree, getRandomColor());
        setBgColor(viewFour, getRandomColor());
        setBgColor(viewFive, getRandomColor());

    }

    private int getRandomColor(){
        Random random = new Random();
        int colorValue = Color.argb(255, random.nextInt(156), random.nextInt(256), random.nextInt(256));
        return colorValue;
    }

    private void setBgColor(View view, int colorValue){
        view.setBackgroundColor(colorValue);
        view.setTag(colorValue);
    }

    private void changeColor(View view, int value){
        int color = (Integer) view.getTag();
        color += value;
        view.setBackgroundColor(color);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        Toast.makeText(this, String.valueOf(progress), Toast.LENGTH_SHORT).show();
        changeColor(viewOne, progress);
        changeColor(viewTwo, progress);
        changeColor(viewThree, progress);
        changeColor(viewFour, progress);
        changeColor(viewFive, progress);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }


}
