package com.example.doan.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.doan.Yeuthich;
import com.example.doan.adapter.yeuthichAdapter;

import com.example.doan.R;
import com.example.doan.utils.Utilsss;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link YeuthichFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YeuthichFragment extends Fragment {
    LinearLayout yeuthichtrong;
    RecyclerView recyclerView;
    TextView soluongSP;

    yeuthichAdapter adapter;


    View mView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public YeuthichFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment YeuthichFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static YeuthichFragment newInstance(String param1, String param2) {
        YeuthichFragment fragment = new YeuthichFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_yeuthich, container, false);

        yeuthichtrong = mView.findViewById(R.id.linearYeuthich);
        recyclerView = mView.findViewById(R.id.rcvYeuthich);
        soluongSP = mView.findViewById(R.id.tvcacsp);


        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        soluongSP.setText(Utilsss.mangyeuthich.size() + " (CÁC) SẢN PHẨM");

        if(Utilsss.mangyeuthich.size() == 0){
            yeuthichtrong.setVisibility(View.VISIBLE);
        }else {
            adapter = new yeuthichAdapter(getContext(), Utilsss.mangyeuthich);
            recyclerView.setAdapter(adapter);
        }
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        soluongSP.setText(Utilsss.mangyeuthich.size() + " (CÁC) SẢN PHẨM");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}