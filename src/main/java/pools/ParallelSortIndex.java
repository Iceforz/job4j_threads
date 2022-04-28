package pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSortIndex extends RecursiveTask<Integer> {

    private final Integer[] array;
    private final int from;
    private final int to;
    private final Integer obj;

    public ParallelSortIndex(Integer[] array, int from, int to, Integer obj) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.obj = obj;
    }

    public Integer findIndex() {
        for (int i = from; i <= to; i++) {
            if (obj.equals(array[i])) {
                return array[i];
            }
        }
        return -1;
    }

    @Override
    protected Integer compute() {
    if ((to - from) <= 10) {
        return findIndex();
    }
    int mid = (from + to) / 2;
        ParallelSortIndex left = new  ParallelSortIndex(array, from, mid, obj);
        ParallelSortIndex right = new  ParallelSortIndex(array, mid + 1, to, obj);
        left.fork();
        right.fork();
        return Math.max(left.join(), right.join());
    }

    public static Integer sort(Integer[] array, Integer obj) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(
                new ParallelSortIndex(array, 0, array.length, obj));
    }
}







