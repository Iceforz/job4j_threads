package pool;

import buffer.SimpleBlockingQueue;
import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(4);
    private final int size = Runtime.getRuntime().availableProcessors();

    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(
                    () -> {
                        while   (!Thread.currentThread().isInterrupted()) {
                            try {
                                tasks.poll().run();

                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                    )
            );
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void work(Runnable job) throws  InterruptedException {
         tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

        public static void main(String[] args) throws InterruptedException {
            ThreadPool thread = new ThreadPool();
            Runnable job1 = () -> System.out.println("First thread");
            Runnable job2 = () -> System.out.println("Second thread");
            Runnable job3 = () -> System.out.println("Third thread");
            Runnable job4 = () -> System.out.println("Fourth thread");
            thread.work(job1);
            thread.work(job2);
            thread.work(job3);
            thread.work(job4);
            thread.shutdown();
        }
}