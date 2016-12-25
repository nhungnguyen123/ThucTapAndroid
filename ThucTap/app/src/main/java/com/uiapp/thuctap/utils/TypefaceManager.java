package com.uiapp.thuctap.utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import com.uiapp.thuctap.MainApplication;

/**
 * Created by hongnhung on 7/18/16.
 */
public class TypefaceManager {
    public static final int BOLD = 1;
    public static final int BOLD_ITALIC = 2;
    public static final int EXTRA_BOLD = 3;
    public static final int EXTRA_BOLD_ITALIC = 4;
    public static final int ITALIC = 5;
    public static final int LIGHT = 6;
    public static final int LIGHT_ITALIC = 7;
    public static final int REGULAR = 8;
    public static final int SEMI_BOLD = 9;
    public static final int SEMI_BOLD_ITALIC = 10;
    public static final int ROBOTO_BOLD = 11;

    public TypefaceManager() {
    }

    public static Typeface getTypeface(int type) {
        String path = "";

        switch (type) {
            case BOLD:
                path = "fonts/OpenSans-Bold.ttf";
                break;
            case BOLD_ITALIC:
                path = "fonts/OpenSans-BoldItalic.ttf";
                break;
            case EXTRA_BOLD:
                path = "fonts/OpenSans-ExtraBold.ttf";
                break;
            case EXTRA_BOLD_ITALIC:
                path = "fonts/OpenSans-ExtraBoldItalic.ttf";
                break;
            case ITALIC:
                path = "fonts/OpenSans-Italic.ttf";
                break;
            case LIGHT:
                path = "fonts/OpenSans-Light.ttf";
                break;
            case LIGHT_ITALIC:
                path = "fonts/OpenSans-LightItalic.ttf";
                break;
            case REGULAR:
                path = "fonts/OpenSans-Regular.ttf";
                break;
            case SEMI_BOLD:
                path = "fonts/OpenSans-Semibold.ttf";
                break;
            case SEMI_BOLD_ITALIC:
                path = "fonts/OpenSans-SemiboldItalic.ttf";
                break;
            case ROBOTO_BOLD:
                path="fonts/RobotoTTF/Roboto-Bold.ttf";
                break;
        }

        AssetManager assetManager = MainApplication.getContext().getAssets();
        Typeface typeface = Typeface.createFromAsset(assetManager, path);
        return typeface;
    }
}
