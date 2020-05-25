package com.example.exam1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_stretching3_1 extends Fragment{
    private View view;

    public static Fragment_stretching3_1 newInstance(){
        Fragment_stretching3_1 fragment_Stretching3_1 = new Fragment_stretching3_1();
        return fragment_Stretching3_1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_stretching3_1,container,false);
        return view;
    }
}
