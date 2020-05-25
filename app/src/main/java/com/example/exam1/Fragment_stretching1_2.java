package com.example.exam1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_stretching1_1 extends Fragment {
    private View view;

    public static Fragment_stretching1_1 newInstance(){
        Fragment_stretching1_1 fragment_Stretching1_1 = new Fragment_stretching1_1();
        return fragment_Stretching1_1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_str1_1, container, false);
        return view;
    }
}
