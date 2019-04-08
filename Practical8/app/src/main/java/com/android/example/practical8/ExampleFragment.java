package com.android.example.practical8;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;


public class ExampleFragment extends Fragment {

    private static final String KEY_NUMBER = "number_message";

    public ExampleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_example, container, false);
        AppCompatTextView numberMessageTextView = view.findViewById(R.id.number_message_text_view);
        numberMessageTextView.setText(Objects.requireNonNull(getArguments()).getString(KEY_NUMBER));
        return view;
    }

    public static Fragment newInstance(String number) {
        Bundle args = new Bundle();
        args.putString(KEY_NUMBER, number);
        ExampleFragment fragment = new ExampleFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
