package qureshi.asim.lab.concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: asim.qureshi
 * Date: 11/8/14
 * Time: 4:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Consumer extends Task {

    private BufferQueue<QueueItem> bufferQueue;


    public Consumer(BufferQueue<QueueItem> bufferQueue) {
        this.bufferQueue = bufferQueue;
    }

    @Override
    public void run() {

     //   print("In Consumer");
        QueueItem item = bufferQueue.get();

        try {
            Thread.sleep(1000/2);       // Do something with the item
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
//        print("item = " + item + ":" + System.currentTimeMillis());
       // print("Out Consumer");

    }
}
