package io.shekhar.blogs.rxjava;

import rx.Observable;

import java.util.stream.IntStream;

public class Example4 {

    public static Observable<Integer> naturalNumbers(int n) {
        return Observable.create(subscriber -> {
            IntStream.rangeClosed(1, n).forEach(number -> subscriber.onNext(number));
            subscriber.onCompleted();
        });
    }

    public static void main(String[] args) {
        Observable<Integer> obs1 = naturalNumbers(10);
        obs1.subscribe(System.out::println);
    }
}
