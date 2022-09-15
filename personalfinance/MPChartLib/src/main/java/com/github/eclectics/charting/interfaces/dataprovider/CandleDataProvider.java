package com.github.eclectics.charting.interfaces.dataprovider;

import com.github.eclectics.charting.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
