package io.shekhar.blogs.rxjava;

import rx.Observable;

public class Example1 {

    public static void main(String[] args) {
        Observable<String> tweets = Observable.just("learning RxJava", "Writing blog about RxJava", "RxJava rocks!!");
        tweets.subscribe(System.out::println);

        // tweets.subscribe(tweet -> System.out.println(tweet));

        // Action1<String> printAction = tweet -> System.out.println(tweet)
        // with method reference

    }
}
