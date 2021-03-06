package com.mcxtzhang.zxtcommonlib;

import android.graphics.Color;
import android.graphics.Rect;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.TouchDelegate;
import android.view.View;

/**
 * 介绍：乱七八糟的Utils
 * 作者：zhangxutong
 * 邮箱：zhangxutong@imcoming.com
 * 时间： 2016/9/1.
 */
public class ZxtUtils {

    /**
     * 返回变成朋友蓝色的SpanString
     *
     * @param source 源数据
     * @param key    要匹配的key
     * @return
     */
    public static SpannableString getFriendColorSpannableString(String source, String key) {
        return getColorSpannableString(source, key, Color.parseColor("#4C9AD2"));
    }

    /**
     * 返回变色的SpanString
     *
     * @param source 源数据
     * @param key    要匹配的key
     * @param color  要变的色
     * @return
     */
    public static SpannableString getColorSpannableString(String source, String key, int color) {
        if (source == null) {
            source = "";
        }
        SpannableString spannableString = new SpannableString(source);//构建Span
        if (!TextUtils.isEmpty(key)) {
            for (int i = 0; i < key.length(); i++) {
                int indexB = source.indexOf(key.charAt(i));
                if (indexB > -1) {//匹配到了,加一个spans
                    spannableString.setSpan(/*span*/new ForegroundColorSpan(color), indexB, indexB + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
        return spannableString;
    }

    /**
     * 为View扩大点击范围
     *
     * @param view
     * @param expandTouchWidth
     */
    public static void expandViewClickRect(final View view, final int expandTouchWidth) {
        final View parentView = (View) view.getParent();
        parentView.post(new Runnable() {
            @Override
            public void run() {
                final Rect rect = new Rect();
                view.getHitRect(rect);
                rect.top -= expandTouchWidth;
                rect.bottom += expandTouchWidth;
                rect.left -= expandTouchWidth;
                rect.right += expandTouchWidth;
                TouchDelegate touchDelegate = new TouchDelegate(rect, view);
                parentView.setTouchDelegate(touchDelegate);
            }
        });
    }
}
