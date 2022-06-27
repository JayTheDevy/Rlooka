package com.example.ridalooka.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.ridalooka.R;

import java.util.ArrayList;
import java.util.List;


public class CarListGraphFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private List<DataEntry> dataEntries;

    public CarListGraphFragment() {
        // Required empty public constructor
    }

    public CarListGraphFragment(List<DataEntry> dataEntries) {
        this.dataEntries = dataEntries;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_list_graph, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("SUV", 34));
        data.add(new ValueDataEntry("Coupe", 12));
        data.add(new ValueDataEntry("Sports", 8));



        AnyChartView anyChartView = (AnyChartView) view.findViewById(R.id.chartView);
        anyChartView.setChart(getPieChart(data));



    }

    public void setDataEntries(List<DataEntry> dataEntries) {
        this.dataEntries = dataEntries;
    }

    private Pie getPieChart(List<DataEntry> dataEntries){
        Pie pie = AnyChart.pie();
        pie.data(dataEntries);

        return pie;
    }
}