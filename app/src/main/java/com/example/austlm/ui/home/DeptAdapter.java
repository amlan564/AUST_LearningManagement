package com.example.austlm.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.austlm.R;

import java.util.List;

public class DeptAdapter extends PagerAdapter {
    private Context context;
    private List<DeptModel> list;

    public DeptAdapter(Context context, List<DeptModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.dept_item_layout, container, false);

        ImageView deptIcon;
        TextView deptTitle, deptDes;

        deptIcon = view.findViewById(R.id.dept_icon);
        deptTitle = view.findViewById(R.id.dept_title);
        deptDes = view.findViewById(R.id.dept_des);

        deptIcon.setImageResource(list.get(position).getImg());

        deptTitle.setText(list.get(position).getTitle());

        deptDes.setText(list.get(position).getDescription());

        container.addView(view, 0);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
