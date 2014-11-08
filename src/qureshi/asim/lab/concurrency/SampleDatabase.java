package qureshi.asim.lab.concurrency;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: asim.qureshi
 * Date: 11/8/14
 * Time: 7:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class SampleDatabase {

    private static final int TOT_ITEMS = TestRunner.TOT_ITEMS;
    private static final int FETCH_SIZE = TestRunner.FETCH_SIZE;

    private static Map<QueueItem, Boolean> database = Collections.synchronizedMap(new  HashMap<QueueItem, Boolean>(TOT_ITEMS));

    static {

        for(int i=0;i<TOT_ITEMS;i++){

            database.put(new QueueItem(i), Boolean.FALSE);

        }

    }

    private static int noOfIteration = 1;
    private static boolean isEmpty = false;


    public static boolean isEmpty(){

        return isEmpty;
    }

    public static synchronized List<QueueItem> queryItems(){

        if(isEmpty) return null;


        System.out.println("noOfIteration = " + noOfIteration);

        List<QueueItem> returnItems = new ArrayList<QueueItem>(FETCH_SIZE);

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
            System.err.println("Database is Empty now!!!! ::: in " + noOfIteration);
            new RuntimeException("Database is Empty now!!!!" + noOfIteration);

        }


        noOfIteration++;
        return returnItems;
    }
}
