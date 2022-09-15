package com.github.eclectics.charting.interfaces.dataprovider;

import com.github.eclectics.charting.data.ScatterData;

public interface ScatterDataProvider extends BarLineScatterCandleBubbleDataProvider {

    ScatterData getScatterData();
}
