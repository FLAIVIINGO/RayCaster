package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    @Test
    void testMatrixInit() {
        double[][] matrix = new double[4][4];
        double count = 1;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(i % 2 == 0) {
                    matrix[i][j] = count++;
                }
                else {
                    matrix[i][j] = .5 + count++;
                }
            }
        }

        assertEquals(matrix[0][0], 1);
        assertEquals(matrix[0][3], 4);
        assertEquals(matrix[1][0], 5.5);
        assertEquals(matrix[1][2], 7.5);
        assertEquals(matrix[2][2], 11);
        assertEquals(matrix[3][0], 13.5);
        assertEquals(matrix[3][2], 15.5);
    }

    @Test
    void compareMatrixPassTest() {
        double[][] m1 = {{1, 2, 3, 4},
                         {5,6,7,8},
                         {9,8,7,6},
                         {5,4,3,2}};
        double[][] m2 = {{1, 2, 3, 4},
                         {5,6,7,8},
                         {9,8,7,6},
                         {5,4,3,2}};
        assertTrue(Arrays.deepEquals(m1, m2));
    }

    @Test
    void compareMatrixFailTest() {
        double[][] m1 = {{1, 2, 3, 4},
                {5,6,7,8},
                {9,8,7,6},
                {5,4,3,2}};
        double[][] m2 = {{2,3,4,5},
                {6,7,8,9},
                {8,7,6,5},
                {4,3,2,1}};
        assertFalse(Arrays.deepEquals(m1, m2));
    }

    @Test
    void matrixMultiplyTest() {
        MatrixOperations matrixOperations = new MatrixOperations();
        double[][] m1 = {{1, 2, 3, 4},
                {5,6,7,8},
                {9,8,7,6},
                {5,4,3,2}};
        double[][] m2 = {{-2,1,2,3},
                {3,2,1,-1},
                {4,3,6,5},
                {1,2,7,8}};
        double[][] result = matrixOperations.multiplyMatrices(m1, m2);
        double[][] compareMatrix = {{20,22,50,48},
                {44,54,114,108},
                {40,58,110,102},
                {16,26,46,42}};
        assertTrue(Arrays.deepEquals(result, compareMatrix));
    }

    @Test
    void matrixTupleMultiplyTest() {
        MatrixOperations matrixOperations = new MatrixOperations();
        double[][] matrix = {{1, 2, 3, 4,},
                {2,4,4,2},
                {8,6,4,1},
                {0,0,0,1}};
        Tuple tuple = new Tuple(1, 2, 3, 1);
        Tuple compare = new Tuple(18, 24, 33, 1);
        Tuple result = matrixOperations.multiplyMatrixByTuple(matrix, tuple);
        assertTrue(compare.equalTuples(result));
    }

    @Test
    void multiplyByIdentityMatrixTest() {
        MatrixOperations matrixOperations = new MatrixOperations();
        double[][] matrix = {{0,1,2,4},
                {1,2,4,8},
                {2,4,8,16},
                {4,8,16,32}};
        double[][] result = matrixOperations.multiplyByIdentityMatrix(matrix);
        assertTrue(Arrays.deepEquals(result, matrix));
    }

    @Test
    void transposeMatrixTest() {
        MatrixOperations matrixOperations = new MatrixOperations();
        double[][] matrix =
                {{0,9,3,0},
                {9,8,0,8},
                {1,8,5,3},
                {0,0,5,8}};
        double[][] compare =
                {{0,9,1,0},
                {9,8,8,0},
                {3,0,5,5},
                {0,8,3,8}};
        double[][] result = matrixOperations.transposeMatrix(matrix);
        assertTrue(Arrays.deepEquals(result, compare));
    }

    @Test
    void transposeIdentityMatrix() {
        MatrixOperations matrixOperations = new MatrixOperations();
        double[][] identityMatrix =
                {{1,0,0,0},
                {0,1,0,0},
                {0,0,1,0},
                {0,0,0,1}};
        assertTrue(Arrays.deepEquals(identityMatrix, matrixOperations.transposeMatrix(identityMatrix)));
    }

    @Test
    void determinantOf2By2MatrixTest() {
        MatrixOperations matrixOperations = new MatrixOperations();
        double[][] matrix =
                {{1,5},
                {-3,2}};
        assertEquals(matrixOperations.determinant(matrix), 17);
    }

    @Test
    void submatrixTest() {
        MatrixOperations matrixOperations = new MatrixOperations();
        double[][] matrix =
                {{-6,1,1,6},
                {-8,5,8,6},
                {-1,0,8,2},
                {-7,1,-1,1}};
        double[][] result = matrixOperations.submatrix(matrix, 2, 1);
        double[][] compare =
                {{-6,1,6},
                {-8,8,6},
                {-7,-1,1}};
        assertTrue(Arrays.deepEquals(result, compare));
    }

    @Test
    void minorTest() {
        MatrixOperations matrixOperations = new MatrixOperations();
        double[][] matrix =
                {{3,5,0},
                {2,-1,-7},
                {6,-1,5}};
        assertEquals(matrixOperations.minor(matrix, 1, 0), 25);
    }

    @Test
    void cofactorTest() {
        MatrixOperations matrixOperations = new MatrixOperations();
        double[][] matrix =
                {{3,5,0},
                {2,-1,-7},
                {6,-1,5}};
        assertEquals(matrixOperations.cofactor(matrix, 0, 0), -12);
        assertEquals(matrixOperations.cofactor(matrix, 1, 0), -25);
    }

    @Test
    void determinantTest1() {
        MatrixOperations matrixOperations = new MatrixOperations();
        double[][] matrix =
                {{1,2,6},
                {-5,8,-4},
                {2,6,4}};
        assertEquals(matrixOperations.cofactor(matrix, 0, 0), 56);
        assertEquals(matrixOperations.cofactor(matrix, 0, 1), 12);
        assertEquals(matrixOperations.cofactor(matrix, 0, 2), -46);
        assertEquals(matrixOperations.determinant(matrix), -196);
    }

    @Test
    void determinantTest2() {
        MatrixOperations matrixOperations = new MatrixOperations();
        double[][] matrix =
                {{-2,-8,3,5},
                {-3,1,7,3},
                {1,2,-9,6},
                {-6,7,7,-9}};
        assertEquals(matrixOperations.cofactor(matrix, 0, 0), 690);
        assertEquals(matrixOperations.cofactor(matrix, 0, 1), 447);
        assertEquals(matrixOperations.cofactor(matrix, 0, 2), 210);
        assertEquals(matrixOperations.cofactor(matrix, 0, 3), 51);
        assertEquals(matrixOperations.determinant(matrix), -4071);
    }

    @Test
    void invertibleTest() {
        MatrixOperations matrixOperations = new MatrixOperations();
        double[][] matrix1 =
                {{6,4,4,4},
                {5,5,7,6},
                {4,-9,3,-7},
                {9,1,7,-6}};
        double[][] matrix2 =
                {{-4,2,-2,-3},
                {9,6,2,6},
                {0,-5,1,-5},
                {0,0,0,0}};
        assertTrue(matrixOperations.isInvertible(matrix1));
        assertFalse(matrixOperations.isInvertible(matrix2));
    }

    @Test
    void inverseMatrixTest1() {
        MatrixOperations matrixOperations = new MatrixOperations();
        double[][] matrix =
                {{-5,2,6,-8},
                {1,-5,1,8},
                {7,7,-6,-7},
                {1,-3,7,4}};
        double[][] compare = {{0.21805,0.45113,0.24060,-0.04511},
                {-0.80827,-1.45677,-0.44361,0.52068},
                {-0.07895, -0.22368,-0.05263,0.19737},
                {-0.52256,-0.81391,-0.30075,0.30639}};
        double deter = matrixOperations.determinant(matrix);
        double[][] result = matrixOperations.inverse(matrix, deter);
        assertTrue(Arrays.deepEquals(result, compare));
    }

    @Test
    void inverseMatrixTest2() {
        MatrixOperations matrixOperations = new MatrixOperations();
        double[][] matrix =
                {{8,-5,9,2},
                        {7,5,6,1},
                        {-6,0,9,6},
                        {-3,0,-9,-4}};
        double[][] compare = {{-0.15385,-0.15385,-0.28205,-0.53846},
                {-0.07692,0.12308,0.02564,0.03077},
                {0.35897,0.35897,0.43590,0.92308},
                {-0.69231,-0.69231,-0.76923,-1.92308}};
        double deter = matrixOperations.determinant(matrix);
        double[][] result = matrixOperations.inverse(matrix, deter);
        assertTrue(Arrays.deepEquals(result, compare));
    }

    @Test
    void inverseMatrixTest3() {
        MatrixOperations matrixOperations = new MatrixOperations();
        double[][] matrix =
                {{9,3,0,9},
                        {-5,-2,-6,-3},
                        {-4,9,6,4},
                        {-7,6,6,2}};
        double[][] compare = {{-0.04074,-0.07778,0.14444,-0.22222},
                {-0.07778,0.03333,0.36667,-0.33333},
                {-0.02901,-0.14630,-0.10926,0.12963},
                {0.17778,0.06667,-0.26667,0.33333}};
        double deter = matrixOperations.determinant(matrix);
        double[][] result = matrixOperations.inverse(matrix, deter);
        assertTrue(Arrays.deepEquals(result, compare));
    }

    @Test
    void inverseMatrixTest4() {
        MatrixOperations matrixOperations = new MatrixOperations();
        double[][] a = {{3, -9, 7, 3},
                {3,-8,2,-9},
                {-4,4,4,1},
                {-6,5,-1,1}};
        double[][] b = {{8,2,2,2},
                {3,-1,7,0},
                {7,0,5,4},
                {6,-2,0,5}};
        double[][] c = matrixOperations.multiplyMatrices(a, b);
        System.out.println(Arrays.deepToString(c));
        double deter = matrixOperations.determinant(b);
        double[][] result = matrixOperations.multiplyMatrices(c, matrixOperations.inverse(b, deter));
        System.out.println(Arrays.deepToString(matrixOperations.inverse(b, deter)));
        System.out.println(Arrays.deepToString(result));
        assertTrue(Arrays.deepEquals(a, result));
    }
}
