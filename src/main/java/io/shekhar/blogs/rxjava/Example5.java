package io.shekhar.blogs.rxjava;

import rx.Observable;
import rx.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class Example5 {

    public static void main(String[] args) throws Exception {
        ConnectableObservable<Long> hotObservable = Observable.interval(1, TimeUnit.SECONDS).publish();
        hotObservable.subscribe(val -> System.out.println("Subscriber 1 >> " + val));
        hotObservable.connect();

        Thread.sleep(5000);

        hotObservable.subscribe(val -> System.out.println("Subscriber 2 >> " + val));

        Thread.sleep(5000);
    }
}
