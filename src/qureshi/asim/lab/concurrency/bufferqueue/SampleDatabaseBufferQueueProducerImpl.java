package qureshi.asim.lab.concurrency.bufferqueue;

import qureshi.asim.lab.concurrency.bufferqueue.producer.BufferQueueProducer;
import qureshi.asim.lab.concurrency.Main;
import qureshi.asim.lab.concurrency.SampleDatabase;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: asim.qureshi
 * Date: 11/9/14
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class SampleDatabaseBufferQueueProducerImpl extends BufferQueueProducer<QueueItem> {


    public SampleDatabaseBufferQueueProducerImpl(BufferQueue<QueueItem> bufferQueue) {
        super(bufferQueue);
    }

    public SampleDatabaseBufferQueueProducerImpl(BufferQueue<QueueItem> queue, boolean isRemainingCapacityThresholdAware) {
        super(queue, isRemainingCapacityThresholdAware);
    }

    @Override
    protected void putItems(BufferQueue<QueueItem> bufferQueue) {

        List<QueueItem> items = SampleDatabase.queryItems(Main.FETCH_SIZE);

        if(items != null){
            for (QueueItem item : items) {

                bufferQueue.put(item);


            }
        }

//        print("Items put in Queue");
    }


}
