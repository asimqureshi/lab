package qureshi.asim.lab.concurrency;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer extends Task {

    private BufferQueue<QueueItem> bufferQueue;

    public Producer(BufferQueue<QueueItem> bufferQueue) {
        this.bufferQueue = bufferQueue;
    }


    @Override
    public void run() {

       // print("In Producer");

        putItems(bufferQueue);

//        print("Exit Producer");


    }

    private void putItems(BufferQueue<QueueItem> bufferQueue) {

        List<QueueItem> items = SampleDatabase.queryItems();

        if(items != null){
            for (QueueItem item : items) {

                bufferQueue.put(item);


            }
        }

//        print("Items put in Queue");
    }
}
