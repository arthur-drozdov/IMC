package com.imc.workshop.core.limits;

import org.junit.*;

import static org.junit.Assert.*;

public class MatrixTest {
    private static final double[][] RAW_MATRIX = {{0, 1, 2}, {3, 4, 5}};
    private static final Matrix TEST_MATRIX = new Matrix(RAW_MATRIX);
    private static double EPSILON = 0.0000001;

    @Test
    public void buildMatrixFromNestedArray() {
        for (int i = 0; i < RAW_MATRIX.length; ++i) {
            for (int j = 0; j < RAW_MATRIX[0].length; ++j) {
                assertEquals(RAW_MATRIX[i][j], TEST_MATRIX.get(i, j), EPSILON);
            }
        }

        assertEquals(RAW_MATRIX.length, TEST_MATRIX.getNumberOfRows());
        assertEquals(RAW_MATRIX[0].length, TEST_MATRIX.getNumberOfColumns());
    }

    @Test
    public void settingValueShouldUpdateMatrix() {
        Matrix myMatrix = new Matrix(RAW_MATRIX);

        double myNewValue = 42.;
        myMatrix.set(1, 1, myNewValue);
        assertEquals(myNewValue, myMatrix.get(1, 1), EPSILON);
    }
}
