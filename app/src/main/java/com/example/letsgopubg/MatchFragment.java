package com.example.letsgopubg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MatchFragment extends Fragment {

    SeekBar seekBar;
    TextView slot;
    Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_match, container, false);
        seekBar = (SeekBar) view.findViewById(R.id.SeekBar);
        slot = (TextView) view.findViewById(R.id.Slots);
        button = (Button) view.findViewById(R.id.btn1);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = 10-progress;

                slot.setText(progress +" Slots left");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = seekBar.getProgress();
                for (int i = 0; i <= 1; i++) {
                    seekBar.setProgress(index + 1);
                }
            }
        });

        return view;
    }


}
