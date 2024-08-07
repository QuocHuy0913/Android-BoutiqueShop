package com.example.doan.fragmentdanhmuc;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.doan.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tresosinhFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tresosinhFragment extends Fragment {
    ListView listView;
    String[] listData;
    ArrayAdapter<String> adapter;
    Context context;
    View mView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public tresosinhFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tresosinhFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static tresosinhFragment newInstance(String param1, String param2) {
        tresosinhFragment fragment = new tresosinhFragment();
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
        mView = inflater.inflate(R.layout.fragment_tresosinh, container, false);

        context = getContext();
        listView = mView.findViewById(R.id.lv_dm_tresosinh);
        listData = new String[]{"TRẺ SƠ SINH (0-3 THÁNG)", "TRẺ SƠ SINH (3-24 THÁNG)", "TRẺ NHỎ (6 THÁNG - 5 NĂM)"};
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);

        return mView;
    }
}