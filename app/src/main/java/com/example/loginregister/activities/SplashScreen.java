package com.example.loginregister.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;


import com.example.loginregister.R;

public class SplashScreen extends AwesomeSplash {


    @Override
    public void initSplash(ConfigSplash splash2) {

        splash2.setBackgroundColor(R.color.bg_colors);
        splash2.setAnimCircularRevealDuration(2000);
        splash2.setRevealFlagX(Flags.REVEAL_LEFT);
        splash2.setRevealFlagY(Flags.REVEAL_TOP);

        splash2.setLogoSplash(R.mipmap.logo1);
        splash2.setAnimLogoSplashDuration(2000);
        splash2.setAnimLogoSplashTechnique(Techniques.FadeIn);
        splash2.setOriginalHeight(400);
        splash2.setOriginalWidth(400);
        splash2.setAnimPathFillingDuration(3000);
        splash2.setPathSplashFillColor(R.color.yellow);
        splash2.setTitleSplash("Engineered Services at Bargained Price");
        splash2.setTitleTextColor(R.color.colorPrimaryDark);
        splash2.setTitleTextSize(18f);
        splash2.setAnimTitleDuration(3000);
        splash2.setAnimTitleTechnique(Techniques.StandUp);
        splash2.setTitleFont("fonts/Roboto.ttf");
    }
    @Override
    public void animationsFinished() {
        Intent intent = new Intent(this, Who_are_you.class);
        startActivity(intent);
    }
}