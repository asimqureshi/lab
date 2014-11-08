package qureshi.asim.lab.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestRunner {

    private final static int NO_OF_CONSUMERS = 25;
    private final static int BUFFER_SIZE = 200;
    private final static int PRODUCER_DELAY_IN_SECS = 2;
    private final static int CONSUMER_DELAY_IN_SECS = 2;


    public static final int TOT_ITEMS = 100;
    public static final int FETCH_SIZE = 5;

    private static final long TOTAL_PROGRAM_TIME_IN_SECS = 70 * 1000;

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        System.out.println("In Main...." + startTime);

        BufferQueue<QueueItem> queue = new ArrayBlockingQueueImpl<QueueItem>(BUFFER_SIZE);

        ScheduledExecutorService producers = Executors.newSingleThreadScheduledExecutor();

        Producer producer = new Producer(queue);
         //producer.run(); // Executing once to fill the database


        producers.scheduleAtFixedRate(producer, 0, PRODUCER_DELAY_IN_SECS, TimeUnit.SECONDS);

        ScheduledExecutorService consumers = Executors.newSingleThreadScheduledExecutor();

        ExecutorService consumer = Executors.newFixedThreadPool(NO_OF_CONSUMERS);

        consumers.scheduleAtFixedRate(new ConsumerManager(queue, consumer, NO_OF_CONSUMERS), 4, CONSUMER_DELAY_IN_SECS, TimeUnit.SECONDS);


        try {

            Thread.sleep(TOTAL_PROGRAM_TIME_IN_SECS); //1 min

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        consumer.shutdown();
        consumers.shutdown();
        producers.shutdown();

        try {

            consumer.awaitTermination(10, TimeUnit.SECONDS);
            consumers.awaitTermination(10, TimeUnit.SECONDS);
            producers.awaitTermination(10, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        System.out.println("Out Main...");
        long endTimeInSecs
                = (System.currentTimeMillis() - startTime)/1000L;
        System.out.println("Total time in secs:" + endTimeInSecs);

    }



}
