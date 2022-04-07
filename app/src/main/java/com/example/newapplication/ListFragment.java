package com.example.newapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import model.DogLs;
import view.DogAPIService;

public class ListFragment extends Fragment {

    private DogAPIService apiservice;
    ArrayList<DogLs> lmodel = new ArrayList<DogLs>();
    ViewcycleAdapter adapter;
    RecyclerView rcv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

        apiservice = new DogAPIService();
        apiservice.getDogs().subscribeOn(
                Schedulers.newThread()
        ).observeOn(AndroidSchedulers.mainThread()).subscribeWith(
                new DisposableSingleObserver<List<DogLs>>() {
                    @Override
                    public void onSuccess(@NonNull List<DogLs> dogLs) {
                        for(DogLs dogLs1:dogLs){
                            lmodel.add(dogLs1);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                }
        );
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ViewcycleAdapter(getContext(), lmodel);
        rcv.setAdapter(adapter);
        rcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Click nè");
            }
        });

    }

    @Override
    public void onViewCreated(@androidx.annotation.NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcv = view.findViewById(R.id.rcv);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }
}