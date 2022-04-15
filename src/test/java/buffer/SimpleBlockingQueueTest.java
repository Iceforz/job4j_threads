package buffer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;


public class SimpleBlockingQueueTest {

    @Test
    public void testing() throws InterruptedException {
                SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(4);
                Thread producer = new Thread(() -> {
                    try {
                       sbq.offer(1);
                        sbq.offer(2);
                         sbq.offer(3);
                          sbq.offer(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                });
                Thread consumer = new Thread(() -> {
                    try {
                        Assert.assertEquals(sbq.poll(), Integer.valueOf(1));
                         Assert.assertEquals(sbq.poll(), Integer.valueOf(2));
                          Assert.assertEquals(sbq.poll(), Integer.valueOf(3));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                producer.start();
                producer.join();
                consumer.start();
                consumer.join();
                assertThat(sbq.poll(), is(4));
            }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}