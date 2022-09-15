package com.github.eclectics.charting.interfaces.dataprovider;

import com.github.eclectics.charting.components.YAxis;
import com.github.eclectics.charting.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
