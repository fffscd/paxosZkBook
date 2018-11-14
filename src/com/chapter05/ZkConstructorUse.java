package com.chapter05;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZkConstructorUse implements Watcher {

    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {

        ZooKeeper zk = new ZooKeeper("localhost:2181",
                5000, new ZkConstructorUse());
        System.out.println(zk.getState());
        latch.await();
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Received watched event:" + event);
        if(Event.KeeperState.SyncConnected == event.getState()){
            latch.countDown();
        }
    }
}
