package qureshi.asim.lab.concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: asim.qureshi
 * Date: 11/9/14
 * Time: 12:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class ThresholdedProducer extends Producer {


    private ThresholdAwareBufferQueue<QueueItem> queue;

    public ThresholdedProducer(ThresholdAwareBufferQueue<QueueItem> bufferQueue) {

        super(bufferQueue);
        this.queue = bufferQueue;
    }

    @Override
    public void run() {

//        print("In Producer");

        while (!queue.thresholdReached()){
            putItems(queue);
        }

//        print("Exit Producer");


    }

}
