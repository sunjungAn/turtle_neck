package com.example.exam1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_stretching3_2 extends Fragment{
    private View view;

    public static Fragment_stretching3_2 newInstance(){
        Fragment_stretching3_2 fragment_Stretching3_2 = new Fragment_stretching3_2();
        return fragment_Stretching3_2;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_stretching3_2,container,false);
        return view;
    }
}
