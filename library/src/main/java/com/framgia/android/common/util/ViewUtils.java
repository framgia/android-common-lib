package com.framgia.android.common.util;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AnimRes;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Copyright 2016 Framgia, Inc.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * ViewUtils
 */
public class ViewUtils {

    /**
     * Play the custom animation on a given view
     *
     * @param context   @see android.content.Context
     * @param animResId The id of the animation which is defined in R.anim
     * @param view      @see android.view.View
     */
    public static void startAnimation(Context context, @AnimRes int animResId, View view) {
        Animation animation = AnimationUtils.loadAnimation(context, animResId);
        view.startAnimation(animation);
    }

    /**
     * Play the fade in animation on a given view
     *
     * @param context @see android.content.Context
     * @param view    @see android.view.View
     */
    public static void fadeIn(Context context, View view) {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        view.startAnimation(fadeInAnimation);
    }

    /**
     * Play the fade out animation on a given view
     *
     * @param context @see android.content.Context
     * @param view    @see android.view.View
     */
    public static void fadeOut(Context context, View view) {
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
        view.startAnimation(fadeOutAnimation);
    }

    /**
     * Show and hide the coresponding views
     *
     * @param viewTobeShowed @see android.view.View
     * @param viewToBeHidden @see android.view.View
     * @param isGone         Whether to set the visibility of the view to be hidden as View.GONE
     */
    public static void switchView(View viewTobeShowed, View viewToBeHidden, boolean isGone) {
        viewTobeShowed.setVisibility(View.VISIBLE);
        viewToBeHidden.setVisibility(isGone ? View.GONE : View.INVISIBLE);
    }


    /**
     * Compatibility auto remove OnGlobalLayoutListener
     *
     * @param pView                   @see android.view.View
     * @param pOnGlobalLayoutListener @see android.view.ViewTreeObserver.OnGlobalLayoutListener
     */
    public static void onGlobalLayoutListenerCompat(final View pView,
        final ViewTreeObserver.OnGlobalLayoutListener pOnGlobalLayoutListener) {
        pView.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override public void onGlobalLayout() {
                        pOnGlobalLayoutListener.onGlobalLayout();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            pView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        } else {
                            pView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        }
                    }
                });
    }
}
