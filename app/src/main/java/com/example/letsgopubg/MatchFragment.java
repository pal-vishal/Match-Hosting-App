package com.example.letsgopubg;

import android.app.Activity;
import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letsgopubg.Models.MatchContent;

import java.util.ArrayList;
import java.util.List;

public class MatchFragment extends Fragment  {

    SeekBar seekBar;
    TextView slot;
    Button button;

    private RecyclerView recyclerView;
    private List<MatchContent> matchContentList;


    public MatchFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        matchContentList = new ArrayList<>();
        matchContentList.add(new MatchContent("ENTRY FEE:","TYPE:Solo","Date:07-04-2020","Time:\n7:20PM","Perkill:10","Winner:50","Battle Of 10"));
        matchContentList.add(new MatchContent("ENTRY FEE:","TYPE:Solo","Date:07-04-2020","Time:\n7:20PM","Perkill:10","Winner:50","Battle Of 10"));
        matchContentList.add(new MatchContent("ENTRY FEE:","TYPE:Solo","Date:07-04-2020","Time:\n7:20PM","Perkill:10","Winner:50","Battle Of 10"));
        matchContentList.add(new MatchContent("ENTRY FEE:","TYPE:Solo","Date:07-04-2020","Time:\n7:20PM","Perkill:10","Winner:50","Battle Of 10"));

    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_match, container, false);
        seekBar = (SeekBar) view.findViewById(R.id.SeekBar);
        slot = (TextView) view.findViewById(R.id.Slots);
        button = (Button) view.findViewById(R.id.btn1);
        recyclerView = (RecyclerView) view.findViewById(R.id.match_recycler);
        MatchContentAdapter matchContentAdapter = new MatchContentAdapter(getActivity(),matchContentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(matchContentAdapter);





        return view;
    }



}
