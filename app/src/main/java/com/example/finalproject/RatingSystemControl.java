package com.example.finalproject;

import android.content.Context;

import com.example.finalproject.RatingDatabase.RatingSystemHelper;

import java.util.ArrayList;
import java.util.List;

public class RatingSystemControl {

    private static RatingSystemControl mRatingControl;
    private Context mContext;
    private RatingSystemHelper mRatingHelper;

    public static RatingSystemControl get(Context context) {
        if(mRatingControl == null) {
            mRatingControl = new RatingSystemControl(context);
        }
        return mRatingControl;
    }

    private RatingSystemControl(Context context) {
        mContext = context.getApplicationContext();
        mRatingHelper = new RatingSystemHelper(mContext);
    }

    public long addRating(RatingSystem rate) {
        return mRatingHelper.addRating(rate);
    }

    public List<RatingSystem> killme(user User) {
        List<RatingSystem> all = mRatingHelper.getRating();
        List<RatingSystem> wow = new ArrayList<>();

        for(RatingSystem r : all) {
            if(r.getUserID().equals(User.getUser())) {
                wow.add(r);
            }
        }

        return wow;
    }

    public List<RatingSystem> killme() {
        return mRatingHelper.getRating();

    }
}
