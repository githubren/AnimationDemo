package com.example.yfsl.animationdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mTranslateAni;
    private Button mScaleAni;
    private Button mRotationAni;
    private Button mAlphaAni;
    private Button mCombinationAni;
    private Button mCombinationOrderAni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

    }

    private void initData() {
        mTranslateAni.setOnClickListener(this);
        mScaleAni.setOnClickListener(this);
        mRotationAni.setOnClickListener(this);
        mAlphaAni.setOnClickListener(this);
        mCombinationAni.setOnClickListener(this);
        mCombinationOrderAni.setOnClickListener(this);
    }

    private void initView() {
        mTranslateAni = findViewById(R.id.translation_animation);
        mScaleAni = findViewById(R.id.scale_animation);
        mRotationAni = findViewById(R.id.rotation_animation);
        mAlphaAni = findViewById(R.id.alpha_animation);
        mCombinationAni = findViewById(R.id.combination_animation_together);
        mCombinationOrderAni = findViewById(R.id.combination_animation_order);
    }

    /**
     * 平移动画
     */
    private void startTranslationAnimation(){
        //创建动画对象  第一个参数是需要执行动画的目标；第二个参数是动画的类型；第三个是动画执行的参数
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTranslateAni,"translationX",400);
        animator.setDuration(1000);//设置动画持续时间
        animator.setInterpolator(new DecelerateInterpolator());//设置插值器  这个设置的是减速插值器
        animator.start();//开始动画
        //动画监听  结束后回复到原样
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(mTranslateAni,"translationX",0);
                animator.setDuration(1000);
                animator.setInterpolator(new DecelerateInterpolator());
                animator.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 缩放动画
     */
    private void startScaleAnimation(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(mScaleAni,"scaleX",1.0f,0.3f);
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(mScaleAni,"scaleX",0.3f,1.0f);
                animator.setDuration(1000);
                animator.setInterpolator(new DecelerateInterpolator());
                animator.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 旋转动画
     */
    private void startRotationAnimation(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(mRotationAni,"rotation",360);
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    /**
     * 透明度动画
     */
    private void startAlphaAnimation(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(mAlphaAni,"alpha",1f,0.3f);
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(mAlphaAni,"alpha",0.3f,1f);
                animator.setDuration(1000);
                animator.setInterpolator(new DecelerateInterpolator());
                animator.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 组合动画
     */
    private void startCombinationAnimation(){
        PropertyValuesHolder holderTranslationX = PropertyValuesHolder.ofFloat("translationX",0);//X方向平移为0
        PropertyValuesHolder holderTranslationY = PropertyValuesHolder.ofFloat("translationY",0);//Y方向平移为0
        PropertyValuesHolder holderScaleX = PropertyValuesHolder.ofFloat("scaleX",1,0);//X方向缩放
        PropertyValuesHolder holderScaleY = PropertyValuesHolder.ofFloat("scaleY",1,0);//Y方向缩放
        PropertyValuesHolder holderRotation = PropertyValuesHolder.ofFloat("rotation",360);//旋转
        PropertyValuesHolder holderAlpha = PropertyValuesHolder.ofFloat("alpha",1,0);//透明度
        ObjectAnimator.ofPropertyValuesHolder(mCombinationAni,holderTranslationX,holderTranslationY,holderScaleX,holderScaleY,holderRotation,holderAlpha).setDuration(1000).start();
    }

    /**
     * 先后顺序的组合动画
     */
    private void startCombinationOrderAnimation(){
        AnimatorSet set = new AnimatorSet();
        //平移动画
        ObjectAnimator animatorTranslation = ObjectAnimator.ofFloat(mCombinationOrderAni,"translationX",400);
        animatorTranslation.setDuration(1000);
        animatorTranslation.setInterpolator(new DecelerateInterpolator());
        //缩放动画
        ObjectAnimator animatorScale = ObjectAnimator.ofFloat(mCombinationOrderAni,"scaleX",1f,0.3f);
        animatorScale.setDuration(1000);
        animatorScale.setInterpolator(new DecelerateInterpolator());
        //旋转动画
        ObjectAnimator animatorRotation = ObjectAnimator.ofFloat(mCombinationOrderAni,"rotation",360);
        animatorRotation.setDuration(1000);
        animatorRotation.setInterpolator(new DecelerateInterpolator());
        //透明度动画
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(mCombinationOrderAni,"alpha",1f,0.3f);
        animatorAlpha.setDuration(1000);
        animatorAlpha.setInterpolator(new DecelerateInterpolator());
        //设置动画的先后顺序
        set.play(animatorTranslation).before(animatorScale);
        set.play(animatorScale).before(animatorRotation);
        set.play(animatorRotation).before(animatorAlpha);
        set.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.translation_animation:
                startTranslationAnimation();
                break;
            case R.id.scale_animation:
                startScaleAnimation();
                break;
            case R.id.rotation_animation:
                startRotationAnimation();
                break;
            case R.id.alpha_animation:
                startAlphaAnimation();
                break;
            case R.id.combination_animation_together:
                startCombinationAnimation();
                break;
            case R.id.combination_animation_order:
                startCombinationOrderAnimation();
                break;
        }
    }
}
