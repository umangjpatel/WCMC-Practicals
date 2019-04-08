package com.android.example.practical8;

import java.util.ArrayList;

class NumbersData {

    public static  NumbersData sNumbersData;

    private ArrayList<String> mNumbersList;

    public static NumbersData getInstance() {
        if (sNumbersData == null)
            sNumbersData = new NumbersData();
        return sNumbersData;
    }

    private NumbersData() {
        mNumbersList = new ArrayList<>();
        mNumbersList.add("Page 1");
        mNumbersList.add("Page 2");
        mNumbersList.add("Page 3");
        mNumbersList.add("Page 4");
        mNumbersList.add("Page 5");
        mNumbersList.add("Page 6");
    }

    public int getDataLength() {
        return mNumbersList.size();
    }

    public String getNumbersDataAtIndex(int index) {
        return mNumbersList.get(index);
    }
}
