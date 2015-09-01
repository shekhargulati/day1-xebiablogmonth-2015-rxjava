package io.shekhar.blogs.rxjava;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

public class Example2 {

    public static void main(String[] args) {
        List<String> tweets = Arrays.asList("learning RxJava", "Writing blog about RxJava", "RxJava rocks!!");
        Observable<String> observable = Observable.from(tweets);
        observable.subscribe(System.out::println);
    }
}
