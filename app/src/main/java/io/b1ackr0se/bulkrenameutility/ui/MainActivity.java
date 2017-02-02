package io.b1ackr0se.bulkrenameutility.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.b1ackr0se.bulkrenameutility.R;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rename) Button rename;
    @BindView(R.id.create_rules) Button createRule;
    @BindView(R.id.saved_rules) Button savedRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        playAnimation();
    }

    private void playAnimation() {
        ArrayList<Animator> animators = new ArrayList<>();

        ObjectAnimator renameAnimation = ObjectAnimator.ofFloat(rename, View.ALPHA, 0, 1);
        animators.add(renameAnimation);

        ObjectAnimator createRuleAnimation = ObjectAnimator.ofFloat(createRule, View.ALPHA, 0, 1);
        animators.add(createRuleAnimation);

        ObjectAnimator savedRulesAnimation = ObjectAnimator.ofFloat(savedRules, View.ALPHA, 0, 1);
        animators.add(savedRulesAnimation);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animators);
        animatorSet.setDuration(400);
        animatorSet.start();
    }

}
