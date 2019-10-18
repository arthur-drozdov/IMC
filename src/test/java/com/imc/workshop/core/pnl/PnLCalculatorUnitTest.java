package com.imc.workshop.core.pnl;


import com.imc.workshop.datamodel.PnL;
import org.junit.Before;
import org.junit.Test;

import static com.imc.workshop.core.pnl.TestData.INSTRUMENT;
import static com.imc.workshop.core.pnl.TestData.marketUpdate;
import static com.imc.workshop.core.pnl.TestData.trade;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PnLCalculatorUnitTest {

    private PnLCalculator theVictim;

    @Before
    public void setUp() {
        theVictim = new PnLCalculator();
    }

    @Test(expected = IllegalStateException.class)
    public void calculatingPnLBeforeMarketUpdateShouldFail() {
        theVictim.onTrade(trade(10, 10, 0));
        double myPnL = theVictim.getPnl(INSTRUMENT).getPnL();
    }

    @Test
    public void longPositionShouldBeAccuratelyMarked() {
        theVictim.onTrade(trade(10, 10, 0));
        theVictim.onMarketUpdate(marketUpdate(12, 13, 1));
        PnL myPnL = theVictim.getPnl(INSTRUMENT);
        assertThat(myPnL.getPnL()).isEqualTo(2 * 12.5);
        assertThat(myPnL.getInstrument()).isEqualTo(INSTRUMENT);
        assertThat(myPnL.getLastSequenceNumber()).isEqualTo(1);
        assertThat(myPnL.getPosition()).isEqualTo(10);
        assertThat(myPnL.getPositionCashValue()).isEqualTo(125);
    }

    @Test
    public void shortPositionShouldBeAccuratelyMarked() {
        theVictim.onTrade(trade(-10, 10, 0));
        theVictim.onMarketUpdate(marketUpdate(12, 13, 1));
        PnL myPnL = theVictim.getPnl(INSTRUMENT);
        assertThat(myPnL.getPnL()).isEqualTo(-2 * 12.5);
        assertThat(myPnL.getInstrument()).isEqualTo(INSTRUMENT);
        assertThat(myPnL.getLastSequenceNumber()).isEqualTo(1);
        assertThat(myPnL.getPosition()).isEqualTo(-10);
        assertThat(myPnL.getPositionCashValue()).isEqualTo(-125);
    }

    @Test
    public void mixedPositionShouldBeAccuratelyMarket() {
        theVictim.onTrade(trade(-10, 10, 0));
        theVictim.onTrade(trade(15, 9, 1));
        theVictim.onMarketUpdate(marketUpdate(11, 13, 2));
        PnL myPnL = theVictim.getPnl(INSTRUMENT);
        assertThat(myPnL.getPnL()).isEqualTo(10 + 15);
        assertThat(myPnL.getInstrument()).isEqualTo(INSTRUMENT);
        assertThat(myPnL.getLastSequenceNumber()).isEqualTo(2);
        assertThat(myPnL.getPosition()).isEqualTo(5);
        assertThat(myPnL.getPositionCashValue()).isEqualTo(60);
    }
}
