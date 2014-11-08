package qureshi.asim.lab.concurrency;

import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueImpl<T> implements BufferQueue<T> {

    private int size = 120;

   // private ArrayBlockingQueue<T> queue =  new ArrayBlockingQueue<T>(size, true);

    private LinkedBlockingQueue<T> queue =  new LinkedBlockingQueue<T>(size);


    public ArrayBlockingQueueImpl(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    private void print(Serializable t){

        System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread().getName() + ":" + t);
    }

    @Override
    public T get(){

        try {

            T item = queue.poll(2, TimeUnit.SECONDS);

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

}
