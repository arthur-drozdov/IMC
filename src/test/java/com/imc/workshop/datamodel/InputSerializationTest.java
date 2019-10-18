package com.imc.workshop.datamodel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imc.workshop.datamodel.MarketUpdate.MarketSide;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InputSerializationTest {

    private ObjectMapper theObjectMapper;

    @Before
    public void setUp() {
        theObjectMapper = new ObjectMapper();
    }

    @Test
    public void shouldBeAbleToSerializeAndDeserializeMarketUpdate() throws Exception {
        MarketUpdate myUpdate = new MarketUpdate("AAPL",
                1,
                1,
                new MarketSide(10, 10),
                new MarketSide(11, 10));
        String mySerialized = theObjectMapper.writeValueAsString(myUpdate);
        MarketUpdate myDeserialized = theObjectMapper.readValue(mySerialized, MarketUpdate.class);
        assertThat(myDeserialized).isEqualTo(myUpdate);
    }

    @Test
    public void shouldBeAbleToSerializeAndDeserializeTrade() throws Exception {
        Trade myTrade = new Trade("AAPL", 10, 1, 1, 1);
        String mySerialized = theObjectMapper.writeValueAsString(myTrade);
        Trade myDeserialized = theObjectMapper.readValue(mySerialized, Trade.class);
        assertThat(myDeserialized).isEqualTo(myTrade);
    }

    @Test
    public void shouldDeserializeGenericType() throws Exception {
        Trade myTrade = new Trade("AAPL", 10, 1, 1, 1);
        String mySerialized = theObjectMapper.writeValueAsString(myTrade);
        MarketEvent myDeserialized = theObjectMapper.readValue(mySerialized, MarketEvent.class);
        assertThat(myDeserialized).isEqualTo(myTrade);
    }
}
