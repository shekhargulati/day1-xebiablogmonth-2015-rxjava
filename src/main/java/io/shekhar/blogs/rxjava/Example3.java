package io.shekhar.blogs.rxjava;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

public class Example3 {

    public static void main(String[] args) {
        List<String> tweets = Arrays.asList("learning RxJava", "Writing blog about RxJava", "RxJava rocks!!");
        Observable<String> observable = Observable.from(tweets);
        observable.subscribe(tweet -> System.out.println("Subscriber 1 >> " + tweet));
        observable.subscribe(tweet -> System.out.println("Subscriber 2 >> " + tweet));
        observable.subscribe(tweet -> System.out.println("Subscriber 3 >> " + tweet));
    }
}
