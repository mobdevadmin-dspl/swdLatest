package com.datamation.swdsfa.view.dashboard;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.datamation.swdsfa.R;
import com.datamation.swdsfa.controller.DashboardController;
import com.datamation.swdsfa.controller.FItenrDetController;
import com.datamation.swdsfa.controller.RouteDetController;
import com.datamation.swdsfa.helpers.SharedPref;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.renderer.PieChartRenderer;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.RandomAccessFile;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by kaveesha - 23-03-2022
 */
public class MainDashboardFragmentNew extends Fragment {

    private static final String LOG_TAG = MainDashboardFragmentNew.class.getSimpleName();

    private HorizontalBarChart cumulativeLineChart;
    private NumberFormat numberFormat = NumberFormat.getInstance();
    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm aaa", Locale.getDefault());

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.getDefault());

    private ArrayList<Double> targetValues;
    private List<Double> achievementValues;

    BarChart chart,groupBarChart ;
    double precentage;
    ImageView menu;
    ArrayList<String> targetItemList;
    CheckBox chCategoryView;
    RadioGroup radioGroup;
    RadioButton rdCase,rdPiece,rdTonnage,rdValue;
    public DatePickerDialog datePickerDialogfrom,datePickerDialogTo;


//    private IOnDashboardFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MainDashboardFragment.
     */
    public static MainDashboardFragmentNew newInstance() {
//        MainDashboardFragment fragment = new MainDashboardFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return new MainDashboardFragmentNew();
    }

    public MainDashboardFragmentNew() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_dashboard, container, false);
        menu = (ImageView) rootView.findViewById(R.id.menu);

        cumulativeLineChart = (HorizontalBarChart) rootView.findViewById(R.id.dashboard_hBarChart);
        targetValues = new ArrayList<Double>();
        cumulativeLineChart.setDrawGridBackground(false);
        cumulativeLineChart.setPinchZoom(true);

        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setGroupingUsed(true);

        chart = rootView.findViewById(R.id.barChart);
        groupBarChart = rootView.findViewById(R.id.groupBarChart);
        chCategoryView = rootView.findViewById(R.id.chCategoryWise);
        radioGroup = rootView.findViewById(R.id.groupradio);
        rdCase = rootView.findViewById(R.id.rdCase);
        rdPiece = rootView.findViewById(R.id.rdPieces);
        rdTonnage = rootView.findViewById(R.id.rdTonnage);
        rdValue = rootView.findViewById(R.id.rdValue);

        if(chCategoryView.isChecked()){
            groupBarChart.setVisibility(View.VISIBLE);
            chart.setVisibility(View.GONE);
        }else{
            chart.setVisibility(View.VISIBLE);
            groupBarChart.setVisibility(View.GONE);
        }

        chCategoryView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(chCategoryView.isChecked()){
                    groupBarChart.setVisibility(View.VISIBLE);
                    chart.setVisibility(View.GONE);

                }else{
                    chart.setVisibility(View.VISIBLE);
                    groupBarChart.setVisibility(View.GONE);

                }
            }
        });

        if(rdCase.isChecked()){

        }else if(rdPiece.isChecked()){

        }else if(rdTonnage.isChecked()){

        }else if(rdValue.isChecked()){

        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rdCase.isChecked()){

                }else if(rdPiece.isChecked()){

                }else if(rdTonnage.isChecked()){

                }else if(rdValue.isChecked()){

                }
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TargetDetailDialog(getActivity());
            }
        });

        double dailyAchieve = new DashboardController(getActivity()).getDailyAchievement();
        double monthlyAchieve = new DashboardController(getActivity()).getMonthAchievement();
        double monthlyTarget = new DashboardController(getActivity()).getRepTarget();
        double monthlyBalance = monthlyTarget - monthlyAchieve;
        if(monthlyBalance<0){
            monthlyBalance = 0;
        }
        double dailyTarget = new DashboardController(getActivity()).getRepTarget()/30;
        double monthlyAchieveInv = 0;
        if(!SharedPref.getInstance(getActivity()).getTMInvSale().equals("0")) {
            monthlyAchieveInv = Double.parseDouble(SharedPref.getInstance(getActivity()).getTMInvSale());
        }else{
            monthlyAchieveInv = 0;
        }
        double monthlyReturn = 0;
        if(!SharedPref.getInstance(getActivity()).getTMReturn().equals("0")) {
            monthlyReturn = Double.parseDouble(SharedPref.getInstance(getActivity()).getTMReturn());
        }else{
            monthlyReturn = 0;
        }
        double monthlyBalanceInv = monthlyTarget - monthlyAchieveInv;

        //bar chart

        List<Integer> entries = new ArrayList<>();

        if(monthlyTarget == 0){
            entries.add(0);
        }else{
            entries.add((int)monthlyTarget);
        }

      if(monthlyAchieve == 0){
          entries.add(0);
      }else{
          entries.add((int)monthlyAchieve);
      }

      if(monthlyAchieveInv == 0){
            entries.add(0);
      }else{
            entries.add((int)monthlyAchieveInv);
      }

      if(monthlyReturn == 0){
            entries.add(0);
      }else{
            entries.add((int)monthlyReturn);
      }

      if(monthlyBalanceInv == 0){
            entries.add(0);
      }else{
            entries.add((int)monthlyBalanceInv);
      }


        List<String> labels = new ArrayList<>();

        labels.add("Booking Target");
        labels.add("Booking");
        labels.add("RD Invoice");
        labels.add("Cancellations");
        labels.add("RD Balance");


        create_graph(labels,entries);

        //---------------------------- horizontal bar chart ----------------------------------------------------------------------

        int nonprd = new DashboardController(getActivity()).getNonPrdCount();
        int ordcount = new DashboardController(getActivity()).getProductiveCount();
        String route = "";
        int curYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        int curMonth = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
        int curDate = Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));

        String curdate = curYear+"-"+ String.format("%02d", curMonth) + "-" + String.format("%02d", curDate);
        if(!new FItenrDetController(getActivity()).getRouteFromItenary(curdate).equals("")) {
            route = new DashboardController(getActivity()).getRoute();
        }else{
            route = new RouteDetController(getActivity()).getRouteCodeByDebCode(SharedPref.getInstance(getActivity()).getSelectedDebCode());
        }
        int outlets = new DashboardController(getActivity()).getOutletCount(route);
        int notVisit = outlets - (ordcount+nonprd);
        if(notVisit > 0){
            notVisit = outlets - (ordcount+nonprd);
        }else{
            notVisit = 0;
        }


        // PREPARING THE ARRAY LIST OF BAR ENTRIES
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1f, ordcount));
        barEntries.add(new BarEntry(2f, notVisit));
        barEntries.add(new BarEntry(3f, nonprd));


        // TO ADD THE VALUES IN X-AXIS
        ArrayList<String> xAxisName = new ArrayList<>();

        xAxisName.add("visit("+ordcount+")");
        xAxisName.add("not visit("+notVisit+")");
        xAxisName.add("nonproductive("+nonprd+")");


        barchart(cumulativeLineChart,barEntries,xAxisName);


        //--------------------------------------- Pie Chart -----------------------------------------------------------------
        PieChart pieChart = rootView.findViewById(R.id.piechart);
        ArrayList<PieEntry> pieChartValues = new ArrayList<>();


        if(dailyAchieve == 0 && dailyTarget == 0){
            precentage = 0;
        }else {
            precentage = (dailyAchieve / dailyTarget) * 100;
        }

        pieChart.getDescription().setText("Item Name \n\n Sales Order - Cases");
        pieChart.setCenterText(Math.round(precentage) + " %");
        pieChart.setCenterTextSize(36);
        pieChart.setCenterTextColor(Color.BLUE);
        pieChart.setCenterText(generateCenterSpannableText());
        pieChart.setHoleRadius(75f);
        pieChart.setTransparentCircleRadius(60f);
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.getLegend().setEnabled(false);
        pieChart.setRotationEnabled(false);
        pieChart.setTouchEnabled(false);
        pieChart.animateX(400);
        pieChart.setDrawSliceText(true);
        pieChart.setDrawRoundedSlices(true);


        pieChartValues.add(0, new PieEntry((float)dailyTarget));
        pieChartValues.add(1,new PieEntry((float)dailyAchieve));

        PieDataSet dataSet = new PieDataSet(pieChartValues, "(-values-)");

        ArrayList title = new ArrayList();

        title.add("Target");
        title.add("Achievement");

        PieData dataPie = new PieData(dataSet);
        pieChart.setData(dataPie);
        dataSet.setColors(new int[]{ContextCompat.getColor(getActivity(), R.color.light_green),
                ContextCompat.getColor(getActivity(),R.color.green )});
        pieChart.animateXY(3000, 3000);
        pieChart.setRenderer(new RoundedSlicesPieChartRenderer(pieChart, pieChart.getAnimator(), pieChart.getViewPortHandler()));

        return rootView;
    }

    public void create_graph(List<String> graph_label, List<Integer> graph_values) {

        try {
            chart.setDrawBarShadow(false);
            chart.setDrawValueAboveBar(true);
            chart.getDescription().setEnabled(false);
            chart.setPinchZoom(true);

            chart.setDrawGridBackground(false);


            YAxis yAxis = chart.getAxisLeft();

            yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
            yAxis.setStartAtZero(true);


            YAxis axisRight = chart.getAxisRight();
            axisRight.setStartAtZero(true);    //Get the label on the left y-axis

            chart.getAxisRight().setEnabled(true);
            chart.getAxisLeft().setEnabled(true);


            XAxis xAxis = chart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setGranularityEnabled(true);
            xAxis.setCenterAxisLabels(true);
            xAxis.setDrawGridLines(false);

            xAxis.setPosition(XAxis.XAxisPosition.TOP);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(graph_label));

            List<BarEntry> yVals1 = new ArrayList<BarEntry>();

            for (int i = 0; i < graph_values.size(); i++) {
                yVals1.add(new BarEntry(i, graph_values.get(i)));
            }


            BarDataSet set1;

            if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
                set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
                set1.setValues(yVals1);
                chart.getData().notifyDataChanged();
                chart.notifyDataSetChanged();
            } else {
                // create 2 datasets with different types
                set1 = new BarDataSet(yVals1, "-values-");

                set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
                set1.setColors(new int[]{ContextCompat.getColor(getActivity(), R.color.red_error),
                        ContextCompat.getColor(getActivity(), R.color.material_alert_positive_button),
                        ContextCompat.getColor(getActivity(), R.color.rd_invoice),
                        ContextCompat.getColor(getActivity(), R.color.half_black),
                        ContextCompat.getColor(getActivity(), R.color.theme_color_dark)});
                set1.setDrawValues(false);
                chart.animateY(2000);
                chart.setDrawGridBackground(false);
                chart.getXAxis().setDrawGridLines(false);

                ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
                dataSets.add(set1);

                BarData data = new BarData(dataSets);
                chart.setData(data);


            }

            chart.setFitBars(true);

            Legend l = chart.getLegend();
            l.setFormSize(12f); // set the size of the legend forms/shapes
            l.setForm(Legend.LegendForm.SQUARE); // set what type of form/shape should be used

            l.setTextSize(10f);
            l.setTextColor(Color.BLACK);
            l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
            l.setYEntrySpace(5f); // set the space between the legend entries on the y-axis

            chart.invalidate();

            chart.animateY(2000);

        } catch (Exception ignored) {
        }
    }

    public void barchart(BarChart barChart, ArrayList<BarEntry> arrayList, final ArrayList<String> xAxisValues) {

        barChart.animateXY(2000, 2000);
        barChart.setDrawGridBackground(false);
        barChart.setDrawBorders(false);
        barChart.setDrawValueAboveBar(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.invalidate();
        barChart.getDescription().setEnabled(false);
        barChart.setFitBars(false);


        BarDataSet barDataSet = new BarDataSet(arrayList, "Values");
        barDataSet.setColors(new int[]{ContextCompat.getColor(getActivity(), R.color.main_green_stroke_color),
                ContextCompat.getColor(getActivity(), R.color.theme_color_dark),
                ContextCompat.getColor(getActivity(), R.color.visit_not_visited)});

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.5f);
        barData.setValueTextSize(0f);

        barChart.setBackgroundColor(Color.TRANSPARENT);
        barChart.setDrawGridBackground(false);

        Legend l = barChart.getLegend();
        l.setTextSize(10f);
        l.setFormSize(10f);

        //To set components of x axis
        XAxis xAxis = barChart.getXAxis();
        xAxis.setTextSize(13f);
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        xAxis.setDrawGridLines(true);
        xAxis.setDrawGridLinesBehindData(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawLabels(true);
      //  xAxis.setLabelCount(4,true);




        barChart.setData(barData);

    }


    private SpannableString generateCenterSpannableText() {

        if(precentage == 0){
            String p = Math.round(precentage) + " %" ;
            SpannableString s = new SpannableString(p);
            s.setSpan(new RelativeSizeSpan(4f), 0, 3, 0);
            s.setSpan(new StyleSpan(Typeface.BOLD), 0, 3, 0);
            s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 3 , 0);
            s.setSpan(new RelativeSizeSpan(.8f), 0, 3, 0);
            return s;
        }else{
            String p = Math.round(precentage) + " %" ;
            SpannableString s = new SpannableString(p);
            s.setSpan(new RelativeSizeSpan(4f), 0, 4, 0);
            s.setSpan(new StyleSpan(Typeface.BOLD), 0, 4, 0);
            s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 4 , 0);
            s.setSpan(new RelativeSizeSpan(.8f), 0, 4, 0);
            return s;
        }

    }

    public class RoundedSlicesPieChartRenderer extends PieChartRenderer {
        public RoundedSlicesPieChartRenderer(PieChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
            super(chart, animator, viewPortHandler);

            chart.setDrawRoundedSlices(true);
        }

        @Override
        protected void drawDataSet(Canvas c, IPieDataSet dataSet) {
            float angle = 0;
            float rotationAngle = mChart.getRotationAngle();

            float phaseX = mAnimator.getPhaseX();
            float phaseY = mAnimator.getPhaseY();

            final RectF circleBox = mChart.getCircleBox();

            final int entryCount = dataSet.getEntryCount();
            final float[] drawAngles = mChart.getDrawAngles();
            final MPPointF center = mChart.getCenterCircleBox();
            final float radius = mChart.getRadius();
            final boolean drawInnerArc = mChart.isDrawHoleEnabled() && !mChart.isDrawSlicesUnderHoleEnabled();
            final float userInnerRadius = drawInnerArc
                    ? radius * (mChart.getHoleRadius() / 100.f)
                    : 0.f;
            final float roundedRadius = (radius - (radius * mChart.getHoleRadius() / 100f)) / 2f;
            final RectF roundedCircleBox = new RectF();

            int visibleAngleCount = 0;
            for (int j = 0; j < entryCount; j++) {
                // draw only if the value is greater than zero
                if ((Math.abs(dataSet.getEntryForIndex(j).getY()) > Utils.FLOAT_EPSILON)) {
                    visibleAngleCount++;
                }
            }

            final float sliceSpace = visibleAngleCount <= 1 ? 0.f : getSliceSpace(dataSet);
            final Path pathBuffer = new Path();
            final RectF mInnerRectBuffer = new RectF();

            for (int j = 0; j < entryCount; j++) {
                float sliceAngle = drawAngles[j];
                float innerRadius = userInnerRadius;

                Entry e = dataSet.getEntryForIndex(j);

                // draw only if the value is greater than zero
                if (!(Math.abs(e.getY()) > Utils.FLOAT_EPSILON)) {
                    angle += sliceAngle * phaseX;
                    continue;
                }

                // Don't draw if it's highlighted, unless the chart uses rounded slices
                if (mChart.needsHighlight(j) && !drawInnerArc) {
                    angle += sliceAngle * phaseX;
                    continue;
                }

                final boolean accountForSliceSpacing = sliceSpace > 0.f && sliceAngle <= 180.f;

                mRenderPaint.setColor(dataSet.getColor(j));

                final float sliceSpaceAngleOuter = visibleAngleCount == 1 ?
                        0.f :
                        sliceSpace / (Utils.FDEG2RAD * radius);
                final float startAngleOuter = rotationAngle + (angle + sliceSpaceAngleOuter / 2.f) * phaseY;
                float sweepAngleOuter = (sliceAngle - sliceSpaceAngleOuter) * phaseY;

                if (sweepAngleOuter < 0.f) {
                    sweepAngleOuter = 0.f;
                }

                pathBuffer.reset();

                float arcStartPointX = center.x + radius * (float) Math.cos(startAngleOuter * Utils.FDEG2RAD);
                float arcStartPointY = center.y + radius * (float) Math.sin(startAngleOuter * Utils.FDEG2RAD);

                if (sweepAngleOuter >= 360.f && sweepAngleOuter % 360f <= Utils.FLOAT_EPSILON) {
                    // Android is doing "mod 360"
                    pathBuffer.addCircle(center.x, center.y, radius, Path.Direction.CW);
                } else {
                    if (drawInnerArc) {
                        float x = center.x + (radius - roundedRadius) * (float) Math.cos(startAngleOuter * Utils.FDEG2RAD);
                        float y = center.y + (radius - roundedRadius) * (float) Math.sin(startAngleOuter * Utils.FDEG2RAD);

                        roundedCircleBox.set(x - roundedRadius, y - roundedRadius, x + roundedRadius, y + roundedRadius);
                        pathBuffer.arcTo(roundedCircleBox, startAngleOuter - 180, 180);
                    }

                    pathBuffer.arcTo(
                            circleBox,
                            startAngleOuter,
                            sweepAngleOuter
                    );
                }

                // API < 21 does not receive floats in addArc, but a RectF
                mInnerRectBuffer.set(
                        center.x - innerRadius,
                        center.y - innerRadius,
                        center.x + innerRadius,
                        center.y + innerRadius);

                if (drawInnerArc && (innerRadius > 0.f || accountForSliceSpacing)) {

                    if (accountForSliceSpacing) {
                        float minSpacedRadius =
                                calculateMinimumRadiusForSpacedSlice(
                                        center, radius,
                                        sliceAngle * phaseY,
                                        arcStartPointX, arcStartPointY,
                                        startAngleOuter,
                                        sweepAngleOuter);

                        if (minSpacedRadius < 0.f)
                            minSpacedRadius = -minSpacedRadius;

                        innerRadius = Math.max(innerRadius, minSpacedRadius);
                    }

                    final float sliceSpaceAngleInner = visibleAngleCount == 1 || innerRadius == 0.f ?
                            0.f :
                            sliceSpace / (Utils.FDEG2RAD * innerRadius);
                    final float startAngleInner = rotationAngle + (angle + sliceSpaceAngleInner / 2.f) * phaseY;
                    float sweepAngleInner = (sliceAngle - sliceSpaceAngleInner) * phaseY;
                    if (sweepAngleInner < 0.f) {
                        sweepAngleInner = 0.f;
                    }
                    final float endAngleInner = startAngleInner + sweepAngleInner;

                    if (sweepAngleOuter >= 360.f && sweepAngleOuter % 360f <= Utils.FLOAT_EPSILON) {
                        // Android is doing "mod 360"
                        pathBuffer.addCircle(center.x, center.y, innerRadius, Path.Direction.CCW);
                    } else {
                        float x = center.x + (radius - roundedRadius) * (float) Math.cos(endAngleInner * Utils.FDEG2RAD);
                        float y = center.y + (radius - roundedRadius) * (float) Math.sin(endAngleInner * Utils.FDEG2RAD);

                        roundedCircleBox.set(x - roundedRadius, y - roundedRadius, x + roundedRadius, y + roundedRadius);

                        pathBuffer.arcTo(roundedCircleBox, endAngleInner, 180);
                        pathBuffer.arcTo(mInnerRectBuffer, endAngleInner, -sweepAngleInner);
                    }
                } else {

                    if (sweepAngleOuter % 360f > Utils.FLOAT_EPSILON) {
                        if (accountForSliceSpacing) {

                            float angleMiddle = startAngleOuter + sweepAngleOuter / 2.f;

                            float sliceSpaceOffset =
                                    calculateMinimumRadiusForSpacedSlice(
                                            center,
                                            radius,
                                            sliceAngle * phaseY,
                                            arcStartPointX,
                                            arcStartPointY,
                                            startAngleOuter,
                                            sweepAngleOuter);

                            float arcEndPointX = center.x +
                                    sliceSpaceOffset * (float) Math.cos(angleMiddle * Utils.FDEG2RAD);
                            float arcEndPointY = center.y +
                                    sliceSpaceOffset * (float) Math.sin(angleMiddle * Utils.FDEG2RAD);

                            pathBuffer.lineTo(
                                    arcEndPointX,
                                    arcEndPointY);

                        } else {
                            pathBuffer.lineTo(
                                    center.x,
                                    center.y);
                        }
                    }

                }

                pathBuffer.close();

                mBitmapCanvas.drawPath(pathBuffer, mRenderPaint);

                angle += sliceAngle * phaseX;
            }

            MPPointF.recycleInstance(center);
        }
    }

    public void TargetDetailDialog(final Context context) {
        final Dialog targetDetDialog = new Dialog(context);
        targetDetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        targetDetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        targetDetDialog.setCancelable(false);
        targetDetDialog.setCanceledOnTouchOutside(false);
        targetDetDialog.setContentView(R.layout.target_details);

        //initializations

        final SearchableSpinner spTargetItem = (SearchableSpinner) targetDetDialog.findViewById(R.id.spTargetItem) ;
        final TextView itemName = (TextView) targetDetDialog.findViewById(R.id.targetItemName) ;
        final ImageView btnFromDate = (ImageView) targetDetDialog.findViewById(R.id.image_view_date_select_from) ;
        final ImageView btnToDate = (ImageView) targetDetDialog.findViewById(R.id.image_view_date_select_to) ;
        final TextView fromDate = (TextView) targetDetDialog.findViewById(R.id.fromDate) ;
        final TextView toDate = (TextView) targetDetDialog.findViewById(R.id.toDate) ;
        final RadioButton rdSales = (RadioButton) targetDetDialog.findViewById(R.id.rdSalesOrder) ;
        final RadioButton rdInvoice = (RadioButton) targetDetDialog.findViewById(R.id.rdInvoice) ;
        final RadioButton rdCase = (RadioButton) targetDetDialog.findViewById(R.id.rdCase) ;
        final RadioButton rdPiece = (RadioButton) targetDetDialog.findViewById(R.id.rdPieces) ;
        final RadioButton rdTonnage = (RadioButton) targetDetDialog.findViewById(R.id.rdTonnage) ;
        final RadioButton rdValue = (RadioButton) targetDetDialog.findViewById(R.id.rdValue) ;
        final RadioGroup radioGroup1 = (RadioGroup) targetDetDialog.findViewById(R.id.groupradio) ;
        final RadioGroup radioGroup2 = (RadioGroup) targetDetDialog.findViewById(R.id.groupradioCategory) ;


        //------------------------------- Set from date --------------------------------------------------------
        btnFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePickerDialogfrom = new DatePickerDialog();
                datePickerDialogfrom.setThemeDark(false);
                datePickerDialogfrom.showYearPickerFirst(false);
                datePickerDialogfrom.setAccentColor(Color.parseColor("#0072BA"));
                datePickerDialogfrom.show(getActivity().getFragmentManager(),"DatePickerDialog");
                datePickerDialogfrom.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        // String date =  "Select Date : " + day + " - " + month + " - " + year;
                        String datesaveFrom = "";
                        if(String.valueOf(monthOfYear+1).length()<2 && String.valueOf(dayOfMonth).length()<2){
                            datesaveFrom = "" + year + "-" + "0"+(monthOfYear+1) + "-" + "0"+dayOfMonth ;
                        }else{
                            if(String.valueOf(monthOfYear+1).length()<2){
                                datesaveFrom = "" + year + "-" +"0"+(monthOfYear+1) +"-" + dayOfMonth ;
                            }else if(String.valueOf(dayOfMonth).length()<2){
                                datesaveFrom = "" + year + "-" +(monthOfYear+1) + "-" + "0"+dayOfMonth ;
                            }else{
                                datesaveFrom = "" + year + "-" +(monthOfYear+1) + "-" + dayOfMonth ;
                            }

                        }
                        fromDate.setText(""+datesaveFrom);
                    }
                });

            }
        });

        // ------------------------- Set to date --------------------------------------------------------------
        btnToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialogTo = new DatePickerDialog();
                datePickerDialogTo.setThemeDark(false);
                datePickerDialogTo.showYearPickerFirst(false);
                datePickerDialogTo.setAccentColor(Color.parseColor("#0072BA"));
                datePickerDialogTo.show(getActivity().getFragmentManager(),"DatePickerDialog");
                datePickerDialogTo.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        //String date =  "Select Date : " + day + " - " + month + " - " + year;
                        String datesaveTo = "";
                        if(String.valueOf(monthOfYear+1).length()<2 && String.valueOf(dayOfMonth).length()<2){
                            datesaveTo = "" + year + "-" + "0"+(monthOfYear+1) + "-" + "0"+dayOfMonth ;
                        }else{
                            if(String.valueOf(monthOfYear+1).length()<2){
                                datesaveTo = "" + year + "-" +"0"+(monthOfYear+1) +"-" + dayOfMonth ;
                            }else if(String.valueOf(dayOfMonth).length()<2){
                                datesaveTo = "" + year + "-" +(monthOfYear+1) + "-" + "0"+dayOfMonth ;
                            }else{
                                datesaveTo = "" + year + "-" +(monthOfYear+1) + "-" + dayOfMonth ;
                            }
                        }
                        toDate.setText(""+datesaveTo);
                    }
                });

            }
        });

        //----------------------------------Select Sales or Invoice-----------------------------------------------------------------
        if(rdSales.isChecked()){

        }else if(rdInvoice.isChecked()){

        }
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rdSales.isChecked()){

                }else if(rdInvoice.isChecked()){

                }
            }
        });

        //------------------------------Category selection--------------------------------------------------------
        if(rdCase.isChecked()){

        }else if(rdPiece.isChecked()){

        }else if(rdTonnage.isChecked()){

        }else if(rdValue.isChecked()){

        }

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rdCase.isChecked()){

                }else if(rdPiece.isChecked()){

                }else if(rdTonnage.isChecked()){

                }else if(rdValue.isChecked()){

                }
            }
        });


        // set target item data into the spinner
//            targetItemList = new CurrencyController(context).searchCurrency();
//            targetItemList.add(0, "-SELECT-");
//
//            ArrayAdapter<String> adaptercur = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, targetItemList);
//            adaptercur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spTargetItem.setAdapter(adaptercur);





        targetDetDialog.findViewById(R.id.btnDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                targetDetDialog.dismiss();
            }
        });


        targetDetDialog.show();
    }





}