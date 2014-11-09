package qureshi.asim.lab.concurrency.bufferqueue;

/**
 * Created with IntelliJ IDEA.
 * User: asim.qureshi
 * Date: 11/8/14
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BufferQueue<T> {

    T get();

    boolean put(T item);

    boolean remainingCapacityThresholdReached();

    boolean prioritize(T item);

}
