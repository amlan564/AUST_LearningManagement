package com.example.austlm.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.austlm.R;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private SliderLayout sliderLayout;
    private ViewPager viewPager;
    private DeptAdapter adapter;
    private List<DeptModel> list;
    private ImageView map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        sliderLayout = view.findViewById(R.id.slider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(2);

        setSliderViews();

        list = new ArrayList<>();
        list.add(new DeptModel(R.drawable.ic_cse, "CSE", "The department of Computer Science and Engineering, abbreviated CSE, is offering an undergraduate engineering degree program since the establishment of the university in the year 1995 with a view to offer quality higher education to numerous worthy young fellows as well as to meet the huge demand of highly qualified specialists in the field."));
        list.add(new DeptModel(R.drawable.ic_eee, "EEE", "The graduates of this department have gone on to a variety of positions in industry such as Siemens Bangladesh, Ericsson, Grameen Phone, Robi, Banglalink, Airtel, Government services, PGCB GETCO etc."));
        list.add(new DeptModel(R.drawable.ic_te, "TE", "The Ahsanullah University of Science and Technology (AUST) was the first private university in Bangladesh to introduce B.Sc. in Textile Technology course in 1998.The program aimed at turning out textile specialists and serving mainly textile industry."));
        list.add(new DeptModel(R.drawable.ic_ce, "CE", "The main aim and objectives of this department is to offer program covering all important branches and disciplines of Civil Engineering. The department covers a wide range of course curriculum and well-equipped laboratory facilities."));

        adapter = new DeptAdapter(getContext(), list);

        viewPager = view.findViewById(R.id.viewPager);

        viewPager.setAdapter(adapter);

        map = view.findViewById(R.id.map);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        return view;
    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q=Ahsanullah University of Science and Technology");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    private void setSliderViews() {
        for (int i = 0; i<5; i++){
            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i){

                case 0:
                    sliderView.setImageDrawable(R.drawable.campus_a);
                    break;

                case 1:
                    sliderView.setImageDrawable(R.drawable.campus_b);
                    break;

                case 2:
                    sliderView.setImageDrawable(R.drawable.campus_c);
                    break;

                case 3:
                    sliderView.setImageDrawable(R.drawable.campus_d);
                    break;

                case 4:
                    sliderView.setImageDrawable(R.drawable.campus_e);
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);

            sliderLayout.addSliderView(sliderView);

        }
    }
}