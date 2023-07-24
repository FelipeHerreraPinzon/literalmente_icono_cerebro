package com.masin.saludmasin;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.masin.saludmasin.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

       ActivityMainBinding binding;
        FloatingActionButton fab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;

                case R.id.bienestar:
                    replaceFragment(new BienestarFragment());
                    break;

                case R.id.asistencia:
                    replaceFragment(new AsistenciaFragment());
                    break;

                case R.id.contenidos:
                    replaceFragment(new PortalesFragment());
                    break;

            }
            return true;
        });




        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new PanicoFragment());
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        Animation breathingAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_breathing);
        fab.startAnimation(breathingAnimation);



    }



    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }




}

