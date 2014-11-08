package qureshi.asim.lab.concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: asim.qureshi
 * Date: 11/9/14
 * Time: 12:08 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ThresholdAwareBufferQueue<T> extends BufferQueue<T> {

    boolean thresholdReached();
}
