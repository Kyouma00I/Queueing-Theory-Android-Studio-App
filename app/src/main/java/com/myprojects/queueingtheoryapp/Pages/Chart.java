package com.myprojects.queueingtheoryapp.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.myprojects.queueingtheoryapp.R;

import java.util.ArrayList;


public class Chart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        try {

            BarChart barChart = findViewById(R.id.barChart);


            ArrayList<BarEntry> customers = new ArrayList<>();
            // Get the input Values
            ArrayList<Double> t = new ArrayList<>();
            for (double i = 0; i <= 100; i++) {
                t.add(i);
            }
            Bundle extras = getIntent().getExtras();
            double lambda = extras.getDouble("Lambda");
            double mu = extras.getDouble("Mu");
            double k = extras.getDouble("K");
            double m = extras.getDouble("M");

            // Get the value of X and Y axis
            ArrayList<Integer> number_of_customers = new ArrayList<>();
            for (int i = 0; i <= 100; i++) {
                int nt = 0;
                //////// First Case ///////
                if (lambda > mu) {
                    nt = (int) (lambda * t.get(i)) - (int) (mu * t.get(i) - mu / lambda);
                    if (nt > k - 1) {
                        break;
                    }
                }
                ///////////////////////////// Second Case //////////////////////////////////
                else if (lambda < mu) {
                    nt = (int) m + (int) Math.round(lambda * t.get(i)) - (int) Math.round(mu * t.get(i));
                    if (nt < 1) {
                        break;
                    }
                }
                number_of_customers.add(nt);
            }

            for (int i = 0; i < number_of_customers.size(); i++) {
                customers.add(new BarEntry(i, number_of_customers.get(i).floatValue()));
            }

            /////////////////////////////////////////   CUSTOMIZING THE CHART /////////////////////////////////////

            BarDataSet barDataSet = new BarDataSet(customers, "Number of customers in the queue");
            barDataSet.setColor(Color.BLUE);
            barDataSet.setValueTextColor(Color.BLACK);
            barDataSet.setValueTextSize(16f);

            BarData barData = new BarData(barDataSet);

            //showing the value of the bar, default true if not set
            barDataSet.setDrawValues(false);

            barChart.setFitBars(true);
            barChart.setData(barData);
            barChart.animateY(3000);
            barChart.animateX(1000);
            barChart.invalidate();
            // Hide gray background and shadow lines
            barChart.setDrawBarShadow(false);
            barChart.setDrawGridBackground(false);
            barChart.setDrawBorders(false);
            // Hide Description
            Description description = new Description();
            description.setEnabled(false);
            barChart.setDescription(description);

            // X Axis
            // change the position of x-axis to the bottom
            XAxis xAxis = barChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            //set the horizontal distance of the grid line
            xAxis.setGranularity(1f);

            //hiding the x-axis line, default true if not set
            xAxis.setDrawAxisLine(false);

            //hiding the vertical grid lines, default true if not set
            xAxis.setDrawGridLines(false);

            // Y Axis
            YAxis leftAxis = barChart.getAxisLeft();
            // Let Y axis be Integers on the left side
            leftAxis.setGranularity(1.0f);
            leftAxis.setGranularityEnabled(true);
            //hiding the left y-axis line, default true if not set
            leftAxis.setDrawAxisLine(false);

            YAxis rightAxis = barChart.getAxisRight();
            //hiding the right y-axis line, default true if not set
            rightAxis.setDrawAxisLine(false);
            // Let Y axis be Integers on the right side
            rightAxis.setGranularity(1.0f);
            rightAxis.setGranularityEnabled(true);

            Legend legend = barChart.getLegend();
            //setting the shape of the legend form to line, default square shape
            legend.setForm(Legend.LegendForm.LINE);
            //setting the text size of the legend
            legend.setTextSize(11f);
            //setting the alignment of legend toward the chart
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
            //setting the stacking direction of legend
            legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            //setting the location of legend outside the chart, default false if not set
            legend.setDrawInside(false);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter valid numbers!", Toast.LENGTH_LONG).show();
        }
    }
}