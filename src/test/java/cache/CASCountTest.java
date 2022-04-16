package cache;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class CASCountTest {

    @Test
    public void whenCount() throws InterruptedException {
        CASCount casCount = new CASCount();
        Thread first = new Thread(
                casCount::increment
        );
        Thread second = new Thread(
                casCount::increment
        );
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(casCount.get(), is(2));
    }
}
