package com.theelitedevelopers.bunkies.ui.onboarding.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.theelitedevelopers.bunkies.R;

public class OnboardScreenPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater inflater;

    public OnboardScreenPagerAdapter(Context context) {
        this.context = context;
    }

    public int[] slide_images = {
        R.drawable.bunkies_onboarding_1,
        R.drawable.bunkies_onboarding_2,
        R.drawable.bunkies_onboarding_3,
    };

    public String[] slide_titles = {
            "Find your ideal roommate",
            "Customize your search",
            "Simple, Fast, Safe"
    };

    public String[] slide_description = {
            "Life's amazing when you share it with someone, let's help you find them.",
            "Discover the roommates that are right for you on Bunkies",
            "Our roommate service is safe, fast and simple, find your ideal roomie today."
    };

    @Override
    public int getCount() {
        return slide_description.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide_layout, container, false);

        ImageView imageView = view.findViewById(R.id.image);
        TextView title = view.findViewById(R.id.title);
        TextView description = view.findViewById(R.id.description);

        imageView.setImageResource(slide_images[position]);
        title.setText(slide_titles[position]);
        description.setText(slide_description[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
