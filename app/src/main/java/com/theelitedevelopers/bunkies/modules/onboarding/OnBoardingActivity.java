package com.theelitedevelopers.bunkies.modules.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.theelitedevelopers.bunkies.R;
import com.theelitedevelopers.bunkies.databinding.ActivityOnboardingBinding;
import com.theelitedevelopers.bunkies.ui.authentication.LoginActivity;
import com.theelitedevelopers.bunkies.modules.onboarding.adapter.OnboardScreenPagerAdapter;

public class OnBoardingActivity extends AppCompatActivity {
    ActivityOnboardingBinding binding;
    private ImageView[] mDots;
    private int currentPage, current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        OnboardScreenPagerAdapter onboardScreenPagerAdapter = new OnboardScreenPagerAdapter(this);
        binding.boardingViewPager.setAdapter(onboardScreenPagerAdapter);

        binding.boardingViewPager.addOnPageChangeListener(viewListener);

        addDotsIndicator(0);

        binding.finishBtn.setOnClickListener(v -> {
            current = currentPage + 1;
            binding.boardingViewPager.setCurrentItem(current);
            if(current > 2) {
                //SharedPref.getInstance(getApplicationContext()).saveBoolean(HAS_BEEN_LAUNCHED_BEFORE, true);

                startActivity(new Intent(OnBoardingActivity.this, LoginActivity.class));
                finish();
            }
        });

    }


    public void addDotsIndicator(int position) {
        mDots = new ImageView[3];
        binding.dotsLayout.removeAllViews();
        for(int i = 0; i < mDots.length; i++) {
            mDots[i] = new ImageView(this);
            if(i == position)
                mDots[i].setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dot_onboard_view, null));
            else {
                mDots[i].setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dot_onboard_unactive_view, null));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(4, 0, 4, 0);
            binding.dotsLayout.addView(mDots[i], params);
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);

            currentPage = position;
            if(position == 0) {
                binding.finishBtn.setVisibility(View.INVISIBLE);
            } else if(position == mDots.length - 1) {
                binding.finishBtn.setVisibility(View.VISIBLE);
            } else {
                binding.finishBtn.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}