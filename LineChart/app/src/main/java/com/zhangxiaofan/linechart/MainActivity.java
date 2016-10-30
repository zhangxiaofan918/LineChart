package com.zhangxiaofan.linechart;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();

    }

    private void initLayout() {
        mLineChart = (LineChart) findViewById(R.id.lineChart);
        LineData lineData = getLineData(7, 5000);
        showChart(mLineChart, lineData, Color.WHITE);
    }

    /**
     * 设置要显示的样式
     *
     * @param lineChart
     * @param lineData
     * @param color
     */
    private void showChart(LineChart lineChart, LineData lineData, int color) {
        lineChart.setDrawBorders(false);  //是否在折线图上添加边框
        // no description text
        lineChart.setDescription("");// 数据描述
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
        lineChart.setNoDataTextDescription("You need to provide data for the chart.");
        // enable / disable grid background
        lineChart.setDrawGridBackground(false); // 是否显示表格颜色
        // 表格的的颜色，在这里是是给颜色设置一个透明度
        lineChart.setGridBackgroundColor(Color.rgb(77, 192, 148) & 0x70FFFFFF);
        // enable touch gestures
        lineChart.setTouchEnabled(true); // 设置是否可以触摸
        // enable scaling and dragging
        lineChart.setDragEnabled(false);// 是否可以拖拽
        lineChart.setScaleEnabled(false);// 是否可以缩放
        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);//
        // add data
        lineChart.setData(lineData); // 设置数据
        //要设置高亮显示有效才能使用该事件
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {

            }

            @Override
            public void onNothingSelected() {

            }
        });
        // get the legend (only possible after setting data)
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的

        // modify the legend ...
        // mLegend.setPosition(LegendPosition.LEFT_OF_CHART);
        mLegend.setEnabled(false);
        mLegend.setForm(Legend.LegendForm.SQUARE);// 样式
        mLegend.setFormSize(7f);// 字体
        mLegend.setTextColor(Color.WHITE);// 颜色
//      mLegend.setTypeface(mTf);// 字体

        setXYAxisValues(lineChart);
    }


    /**
     * 设置x轴、y轴的标签信息
     *
     * @param lineChart
     */
    private void setXYAxisValues(LineChart lineChart) {
        // 设置X轴
        XAxis xAxis = lineChart.getXAxis();
        // 设置X轴的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 设置X轴启用或者禁用
        xAxis.setEnabled(true);
        // 上面第一行代码设置了false,所以下面第一行即使设置为true也不会绘制AxisLine
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setAxisLineColor(Color.rgb(77, 192, 148));
        xAxis.setSpaceBetweenLabels(0);
        xAxis.setLabelsToSkip(0);
        xAxis.setAvoidFirstLastClipping(true);

        YAxis yAxis = lineChart.getAxisLeft();
        // 设置左边的label可用
        yAxis.setDrawLabels(true);
        // 设置左边的线不可用
        yAxis.setDrawGridLines(false);
        // 设置左边的线不可用
        yAxis.setDrawAxisLine(false);

        YAxis yAxisRight = lineChart.getAxisRight();
        // 设置左边的label不可用
        yAxisRight.setDrawLabels(false);
        // 设置左边的线不可用
        yAxisRight.setDrawGridLines(false);
        // 设置左边的线不可用
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setTextColor(Color.WHITE);
        yAxisRight.setSpaceTop(15);  //设置最大值距离顶部的距离,即留白区域
        yAxisRight.setSpaceBottom(15);

        //添加限制线
        LimitLine limitLineY = new LimitLine(3000f, "");
        limitLineY.setLabel("3000");
        limitLineY.enableDashedLine(10f, 10f, 0f); //是否启用虚线模式
        limitLineY.setTextSize(3f);
        limitLineY.setTextColor(Color.rgb(77, 192, 148));
        limitLineY.setLineColor(Color.rgb(77, 192, 148));
        limitLineY.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        yAxisRight.addLimitLine(limitLineY);
        yAxisRight.setDrawLimitLinesBehindData(true);
//        lineChart.animateX(2500); // 立即执行的动画,x轴
    }

    /**
     * 生成折线的数据
     *
     * @param count 表示图表中有多少个坐标点
     * @param range 用来生成range以内的随机数
     * @return
     */
    private LineData getLineData(int count, int range) {
        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xValues.add(i + "");
        }

        // y轴的数据
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            int value = (int) (Math.random() * range);
            yValues.add(new Entry(value, i));
        }

        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(yValues, null);

        //用y轴的集合来设置参数
        lineDataSet.setLineWidth(1.75f); // 线宽
        lineDataSet.setCircleSize(2f);// 显示的圆形大小
        lineDataSet.setColor(Color.parseColor("#45a6d9"));// 显示颜色
        lineDataSet.setCircleColor(Color.WHITE);// 圆形的颜色
        lineDataSet.setValueTextColor(Color.WHITE);

        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setHighLightColor(Color.TRANSPARENT); // 高亮的线的颜色
        lineDataSet.setDrawFilled(true);  //填充折现下面的区域
        lineDataSet.setFillColor(Color.rgb(245, 245, 245)); //设置填充区域的颜色
        ArrayList<ILineDataSet> lineDataSets = new ArrayList<ILineDataSet>();
        lineDataSets.add(lineDataSet); // add the datasets

        // create a data object with the datasets
        LineData lineData = new LineData(xValues, lineDataSets);
        lineData.setDrawValues(false);

        return lineData;
    }
}
