package com.github.eclectics.charting.interfaces.dataprovider;

import com.github.eclectics.charting.components.YAxis.AxisDependency;
import com.github.eclectics.charting.data.BarLineScatterCandleBubbleData;
import com.github.eclectics.charting.utils.Transformer;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(AxisDependency axis);
    boolean isInverted(AxisDependency axis);
    
    float getLowestVisibleX();
    float getHighestVisibleX();

    BarLineScatterCandleBubbleData getData();
}
