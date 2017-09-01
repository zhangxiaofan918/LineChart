# LineChart
MPAndroidChart之折线图

MPAndroidChart是一个开源的第三方图表库，支持折线图、饼状图、柱状图等等，
例程只实现了折线图的功能。

<img width="350" height="622" src="https://github.com/zhangxiaofan918/LineChart/blob/master/LineChart/images/device-2017-09-01-162634.png"/>


使用也比较简单，添加依赖：
```
compile files('libs/mpandroidchartlibrary-2-2-4.jar')
```
设置坐标轴的信息
```
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
```
生成折线的数据
```
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
```
设置要显示的样式
```
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
```

