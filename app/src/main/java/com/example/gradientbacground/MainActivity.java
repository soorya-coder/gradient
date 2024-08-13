package com.example.gradientbacground;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CardView card;
    TextView gradientcolor;
    SwipeRefreshLayout refresh;
    Runnable runnable;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card = findViewById(R.id.card);
        gradientcolor = findViewById(R.id.gradientcolor);
        refresh = findViewById(R.id.refresh);

        int[][] colour = {

                {c(R.color.gba1),c(R.color.gba2)},{c(R.color.nge1),c(R.color.nge2)},
                {c(R.color.gyr1),c(R.color.gyr2)},{c(R.color.gsb1),c(R.color.gsb2)},
                {c(R.color.ggl1),c(R.color.ggl2)},{c(R.color.gva1),c(R.color.gva2)},
                {c(R.color.grv1),c(R.color.grv2)},{c(R.color.gra1),c(R.color.gra2)},
                {c(R.color.gog1),c(R.color.gog2)},{c(R.color.gbr1),c(R.color.gbr2)},
                {c(R.color.gbv1),c(R.color.gbv2)},{c(R.color.gsv1),c(R.color.gsv2)},
                {c(R.color.gyg1),c(R.color.gyg2)},{c(R.color.gbc1),c(R.color.gbc2)},
                {c(R.color.gga1),c(R.color.gga2)},{c(R.color.grb1),c(R.color.grb2)},
                {c(R.color.gya1),c(R.color.gya2)},{c(R.color.ggr1),c(R.color.ggr2)},
                {c(R.color.grd1),c(R.color.grd2)},{c(R.color.gyv1),c(R.color.gyv2)},
                {c(R.color.ggb1),c(R.color.ggb2)},{c(R.color.gbo1),c(R.color.gbo2)},
                {c(R.color.gsr1),c(R.color.gsr2)},{c(R.color.gbb1),c(R.color.gbb2)},

                {c(R.color.nya1),c(R.color.nya2)},{c(R.color.nba1),c(R.color.nba2)},
                {c(R.color.noa1),c(R.color.noa2)},{c(R.color.nva1),c(R.color.nva2)},
                {c(R.color.ngb1),c(R.color.ngb2)},{c(R.color.nra1),c(R.color.nra2)},
                {c(R.color.nga1),c(R.color.nga2)},{c(R.color.nvb1),c(R.color.nvb2)},
                {c(R.color.nvc1),c(R.color.nvc2)},{c(R.color.nbb1),c(R.color.nbb2)},
                {c(R.color.nob1),c(R.color.nob2)},{c(R.color.nsa1),c(R.color.nsa2)},
                {c(R.color.nyb1),c(R.color.nyb2)},{c(R.color.noc1),c(R.color.noc2)},
                {c(R.color.nsb1),c(R.color.nsb2)},{c(R.color.nec1),c(R.color.nec2)},
                {c(R.color.nog1),c(R.color.nog2)},{c(R.color.nna1),c(R.color.nna2)},
                {c(R.color.nbc1),c(R.color.nbc2)},{c(R.color.nvd1),c(R.color.nvd2)},
                {c(R.color.nsc1),c(R.color.nsc2)},{c(R.color.nyc1),c(R.color.nyc2)},
                {c(R.color.nbd1),c(R.color.nbd2)},{c(R.color.nve1),c(R.color.nve2)},
                {c(R.color.ngd1),c(R.color.ngd2)},{c(R.color.nsd1),c(R.color.nsd2)},

        };

        GradientDrawable drawable = new GradientDrawable();
        drawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);

        drawable.setColors(colour[new Random().nextInt(colour.length)]);

        gradientcolor.setBackground(drawable);

        gradientcolor.setText(String.valueOf(colour[new Random().nextInt(colour.length)]));

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        drawable.setColors(colour[new Random().nextInt(colour.length)]);
                        gradientcolor.setBackground(drawable);

                        gradientcolor.setText(String.valueOf(colour[new Random().nextInt(colour.length)]));

                        refresh.setRefreshing(false);
                    }
                },1_000);
            }
        });
        refresh.setProgressBackgroundColorSchemeResource(R.color.white);
        refresh.setColorScheme(R.color.gyr2, R.color.gbo2);

        Handler handler = new Handler();

        runnable= () -> {
            drawable.setColors(colour[new Random().nextInt(colour.length)]);
            gradientcolor.setBackground(drawable);

            gradientcolor.setText(String.valueOf(colour[new Random().nextInt(colour.length)]));

            handler.postDelayed(runnable, 2_000);
        };

        final boolean[] running = {true};
        handler.post(runnable);

        gradientcolor.setOnClickListener(v -> {

            if (running[0]) {
                handler.removeCallbacks(runnable);
                running[0] =false;
            }else {
                handler.post(runnable);
                running[0] =true;
            }

        });

    }

    public int c(int colors){ return getResources().getColor(colors); }

}