package pools;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParallelSortIndexTest {

    @Test
    public void whenFinderIsMoreObjectsAndInt() {
       Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(ParallelSortIndex.sort(array, 9), is(9));
    }

    @Test
    public void whenNothingFindAndReturnNull() {
        Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        assertThat(ParallelSortIndex.sort(array, 12), is(-1));
    }

    @Test
    public void whenReturnLastObjInArray() {
        Integer[] array = new Integer[] {0, 1, 1, 1, 1, 0, 2, 3};
        assertThat(ParallelSortIndex.sort(array, 3), is(7));

    }
}