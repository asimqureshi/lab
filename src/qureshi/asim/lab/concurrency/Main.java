package qureshi.asim.lab.concurrency;

import qureshi.asim.lab.concurrency.bufferqueue.*;
import qureshi.asim.lab.concurrency.bufferqueue.consumer.ConsumerManager;
import qureshi.asim.lab.concurrency.bufferqueue.producer.BufferQueueProducer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    private final static int NO_OF_CONSUMERS = 25;

    private final static int BUFFER_CAPACITY = 200;
    private final static int BUFFER_REMAINING_CAPACITY_THRESHOLD = 25;

    private final static int PRODUCER_DELAY_IN_SECS = 5;
    private final static int CONSUMER_DELAY_IN_SECS = 2;


    public static final int TOT_ITEMS = 250;
    public static final int FETCH_SIZE = 50;


    private static final long TOTAL_PROGRAM_TIME_IN_SECS = 70 * 1000;

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        System.out.println("In Main...." + startTime);

        BufferQueueContainer container = new BufferQueueContainer();
        container.init();
        container.start();

        simulateConsumers(container);

        container.shutdown();

        System.out.println("Out Main...");
        long endTimeInSecs
                = (System.currentTimeMillis() - startTime)/1000L;
        System.out.println("Total time in secs:" + endTimeInSecs);

    }

    private static void simulateConsumers(BufferQueueContainer container) {

        ScheduledExecutorService consumers = Executors.newSingleThreadScheduledExecutor();

        ExecutorService consumer = Executors.newFixedThreadPool(NO_OF_CONSUMERS);

        consumers.scheduleAtFixedRate(new ConsumerManager(container, consumer, NO_OF_CONSUMERS), 2, CONSUMER_DELAY_IN_SECS, TimeUnit.SECONDS);


        try {

            Thread.sleep(TOTAL_PROGRAM_TIME_IN_SECS); //1 min

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        consumer.shutdownNow();
        consumers.shutdownNow();
    }


}
