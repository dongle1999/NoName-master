package com.example.noname.TrangBacSi.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.noname.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_BacSi_TrangChu extends Fragment {

    ImageSlider img_slider;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_bac_si_trang_chu, container,false);


        img_slider=v.findViewById(R.id.img_slider_BacSi_TrangChu);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.slider_1,null));
        slideModels.add(new SlideModel(R.drawable.slider_2,null));
        slideModels.add(new SlideModel(R.drawable.slider_3,null));
        slideModels.add(new SlideModel(R.drawable.slider_4,null));
        img_slider.setImageList(slideModels,true);



        return v;
    }
}