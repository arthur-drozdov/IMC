package com.imc.workshop.datamodel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OutputSerializationTest {

    private ObjectMapper theObjectMapper;

    @Before
    public void setUp() {
        theObjectMapper = new ObjectMapper();
    }

    @Test
    public void shouldBeAbleToSerializeAndDeserializePnl() throws Exception {
        var myPnl = new PnL("AAPL",
                1,
                1,
                1,
            1);
        var mySerialized = theObjectMapper.writeValueAsString(myPnl);
        var myDeserialized = theObjectMapper.readValue(mySerialized, PnL.class);
        assertThat(myDeserialized).isEqualTo(myPnl);
    }

    @Test
    public void shouldBeAbleToSerializeAndDeserializePositionLimits() throws Exception {
        var myLimits = new PositionLimits("AAPL", 10, 1, 1);
        var mySerialized = theObjectMapper.writeValueAsString(myLimits);
        var myDeserialized = theObjectMapper.readValue(mySerialized, PositionLimits.class);
        assertThat(myDeserialized).isEqualTo(myLimits);
    }

    @Test
    public void shouldDeserializeGenericType() throws Exception {
        var myLimits = new PositionLimits("AAPL", 10, 1, 1);
        var mySerialized = theObjectMapper.writeValueAsString(myLimits);
        var myDeserialized = theObjectMapper.readValue(mySerialized, Result.class);
        assertThat(myDeserialized).isEqualTo(myLimits);
    }
}
