package qureshi.asim.lab.concurrency;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: asim.qureshi
 * Date: 11/8/14
 * Time: 4:39 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Worker implements Runnable {


    public void print(Serializable str){

        if(null == str) str="";

        System.out.println("[" + Thread.currentThread().getName() +   "]" + str);


    }


    @Override
    public void run() {

          this.execute();

    }


    public abstract void execute();



}
