import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryA {


    //А1: обход треугольника по часовой стрелке. Если точка всегда лежит справа, то она внутри

    //положение точки относительно вектора
    private int pointPosition(int x1, int y1, int x2, int y2, int pointX, int pointY) {
        int sq;
        sq = (x2 - x1) * (pointY - y1) - (y2 - y1) * (pointX - x1);
        if (sq > 0)
            return 1;
        else if (sq < 0)
            return -1;
        else
            return 0;
    }

    //определяем положение точки
    private String isPointInside(int x1, int y1, int x2, int y2, int x3, int y3, int pointX, int pointY) {
        int sq1, sq2, sq3;
        boolean pointInside = false;
        sq1 = pointPosition(x1, y1, x2, y2, pointX, pointY);
        sq2 = pointPosition(x2, y2, x3, y3, pointX, pointY);
        if (sq1 * sq2 < 0)
            return "OUT";
        sq3 = pointPosition(x3, y3, x1, y1, pointX, pointY);
        if (sq3 * sq2 < 0)
            return "OUT";
        return "IN";
    }

    //для вызова в Main()
    public void taskA1(int x1, int y1, int x2, int y2, int x3, int y3, int pointX, int pointY) {
        String A1 = isPointInside(x1, y1, x2, y2, x3, y3, pointX, pointY);
        System.out.println(A1);

    }


    // A2
    private int sumOfElements() {

        int[][] matrixA = {
                {1, 3, 8},
                {9, 2, 1},
                {0, 3, 7}
        };
        int mainSum = 0, secondarySum = 0, rez = 0;
        for (int i = 0; i < 3; i++)
            mainSum += matrixA[i][i];
        for (int i = 0, j = 2; i < 3; i++, j--)
            secondarySum += matrixA[i][j];

        return (Math.abs(secondarySum - mainSum));
    }

    public void taskA2() {
        int rez = sumOfElements();
        System.out.println(rez);
    }

    //A3
    public void taskA3(int n) {

        for (int i = 0; i < n; i++) {
            int k = n - i;
            for (int j = 0; j < k; j++)
                System.out.print(" ");
            for (int m = 0; m < i; m++)
                System.out.print("#");
            System.out.println("#");

        }

    }

    //A4
    public void taskA4(int[] A, int k) {
        System.out.println(findNumbers(A, k));
    }

    private int findNumbers(int[] A, int k) {

        int count = 0;
        for (int i = 0; i < A.length / 2; ++i) {
            for (int j = 1; j < A.length; ++j) {
                if ((A[i] + A[j]) % k == 0) {
                    count++;
                }
            }

        }
        return count;
    }


    //A5
    public void taskA5(int[][] A, int[][] B) {

        findWindow(A, B);
    }

    private int findSequence(int[][] A, int[][] B, int k, int h) {


        List rowArrayA = new ArrayList();
        List rowsArrayB = new ArrayList();

        for (int j = 0; j < A[0].length; ++j) {
            rowArrayA.add(A[k][j]); // собираем k-ую строку
        }

        for (int i = 0; i < B[0].length; ++i) {
            rowsArrayB.add(B[h][i]); // собираем h-ую строку
        }

        int index = Collections.indexOfSubList(rowArrayA, rowsArrayB);

        return index;
    }

    private void findWindow(int[][] A, int[][] B) {
        int columnsA = A[0].length;
        int columnsB = B[0].length;
        int rowsA = A.length;
        int rowsB = B.length;
        int index = -1;
        int index2 = -1;
        List rowIndex = new ArrayList();
        List columnIndex = new ArrayList();
        int cur;
        int flag = 0;
        //разбивается двумерный на одномерные
        //в каждом ищется подстрока
        //если находится - сравниваются номера массивов

        for (int i = 0; i < rowsA; ++i) {
            for (int j = 0; j < rowsB; ++j) {

            }
        }

        // k - строка большей матрицы, h - окна

        for (int k = 0; k < rowsA; ++k) {
            for (int h = 0; h < rowsB; ++h) {
                index = findSequence(A, B, k, h);

                if (index != -1) {
                    rowIndex.add(index);
                    columnIndex.add(k);
                }
            }
        }


        if (columnIndex.size() > 2) {

            for (int i = 1; i < columnIndex.size() - 1; i++) {

                if ((int) columnIndex.get(i) - (int) columnIndex.get(i - 1) != 1) {
                    flag = 0;

                } else
                    flag = 1;

            }

            if (flag == 0)
                System.out.println("FAIL");
            if (flag == 1)
                System.out.println(columnIndex.get(0) + "  " + rowIndex.get(0)); // номер строки - номер столбца


        }

        if(columnIndex.size() == 1) {


            if ( rowsB == columnIndex.size())
                System.out.println(columnIndex.get(0) + "  " + rowIndex.get(0)); // номер строки - номер столбца
            else
                System.out.println("FAIL");
        }

        if (columnIndex.size() == 2){


            if (((int) columnIndex.get(1) - (int) columnIndex.get(0) != 1) ) {
                flag = 0;

            } else
                flag = 1;

            if (flag == 0)
                System.out.println("FAIL");
            if (flag == 1)
                System.out.println(columnIndex.get(0) + "  " + rowIndex.get(0)); // номер строки - номер столбца


        }


    }
}
