package qureshi.asim.lab.concurrency;

import qureshi.asim.lab.concurrency.bufferqueue.QueueItem;

import java.util.*;


public class SampleDatabase {

    private static final int TOT_ITEMS = Main.TOT_ITEMS;
    private static final int FETCH_SIZE = Main.FETCH_SIZE;

    private static Map<QueueItem, Boolean> database = Collections.synchronizedMap(new  HashMap<QueueItem, Boolean>(TOT_ITEMS));

    static {

        for(int i=0;i<TOT_ITEMS;i++){

            database.put(new QueueItem(i), Boolean.FALSE);

        }

    }

    private static int timesDatabaseQueried = 1;
    private static boolean isEmpty = false;


    public static boolean isEmpty(){

        return isEmpty;
    }

    public static synchronized List<QueueItem> queryItems( int fetchSize){

        if(isEmpty) {

            //System.out.println("Returning null...");
            return null;            }


//        System.out.println("timesDatabaseQueried = " + timesDatabaseQueried);

        List<QueueItem> returnItems = new ArrayList<QueueItem>(fetchSize);

        int fetchCount = 0;

        for (QueueItem queueItem : database.keySet()) {

            if(!database.get(queueItem)) { //pickedFlag is false

                returnItems.add(queueItem);           //add to return list
                database.put(queueItem, Boolean.TRUE);  //update in database
                fetchCount++;

                if(fetchCount == FETCH_SIZE) break;

            }

        }

        if(fetchCount == 0){

            isEmpty = true;
            System.err.println("Database is Empty now!!!! ::: in " + timesDatabaseQueried);
            new RuntimeException("Database is Empty now!!!!" + timesDatabaseQueried);

        }


        timesDatabaseQueried++;
        return returnItems;
    }
}
