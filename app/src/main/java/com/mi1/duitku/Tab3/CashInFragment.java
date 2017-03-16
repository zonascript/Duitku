package com.mi1.duitku.Tab3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mi1.duitku.R;
import com.mi1.duitku.Tab1.NewsAdapter;

import java.security.KeyStore;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CashInFragment extends Fragment {

    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView recycler;
    private CashAdapter adapter;
    private ArrayList<CashInfo> cashInfos = new ArrayList<>();

    public CashInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_cashin, container, false);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler = (RecyclerView) view.findViewById(R.id.recycler_cash_in);
        recycler.setLayoutManager(mLinearLayoutManager);
        recycler.addItemDecoration(new DividerItemDecoration(getContext()));

        init();

        adapter = new CashAdapter(getActivity(), cashInfos);
        recycler.setAdapter(adapter);

        return view;
    }

    private void init() {
        cashInfos.add(new CashInfo("1.000.000", "09/23/2016"));
        cashInfos.add(new CashInfo("1.000.000", "09/23/2016"));
        cashInfos.add(new CashInfo("1.000.000", "09/23/2016"));
        cashInfos.add(new CashInfo("50.000", "09/23/2016"));
        cashInfos.add(new CashInfo("1.000.000", "09/23/2016"));
    }

}
