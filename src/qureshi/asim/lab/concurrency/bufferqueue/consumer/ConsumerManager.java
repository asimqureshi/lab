package qureshi.asim.lab.concurrency.bufferqueue.consumer;

import qureshi.asim.lab.concurrency.bufferqueue.BufferQueue;
import qureshi.asim.lab.concurrency.bufferqueue.BufferQueueContainer;
import qureshi.asim.lab.concurrency.bufferqueue.QueueItem;
import qureshi.asim.lab.concurrency.Worker;

import java.util.concurrent.ExecutorService;

/**
 * Created with IntelliJ IDEA.
 * User: asim.qureshi
 * Date: 11/8/14
 * Time: 9:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConsumerManager extends Worker {

    private ExecutorService consumers;
    private int consumerCount;

    BufferQueueContainer queueContainer;

    public ConsumerManager(BufferQueueContainer queueContainer, ExecutorService consumers, int consumerCount) {

        this.queueContainer = queueContainer;
        this.consumers = consumers;
        this.consumerCount = consumerCount;
    }

    @Override
    public void execute() {

        for(int i=0;i<consumerCount;i++)
            consumers.execute(new Consumer(queueContainer.getQueue()));

    }
}
