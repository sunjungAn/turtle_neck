package com.example.exam1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_stretching1_2 extends Fragment {
    private View view;

    public static Fragment_stretching1_2 newInstance(){
        Fragment_stretching1_2 fragment_Stretching1_2 = new Fragment_stretching1_2();
        return fragment_Stretching1_2;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_str1_2,container,false);
        return view;
    }
}

