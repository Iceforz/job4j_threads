package pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSortIndex<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int from;
    private final int to;
    private final Integer obj;

    public ParallelSortIndex(T[] array, int from, int to, Integer obj) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.obj = obj;
    }

    public int findIndex() {
        int rsl = -1;
        if (array != null) {
            for (int i = from; i < to; i++) {
                if (obj.equals(array[i])) {
                    return i;
                }
            }
        }
        return rsl;
    }

    @Override
    protected Integer compute() {
    if ((to - from) <= 10) {
        return findIndex();
    }
    int mid = (from + to) / 2;
        ParallelSortIndex<T> left = new ParallelSortIndex<>(array, from, mid, obj);
        ParallelSortIndex<T> right = new ParallelSortIndex<>(array, mid + 1, to, obj);
        left.fork();
        right.fork();
        return Math.max(left.join(), right.join());
    }

    public static <T> int sort(T[] array, Integer obj) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(
                new ParallelSortIndex<>(array, 0, array.length, obj));
    }
}







