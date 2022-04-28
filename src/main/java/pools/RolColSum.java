package pools;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {

        Sums[] value = new Sums[matrix.length];
        for (int i = 0; i < value.length; i++) {
            value[i] = new Sums(0, 0);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                value[i].setRowSum(value[i].getRowSum() + matrix[i][j]);
                value[i].setColSum(value[i].getColSum() + matrix[j][i]);
            }
        }
        return value;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {

        Sums[] value = new Sums[matrix.length];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            futures.put(i, getTask(matrix, i));
        }
        for (Integer key : futures.keySet()) {
            value[key] = futures.get(key).get();
        }
        return value;
    }

    public static CompletableFuture<Sums> getTask(int[][] matrix, int index) {

        return CompletableFuture.supplyAsync(
                () -> {
                    int rowSum = 0;
                    int colSum = 0;
                    for (int i = 0; i < matrix.length; i++) {
                        rowSum = rowSum + matrix[index][i];
                        colSum = colSum + matrix[i][index];
                    }
                    return new Sums(rowSum, colSum);
                }
        );
    }
}