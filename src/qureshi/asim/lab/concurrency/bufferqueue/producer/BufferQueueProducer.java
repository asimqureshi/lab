package qureshi.asim.lab.concurrency.bufferqueue.producer;

import qureshi.asim.lab.concurrency.bufferqueue.QueueItem;
import qureshi.asim.lab.concurrency.Worker;
import qureshi.asim.lab.concurrency.bufferqueue.BufferQueue;

public abstract class BufferQueueProducer<T> extends Worker {

    private BufferQueue<T> queue;
    private boolean isRemainingCapacityThresholdAware = false;

    public BufferQueueProducer(BufferQueue<T> queue) {
        this.queue = queue;
    }

    public BufferQueueProducer(BufferQueue<T> queue, boolean isRemainingCapacityThresholdAware) {
        this.queue = queue;
        this.isRemainingCapacityThresholdAware = isRemainingCapacityThresholdAware;
    }


    @Override
    public void execute() {

       // print("In BufferQueueProducer");

       if(isRemainingCapacityThresholdAware){

           while (!queue.remainingCapacityThresholdReached()){
               putItems(queue);
           }

       }   else {

           putItems(queue);
       }




//        print("Exit BufferQueueProducer");


    }



    protected abstract void putItems(BufferQueue<T> bufferQueue);
}
