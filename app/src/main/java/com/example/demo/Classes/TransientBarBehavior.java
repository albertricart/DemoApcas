package com.example.demo.Classes;


import android.content.Context;

import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;


@SuppressWarnings("unused") // Constructed via xml
public class TransientBarBehavior extends CoordinatorLayout.Behavior<View> {

    private static final Interpolator fastOutSlowInInterpolator = new FastOutSlowInInterpolator();

    public TransientBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        if (child.getVisibility() == View.VISIBLE) {
            float translationY = this.getViewTranslationYForSnackbar(parent, child);
            child.setTranslationY(translationY);
        }
        return true;
    }

    @Override
    public void onDependentViewRemoved(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        if (dependency instanceof Snackbar.SnackbarLayout && child.getTranslationY() != 0.0F) {
            ViewCompat.animate(child).translationY(0.0F).scaleX(1.0F).scaleY(1.0F).alpha(1.0F)
                    .setInterpolator(fastOutSlowInInterpolator);
        }
    }

    private float getViewTranslationYForSnackbar(CoordinatorLayout parent, View child) {
        float minOffset = 0.0F;
        List dependencies = parent.getDependencies(child);
        int i = 0;

        for (int z = dependencies.size(); i < z; ++i) {
            View view = (View) dependencies.get(i);
            if (view instanceof Snackbar.SnackbarLayout && parent.doViewsOverlap(child, view)) {
                minOffset = Math.min(minOffset, view.getTranslationY() - (float) view.getHeight());
            }
        }

        return minOffset;
    }
}
