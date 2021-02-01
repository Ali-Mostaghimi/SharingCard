package com.skyproduct.sharingcard;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

public class MySharingCard extends RelativeLayout implements View.OnClickListener {

    private static final long RVEAL_DURATION = 600; //ms

    View rootView;
    AppCompatImageView profileImage;
    AppCompatImageView socialIcon;
    RelativeLayout layoutReveal;
    LinearLayout layoutBtns;
    AppCompatImageView coverImage;
    AppCompatButton socialBtn1, socialBtn2, socialBtn3;

    public MySharingCard(Context context) {
        super(context);
        init(context);
    }

    public MySharingCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        rootView = inflate(context, R.layout.layout_shring_card, this);
        profileImage = rootView.findViewById(R.id.image_profile);
        socialIcon = rootView.findViewById(R.id.social_icon);
        layoutReveal = rootView.findViewById(R.id.layout_reveal);
        layoutBtns = rootView.findViewById(R.id.layout_btns);
        coverImage = rootView.findViewById(R.id.cover_image);
        socialBtn1 = rootView.findViewById(R.id.social_btn_1);
        socialBtn2 = rootView.findViewById(R.id.social_btn_2);
        socialBtn3 = rootView.findViewById(R.id.social_btn_3);
        socialIcon.setOnClickListener(this);
    }

    public AppCompatImageView getProfileImage() { return profileImage; }
    public AppCompatImageView getCoverImage() { return coverImage; }
    public AppCompatButton getSocialBtn1() { return socialBtn1; }
    public AppCompatButton getSocialBtn2() { return socialBtn2;}
    public AppCompatButton getSocialBtn3() { return socialBtn3; }

    @Override
    public void onClick(View v) {
        int centerX = (socialIcon.getRight() + socialIcon.getLeft()) / 2;
        int centerY = (socialIcon.getBottom() + socialIcon.getTop()) / 2;
        float radius = (float) Math.hypot(centerX - coverImage.getLeft(), coverImage.getHeight());
        if(layoutReveal.getVisibility() == VISIBLE){
            hide(centerX, centerY, radius);
        }else {
            show(centerX, centerY, radius);

        }
    }

    private void show(int centerX, int centerY, float radius) {
        socialIcon.setImageResource(R.drawable.ic_cancel);
        socialIcon.setBackgroundResource(R.drawable.social_icon_cancel_bg);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Animator animator = ViewAnimationUtils
                    .createCircularReveal(layoutReveal, centerX, centerY, 0, radius);
            animator.setDuration(MySharingCard.RVEAL_DURATION);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    layoutReveal.setVisibility(VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    layoutBtns.setVisibility(VISIBLE);
                    Animation fadein = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.fadein);
                    layoutBtns.startAnimation(fadein);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.start();

        }  else{
            Animation animation = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.fadein);
            animation.setDuration(MySharingCard.RVEAL_DURATION * 3 / 4);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    layoutReveal.setVisibility(VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    layoutBtns.setVisibility(VISIBLE);
                    Animation fadein = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.fadein);
                    fadein.setDuration(MySharingCard.RVEAL_DURATION * 3 / 8);
                    layoutBtns.startAnimation(fadein);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            layoutReveal.startAnimation(animation);
        }
    }

    private void hide(int centerX, int centerY, float radius) {
        socialIcon.setImageResource(R.drawable.sent_message_icon);
        socialIcon.setBackgroundResource(R.drawable.social_icon_normal_bg);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Animator animator = ViewAnimationUtils
                    .createCircularReveal(layoutReveal, centerX, centerY, radius, 0);
            animator.setDuration(MySharingCard.RVEAL_DURATION / 2);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    Animation fadeout = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.fadeout);
                    layoutBtns.startAnimation(fadeout);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    layoutBtns.setVisibility(GONE);
                    layoutReveal.setVisibility(GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.start();

        }else {
            Animation animation = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.fadeout);
            animation.setDuration(MySharingCard.RVEAL_DURATION * 3 / 6);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    Animation fadeout = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.fadeout);
                    fadeout.setDuration(MySharingCard.RVEAL_DURATION * 3 / 8);
                    layoutBtns.startAnimation(fadeout);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    layoutBtns.setVisibility(GONE);
                    layoutReveal.setVisibility(GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            layoutReveal.startAnimation(animation);
        }
    }
}
