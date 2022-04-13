package buffer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;



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
        }