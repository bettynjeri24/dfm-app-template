package com.github.eclectics.charting.interfaces.dataprovider;

import com.github.eclectics.charting.data.BubbleData;

public interface BubbleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BubbleData getBubbleData();
}
