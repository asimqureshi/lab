package qureshi.asim.lab.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: asim.qureshi
 * Date: 11/8/14
 * Time: 9:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConsumerManager extends Task {

    private BufferQueue<QueueItem> queue;

    private ExecutorService consumers;
    private int consumerCount;

    public ConsumerManager(BufferQueue<QueueItem> queue, ExecutorService consumers, int consumerCount) {

        this.queue = queue;
        this.consumers = consumers;
        this.consumerCount = consumerCount;
    }

    @Override
    public void run() {

        for(int i=0;i<consumerCount;i++)
            consumers.execute(new Consumer(queue));

    }
}
