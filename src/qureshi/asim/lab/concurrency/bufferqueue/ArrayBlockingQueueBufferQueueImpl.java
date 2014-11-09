package qureshi.asim.lab.concurrency.bufferqueue;

import java.io.Serializable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueBufferQueueImpl<T> implements BufferQueue<T> {


    private BufferQueue<T> priorityQueue;
    private int capacity = 120;
    private int remainingCapacityThreshold = 20;
    private int waitTimeForGetInSecs = 2;



   // private ArrayBlockingQueue<T> queue =  new ArrayBlockingQueue<T>(capacity, true);

    protected LinkedBlockingQueue<T> queue;

    public ArrayBlockingQueueBufferQueueImpl() {

        queue = new LinkedBlockingQueue<T>(capacity);
    }

    public ArrayBlockingQueueBufferQueueImpl(int capacity) {

        queue = new LinkedBlockingQueue<T>(capacity);
        this.capacity = capacity;
    }


    public ArrayBlockingQueueBufferQueueImpl(int capacity, int remainingCapacityThreshold) {

        queue = new LinkedBlockingQueue<T>(capacity);
        this.capacity = capacity;
        this.remainingCapacityThreshold = remainingCapacityThreshold;

    }

    public ArrayBlockingQueueBufferQueueImpl(int capacity, int remainingCapacityThreshold, int waitTimeForGetInSecs) {

        queue = new LinkedBlockingQueue<T>(capacity);
        this.capacity = capacity;
        this.remainingCapacityThreshold = remainingCapacityThreshold;
        this.waitTimeForGetInSecs = waitTimeForGetInSecs;

    }

    protected void print(Serializable t){

        System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread().getName() + ":" + t);
    }

    @Override
    public T get(){

        try {

            T item = queue.poll(waitTimeForGetInSecs, TimeUnit.SECONDS);

            print("GET->" + item);

            return item;

        } catch (InterruptedException e) {

        }

        return null;

    }

      @Override
      public boolean put(T item){

          print("PUT->" + item);

          boolean returnVal =false;

          try {
              returnVal = queue.add(item);
          } catch (Exception e) {

              print("Queue FFFFFFUUUUUUUUUULLLLLLLLLLLL!!!!!!!!!! losing item" + item);

          }

          return returnVal;

      }




    @Override
    public boolean remainingCapacityThresholdReached() {

        //print(":RC:"+queue.remainingCapacity());

        return queue.remainingCapacity() < remainingCapacityThreshold;
    }

    @Override
    public boolean prioritize(T item) {

        boolean operationSuccessful;

        if(null != priorityQueue) {

            try {

                operationSuccessful = queue.remove(item);

                if(!operationSuccessful) return false;

                operationSuccessful = priorityQueue.put(item);

                if(!operationSuccessful) {  put(item); return false; }

            } catch (Throwable t) {

                return false;
            }

        }

        return true;
    }

}
