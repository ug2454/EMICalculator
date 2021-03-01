package com.example.caremicalculator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {
    TextView emiTextView, interestAmountTextView, totalPaymentTextView;

    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();


        emiTextView = findViewById(R.id.emiTextView);
        interestAmountTextView = findViewById(R.id.interestAmountTextView);
        totalPaymentTextView = findViewById(R.id.totalPaymentTextView);
        Locale indialocale = new Locale("en", "IN");
        NumberFormat india = NumberFormat.getCurrencyInstance(indialocale);

        String emi = india.format((int) intent.getFloatExtra("emi", 0));
        String[] emi1 = emi.split("\\.");

        String interest = india.format((int) intent.getFloatExtra("interestAmount", 0));
        String[] interest1 = interest.split("\\.");
        String totalPayment = india.format((int) intent.getFloatExtra("totalAmount", 0));
        String[] totalPayment1 = totalPayment.split("\\.");

//
//        pieChart.addPieSlice(
//                new PieModel(
//                        String.valueOf((int) intent.getFloatExtra("interestAmount", 0)),
//                        Integer.parseInt(String.valueOf((int) intent.getFloatExtra("interestAmount", 0))),
//                        Color.parseColor("#66BB6A")));
//        pieChart.addPieSlice(
//                new PieModel(
//                        String.valueOf(intent.getFloatExtra("principalAmount", 0)),
//                        Integer.parseInt(String.valueOf((int) intent.getIntExtra("principalAmount", 0))),
//                        Color.parseColor("#EF5350")));
//
//
//
//        pieChart.setInnerValueString(String.valueOf(intent.getFloatExtra("totalAmount", 0)));
//
//
//        pieChart.startAnimation();
        pieChart = findViewById(R.id.pieChart);
        pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry((int) intent.getFloatExtra("interestAmount", 0), Integer.parseInt(String.valueOf((int) intent.getFloatExtra("interestAmount", 0)))));
        pieEntries.add(new PieEntry(intent.getIntExtra("principalAmount", 0), Integer.parseInt(String.valueOf(intent.getIntExtra("principalAmount", 0)))));

        pieDataSet = new PieDataSet(pieEntries, "");
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(10f);
        pieDataSet.setSliceSpace(5f);
        pieChart.getDescription().setText("LOAN PAYMENT CHART");
        pieChart.getDescription().setTextColor(Color.YELLOW);
        pieChart.getDescription().setTextSize(12);
        pieChart.setCenterText(totalPayment1[0]);
        Legend legend = pieChart.getLegend();
        legend.setEnabled(true);

        setLegends();

        pieChart.animate();

        emiTextView.setText(emi1[0]);
        interestAmountTextView.setText(interest1[0]);
        totalPaymentTextView.setText(totalPayment1[0]);
    }

    public void setLegends() {

        Legend l = pieChart.getLegend();

        l.getEntries();

        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);

        l.setYEntrySpace(10f);

        l.setWordWrapEnabled(true);

        LegendEntry l1 = new LegendEntry("Principal Loan Amount", Legend.LegendForm.CIRCLE, 10f, 2f, null, Color.YELLOW);
        LegendEntry l2 = new LegendEntry("Interest Payable", Legend.LegendForm.CIRCLE, 10f, 2f, null, Color.GREEN);

        l.setCustom(new LegendEntry[]{l1, l2});
        l.setTextColor(Color.WHITE);

        l.setEnabled(true);

    }
}