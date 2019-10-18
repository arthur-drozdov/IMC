package com.imc.workshop.core.limits;

import com.imc.workshop.datamodel.Covariance;
import com.imc.workshop.datamodel.PnL;
import com.imc.workshop.datamodel.PositionLimits;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class LimitsCalculatorTest {
    private LimitsCalculator theLimitsCalculator = new LimitsCalculator(1000.);
    private static final List<Covariance> COVARIANCES
        = List.of(new Covariance("A", "A", 0.5),
                  new Covariance("A", "B", 0.1),
                  new Covariance("B", "A", 0.3),
                  new Covariance("B", "B", 0.7));

    @Test
    public void testScenario() {
        COVARIANCES.forEach(theLimitsCalculator::handleCovariance);

        theLimitsCalculator.handlePnl(new PnL("A", 1, 10, 0, 30));

        assertThat(theLimitsCalculator.getLimits()).containsExactlyInAnyOrder(
            new PositionLimits("A", 1, 1384.213562373095, 1444.213562373095),
            new PositionLimits("B", 0, 1190.6816236677173, 1199.2530522391457));

        theLimitsCalculator.handlePnl(new PnL("B", 2, -1000, 0, -2000));

        assertThat(theLimitsCalculator.getLimits()).containsExactlyInAnyOrder(
            new PositionLimits("A", 1, 1170., 0),
            new PositionLimits("B", 2, 3197.833163329294, 0.));
    }
}
