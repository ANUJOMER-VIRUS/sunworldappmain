package com.monstertechno.loginsignupui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ImageFragmentAdapter extends FragmentStatePagerAdapter {

    String[] ImageSrc = new String[]{
            "https://www.google.com/imgres?imgurl=https%3A%2F%2F5.imimg.com%2Fdata5%2FANDROID%2FDefault%2F2022%2F4%2FMH%2FOD%2FZA%2F65784089%2F1650267331270-jpg-500x500.jpg&imgrefurl=https%3A%2F%2Fwww.indiamart.com%2Fgsrsteels%2Fwater-tank.html&tbnid=6lgd8l_UYPjJhM&vet=12ahUKEwjw1dfh7tn5AhUSKbcAHYwBAhcQMygAegQIARBZ..i&docid=AFH5bEfFhT06xM&w=415&h=500&q=sunworld%20water%20tank&ved=2ahUKEwjw1dfh7tn5AhUSKbcAHYwBAhcQMygAegQIARBZ",
            "https://www.google.com/imgres?imgurl=https%3A%2F%2Fi.ytimg.com%2Fvi%2FlS0lvqb7xHk%2Fmaxresdefault.jpg&imgrefurl=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3DlS0lvqb7xHk&tbnid=3lduVJdt3Oj6hM&vet=12ahUKEwiNrpi779n5AhUVidgFHbFcBFYQMygaegUIARCVAQ..i&docid=VcpXfOo40Po5dM&w=1280&h=720&q=sunworld%20water%20tank&ved=2ahUKEwiNrpi779n5AhUVidgFHbFcBFYQMygaegUIARCVAQ",
            "https://www.google.com/imgres?imgurl=https%3A%2F%2Fcdn.freebiesupply.com%2Flogos%2Flarge%2F2x%2Fsunworld-logo-png-transparent.png&imgrefurl=https%3A%2F%2Ffreebiesupply.com%2Flogos%2Fsunworld-logo%2F&tbnid=FItwv5NFEhDkYM&vet=12ahUKEwiNrpi779n5AhUVidgFHbFcBFYQMygWegUIARCIAQ..i&docid=okxO42pfwp9FbM&w=2400&h=2400&q=sunworld%20water%20tank&ved=2ahUKEwiNrpi779n5AhUVidgFHbFcBFYQMygWegUIARCIAQ",
            "https://www.google.com/imgres?imgurl=https%3A%2F%2Fcdn.freebiesupply.com%2Flogos%2Flarge%2F2x%2Fsunworld-logo-png-transparent.png&imgrefurl=https%3A%2F%2Ffreebiesupply.com%2Flogos%2Fsunworld-logo%2F&tbnid=FItwv5NFEhDkYM&vet=12ahUKEwiNrpi779n5AhUVidgFHbFcBFYQMygWegUIARCIAQ..i&docid=okxO42pfwp9FbM&w=2400&h=2400&q=sunworld%20water%20tank&ved=2ahUKEwiNrpi779n5AhUVidgFHbFcBFYQMygWegUIARCIAQ"

    };

    public ImageFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("source",ImageSrc[i]);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return ImageSrc.length;
    }
}
