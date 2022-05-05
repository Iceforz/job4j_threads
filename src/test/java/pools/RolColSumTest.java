package pools;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RolColSumTest {

    @Test
    public void whenSum() {
        String excepted = "Ряд {10}, столбец {28} Ряд {26}, столбец {32} Ряд {42}, столбец {36} Ряд {58}, столбец {40} ";
        StringBuilder sb = new StringBuilder();
        int[][] array = new int[][] {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        RolColSum.Sums[] rolColSums = RolColSum.sum(array);
        for (RolColSum.Sums s : rolColSums) {
            sb.append(
                            String.format("Ряд {%d}, столбец {%d}", s.getRowSum(), s.getColSum()))
                    .append(" ");
        }
        assertThat(excepted, is(sb.toString()));
    }

    @Test
    public void whenAsyncSum() throws ExecutionException, InterruptedException {
        String excepted = "Ряд {10}, столбец {28} Ряд {26}, столбец {32} Ряд {42}, столбец {36} Ряд {58}, столбец {40} ";
        StringBuilder sb = new StringBuilder();
        int[][] array = new int[][] {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        RolColSum.Sums[] rolColSums = RolColSum.asyncSum(array);
        for (RolColSum.Sums s : rolColSums) {
            sb.append(
                            String.format("Ряд {%d}, столбец {%d}", s.getRowSum(), s.getColSum()))
                    .append(" ");
        }
        assertThat(excepted, is(sb.toString()));
    }
}