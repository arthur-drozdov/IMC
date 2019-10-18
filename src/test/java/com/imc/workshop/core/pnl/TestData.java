package com.imc.workshop.core.pnl;

import com.imc.workshop.datamodel.MarketUpdate;
import com.imc.workshop.datamodel.MarketUpdate.MarketSide;
import com.imc.workshop.datamodel.Trade;

public class TestData {
    static final String INSTRUMENT = "BP";

    static Trade trade(int aVolume, double aPrice, long aSequenceNumber) {
        return new Trade(INSTRUMENT, aVolume, aPrice, 0, aSequenceNumber);
    }

    static MarketUpdate marketUpdate(double aBestBid, double aBestAsk, long aSequenceNumber) {
        return new MarketUpdate(INSTRUMENT,
                aSequenceNumber,
                0,
                new MarketSide(aBestBid, 1),
                new MarketSide(aBestAsk, 1));
    }
}
