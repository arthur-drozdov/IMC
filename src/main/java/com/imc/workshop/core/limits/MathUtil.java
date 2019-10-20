package com.imc.workshop.core.limits;

public class MathUtil {
    public static Matrix transpose(Matrix matrix) {
        int N = matrix.getNumberOfRows();
        int M = matrix.getNumberOfColumns();
        double[][] transposed = new double[M][N];
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                transposed[i][j] = matrix.get(j, i);
            }
        }
        return new Matrix(transposed);
    }

    public static Matrix multiply(Matrix leftMatrix, Matrix rightMatrix) {
        int N = leftMatrix.getNumberOfRows();
        int K = leftMatrix.getNumberOfColumns(); // or rightMatrix.getNumberOfRows()
        int M = rightMatrix.getNumberOfColumns();

        double[][] res = new double[N][M];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                res[i][j] = 0;
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                for(int k = 0; k < K; k++){
                    res[i][j]= res[i][j] + (leftMatrix.get(i, k) * rightMatrix.get(k, j));
                }
            }
        }

        return new Matrix(res);
    }
}
