package com.imc.workshop.core.limits;

import com.imc.workshop.core.limits.Polynom.*;
import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;

public class MathUtilUnitTest {

    @Test
    public void matrixTranspose() {
        Matrix myMatrix = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix myExpectedTransposed = new Matrix(new double[][]{{1, 3}, {2, 4}});

        Matrix myActualResult = MathUtil.transpose(myMatrix);

        assertThat(myActualResult).isEqualTo(myExpectedTransposed);
    }

    @Test
    public void matrixMultiplyWithSameDimensions() {
        Matrix myFirstMatrix = new Matrix(new double[][]{{1, 2}, {3, 4}});
        Matrix mySecondMatrix = new Matrix(new double[][]{{5, 6}, {7, 8}});
        Matrix myExpectedResult = new Matrix(new double[][]{{19, 22}, {43, 50}});

        Matrix myActualResult = MathUtil.multiply(myFirstMatrix, mySecondMatrix);

        assertThat(myActualResult).isEqualTo(myExpectedResult);
    }

    @Test
    public void matrixMultiplyWithDifferentDimensions() {
        Matrix myFirstMatrix = new Matrix(new double[][]{{1, 2, 3, 4}, {5, 6, 7, 8}});
        Matrix mySecondMatrix = new Matrix(new double[][]{{9, 10, 11}, {12, 13, 14}, {15, 16, 17}, {18, 19, 20}});
        Matrix myExpectedResult = new Matrix(new double[][]{{150, 160, 170}, {366, 392, 418}});

        Matrix myActualResult = MathUtil.multiply(myFirstMatrix, mySecondMatrix);

        assertThat(myActualResult).isEqualTo(myExpectedResult);
    }

    @Test
    public void positiveDiscriminantShouldYieldTwoPolynomialRoots() {
        PolynomialRoots myRoots = Polynom.computePolynomialRoots(-2, 3, -1);

        assertThat(myRoots.theFirstRoot).isEqualTo(1);
        assertThat(myRoots.theSecondRoot).isEqualTo(0.5);
    }

    @Test
    public void negativeDiscriminantShouldYieldMinimum() {
        PolynomialRoots myRoots = Polynom.computePolynomialRoots(-4, 16, -19);

        assertThat(myRoots.theFirstRoot).isEqualTo(2);
        assertThat(myRoots.theSecondRoot).isNaN();
    }
}
