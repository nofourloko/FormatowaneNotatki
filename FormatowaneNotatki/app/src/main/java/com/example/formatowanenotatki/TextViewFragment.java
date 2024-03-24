package com.example.formatowanenotatki;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TextViewFragment extends Fragment {
    public String text;
    private long startTimeMillis;
    private TextView timerTextView, textLength;
    private Handler timerHandler = new Handler();
    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTimeMillis;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timerTextView.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);
        }
    };
    public TextViewFragment() {}




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text_view, container, false);
        TextView textView = view.findViewById(R.id.textViewDisplayedText);
        timerTextView = view.findViewById(R.id.timerTextView);
        textLength = view.findViewById(R.id.textLength);

        text = String.valueOf(textView.getText());
        DataViewModel dataVM = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        startTimeMillis = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);

        dataVM.getData()
                .observe(
                        getViewLifecycleOwner(), s -> {
                            text = s;
                            textLength.setText("Dlugość tekstu : " + String.valueOf(s.length()));
                            textView.setText(text);
                        }
                );

        dataVM.getStyleData()
                .observe(
                        getViewLifecycleOwner(), s -> {
                            if(text.equals("")){return;}
                            Spannable spn = new SpannableString(text);

                            if(s.equals("style_1")){
                                spn.setSpan(new ForegroundColorSpan(Color.GREEN), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                spn.setSpan(new StyleSpan(Typeface.ITALIC), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                spn.setSpan(new RelativeSizeSpan(2f), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            } else if (s.equals("style_2")) {
                                spn.setSpan(new ForegroundColorSpan(Color.RED), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                spn.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                spn.setSpan(new RelativeSizeSpan(1f), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            } else if (s.equals("style_3")) {
                                spn.setSpan(new ForegroundColorSpan(Color.BLUE), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                spn.setSpan(new RelativeSizeSpan(0.5f), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                spn.setSpan(new StyleSpan(Typeface.BOLD), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }

                            textView.setText(spn);

                        }
                );


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timerHandler.removeCallbacks(timerRunnable);
    }
}