package qureshi.asim.lab.concurrency;

import java.util.List;

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
