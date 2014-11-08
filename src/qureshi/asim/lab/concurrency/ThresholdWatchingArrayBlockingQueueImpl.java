package qureshi.asim.lab.concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: asim.qureshi
 * Date: 11/9/14
 * Time: 12:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class ThresholdWatchingArrayBlockingQueueImpl<T> extends ArrayBlockingQueueImpl<T> implements ThresholdAwareBufferQueue<T> {

    private int remainingCapacityThreshold;

    public ThresholdWatchingArrayBlockingQueueImpl(int size) {
        super(size);
    }

    public ThresholdWatchingArrayBlockingQueueImpl(int size, int remainingCapacityThreshold) {
        super(size);
        this.remainingCapacityThreshold = remainingCapacityThreshold;
    }

    @Override
    public boolean thresholdReached() {

        //print(":RC:"+queue.remainingCapacity());

        return queue.remainingCapacity() < remainingCapacityThreshold;
    }
}
