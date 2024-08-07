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
 * Use the {@link treemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class treemFragment extends Fragment {
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

    public treemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment treemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static treemFragment newInstance(String param1, String param2) {
        treemFragment fragment = new treemFragment();
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
        mView = inflater.inflate(R.layout.fragment_treem, container, false);

        context = getContext();
        listView = mView.findViewById(R.id.lv_dm_treem);
        listData = new String[]{"ĐỒ MẶC NGOÀI", "ÁO", "QUẦN", "ĐẦM & ĐẦM YẾM", "ĐỒ MẶC TRONG & ĐỒ LÓT", "ĐỒ MẶC NHÀ", "PHỤ KIỆN", "SPORT UTILITY WEAR"};
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);

        return mView;
    }
}