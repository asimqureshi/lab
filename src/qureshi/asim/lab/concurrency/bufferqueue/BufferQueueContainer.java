package qureshi.asim.lab.concurrency.bufferqueue;

import qureshi.asim.lab.concurrency.bufferqueue.producer.BufferQueueProducer;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/** http://www.ibm.com/developerworks/websphere/techjournal/0606_johnson/0606_johnson.html#sec5        */


public class BufferQueueContainer {


    private ThreadFactory threadFactory;
    private BufferQueue<QueueItem> queue;
    private BufferQueue<QueueItem> priorityQueue;
    private BufferQueueProducer<QueueItem> singleThreadedProducer;
    private Comparator<QueueItem> invalidateItemComparator;

    private int bufferCapacity = 200;
    private boolean isRemainingCapacityThresholdAware = false;
    private int bufferRemainingCapacityThreshold = 25;
    private int producerInitialDelayInSecs = 0;
    private int producerPeriodicDelayInSecs = 5;
    private int itemInvalidityTimeInSecs = -1; // Means Never
    private int waitTimeForGetInSecs = 2;

    private ScheduledExecutorService producerExecutorService;
    private ScheduledExecutorService invalidateExecutorService;




    public void init() {

        threadFactory = Executors.defaultThreadFactory();

        queue = new ArrayBlockingQueueBufferQueueImpl<QueueItem>(bufferCapacity, bufferRemainingCapacityThreshold, waitTimeForGetInSecs);
        priorityQueue = new ArrayBlockingQueueBufferQueueImpl<QueueItem>(bufferCapacity/4,0, waitTimeForGetInSecs);

        singleThreadedProducer = new SampleDatabaseBufferQueueProducerImpl(queue, isRemainingCapacityThresholdAware);

        producerExecutorService = Executors.newSingleThreadScheduledExecutor(threadFactory);

        if(itemInvalidityTimeInSecs > 0)
            invalidateExecutorService = Executors.newSingleThreadScheduledExecutor(threadFactory);



    }


    public void start() {

        producerExecutorService.scheduleAtFixedRate(singleThreadedProducer,producerInitialDelayInSecs,
                producerPeriodicDelayInSecs, TimeUnit.SECONDS);

        if(null != invalidateExecutorService)
            invalidateExecutorService.scheduleAtFixedRate(singleThreadedProducer,producerInitialDelayInSecs,
                producerPeriodicDelayInSecs, TimeUnit.SECONDS);

    }


    public void shutdown(){

        List<Runnable> tasks = producerExecutorService.shutdownNow();

        if(null != invalidateExecutorService)
            tasks.addAll(invalidateExecutorService.shutdownNow());

        print(tasks);

    }

    private void print(List<Runnable> tasks) {

        System.out.println("====== Tasks that failed to complete =======");

        for (Runnable task : tasks) {

            System.out.println("task = " + task);
        }

        System.out.println("============================================");

    }


    public ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    public void setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    public BufferQueue<QueueItem> getQueue() {
        return queue;
    }

    public void setQueue(BufferQueue<QueueItem> queue) {
        this.queue = queue;
    }

    public BufferQueue<QueueItem> getPriorityQueue() {
        return priorityQueue;
    }

    public void setPriorityQueue(BufferQueue<QueueItem> priorityQueue) {
        this.priorityQueue = priorityQueue;
    }

    public BufferQueueProducer<QueueItem> getSingleThreadedProducer() {
        return singleThreadedProducer;
    }

    public void setSingleThreadedProducer(BufferQueueProducer<QueueItem> singleThreadedProducer) {
        this.singleThreadedProducer = singleThreadedProducer;
    }

    public int getBufferCapacity() {
        return bufferCapacity;
    }

    public void setBufferCapacity(int bufferCapacity) {
        this.bufferCapacity = bufferCapacity;
    }

    public boolean isRemainingCapacityThresholdAware() {
        return isRemainingCapacityThresholdAware;
    }

    public void setRemainingCapacityThresholdAware(boolean remainingCapacityThresholdAware) {
        isRemainingCapacityThresholdAware = remainingCapacityThresholdAware;
    }

    public int getBufferRemainingCapacityThreshold() {
        return bufferRemainingCapacityThreshold;
    }

    public void setBufferRemainingCapacityThreshold(int bufferRemainingCapacityThreshold) {
        this.bufferRemainingCapacityThreshold = bufferRemainingCapacityThreshold;
    }

    public int getProducerInitialDelayInSecs() {
        return producerInitialDelayInSecs;
    }

    public void setProducerInitialDelayInSecs(int producerInitialDelayInSecs) {
        this.producerInitialDelayInSecs = producerInitialDelayInSecs;
    }

    public int getProducerPeriodicDelayInSecs() {
        return producerPeriodicDelayInSecs;
    }

    public void setProducerPeriodicDelayInSecs(int producerPeriodicDelayInSecs) {
        this.producerPeriodicDelayInSecs = producerPeriodicDelayInSecs;
    }

    public int getItemInvalidityTimeInSecs() {
        return itemInvalidityTimeInSecs;
    }

    public void setItemInvalidityTimeInSecs(int itemInvalidityTimeInSecs) {
        this.itemInvalidityTimeInSecs = itemInvalidityTimeInSecs;
    }

    public int getWaitTimeForGetInSecs() {
        return waitTimeForGetInSecs;
    }

    public void setWaitTimeForGetInSecs(int waitTimeForGetInSecs) {
        this.waitTimeForGetInSecs = waitTimeForGetInSecs;
    }

    public ScheduledExecutorService getProducerExecutorService() {
        return producerExecutorService;
    }

    public void setProducerExecutorService(ScheduledExecutorService producerExecutorService) {
        this.producerExecutorService = producerExecutorService;
    }

    public ScheduledExecutorService getInvalidateExecutorService() {
        return invalidateExecutorService;
    }

    public void setInvalidateExecutorService(ScheduledExecutorService invalidateExecutorService) {
        this.invalidateExecutorService = invalidateExecutorService;
    }
}
