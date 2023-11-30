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
}
