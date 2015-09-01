package io.shekhar.blogs.rxjava.sentiment_analyzer;

import rx.Observable;
import rx.observables.ConnectableObservable;
import rx.observables.GroupedObservable;
import twitter4j.Status;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class SentimentAnalyzer {

    private static final String[] NEGATIVE_WORDS = words("src/main/resources/negative_words.txt");
    private static final String[] POSITIVE_WORDS = words("src/main/resources/positive_words.txt");


    public static void main(String[] args) throws Exception {
        String[] searchTerms = {"Justin Bieber"};
        ConnectableObservable<Status> observable = TweetObservable.tweetObservable(searchTerms).publish();
        observable.connect();
        Observable<String> tweetStream = observable.map(Status::getText);
        tweetStream.forEach(System.out::println);

        tweetStream.groupBy(tweet -> sentiment(tweet)).subscribe(
                (GroupedObservable<Sentiment, String> gr) ->
                        gr.asObservable().forEach(tweet -> System.out.println(String.format("%s >> %s", gr.getKey(), tweet))));
    }


    private static Sentiment sentiment(String tweet) {
        if (isNegativeTweet(tweet)) {
            return Sentiment.NEGATIVE;
        } else if (isPositiveTweet(tweet)) {
            return Sentiment.POSITIVE;
        }
        return Sentiment.NEUTRAL;
    }

    private static boolean isNegativeTweet(String tweet) {
        String[] words = tweet.toLowerCase().split("\\s");
        return Arrays.stream(words).anyMatch(word -> Arrays.binarySearch(NEGATIVE_WORDS, word) >= 0);
    }

    private static boolean isPositiveTweet(String tweet) {
        String[] words = tweet.toLowerCase().split("\\s");
        return Arrays.stream(words).anyMatch(word -> Arrays.binarySearch(POSITIVE_WORDS, word) >= 0);
    }

    private static String[] words(String file) {
        try {
            return Files.readAllLines(Paths.get(file)).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[]{};
        }
    }


}
