package qureshi.asim.lab.concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: asim.qureshi
 * Date: 11/8/14
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueueItem {

    private int id;

    public QueueItem(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object obj) {

        if(obj == null) return false;

        if(!(obj instanceof QueueItem)) return false;

        return this.id == ((QueueItem)obj).id;
    }

    @Override
    public String toString() {
        return "QueueItem{"  + id +  '}';
    }

    public int getId() {
        return id;
    }
}
