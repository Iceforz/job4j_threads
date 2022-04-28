package pools;
import static org.hamcrest.CoreMatchers.is;
import junit.framework.TestCase;
import org.junit.Test;

import java.lang.module.FindException;

import static org.hamcrest.MatcherAssert.assertThat;

public class ParallelSortIndexTest {

    @Test
    public void whenFinderIsMoreObjectsAndInt() {
       Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(ParallelSortIndex.sort(array, 9), is(9));
    }
}