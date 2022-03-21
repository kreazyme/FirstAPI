package com.example.newapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import model.DogLs;
import view.DogAPIService;

public class MainActivity extends AppCompatActivity {

    private DogAPIService apiservice;
    ArrayList<DogLs> lmodel = new ArrayList<DogLs>();
    ViewcycleAdapter adapter;
    RecyclerView rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv = findViewById(R.id.recicleviewer);



        apiservice = new DogAPIService();
        apiservice.getDogs().subscribeOn(
                Schedulers.newThread()
        ).observeOn(AndroidSchedulers.mainThread()).subscribeWith(
                new DisposableSingleObserver<List<DogLs>>() {
                    @Override
                    public void onSuccess(@NonNull List<DogLs> dogLs) {
                        for(DogLs dogLs1:dogLs){
                            lmodel.add(dogLs1);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                }
        );
        rcv.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new ViewcycleAdapter(this, lmodel);
        rcv.setAdapter(adapter);
        rcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Click nè");
            }
        });
    }
}