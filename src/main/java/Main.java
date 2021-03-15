import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws TwitterException {
        displayTwitterStatusStream();
    }

    /**
     * Display tweets from tweeter stream observable
     */
    private static void displayTwitterStatusStream() {
        Flowable<Status> statuses = tweetObservable();
        statuses.subscribe(status -> {
            System.out.println(status.getText());
        });
    }

    /**
     * Wrap Twitter Stream API into an RxJAVA Observable
     * @return
     */
    private static Flowable<Status> tweetObservable() {
        return Flowable.create(subscriber -> {
            final TwitterStream twitterStream = TwitterStreamConnector.getInstance();
            twitterStream.addListener(new StatusAdapter() {
                public void onStatus(Status status) {
                    subscriber.onNext(status);
                }

                public void onException(Exception ex) {
                    subscriber.onError(ex);
                }
            });
            twitterStream.sample();
        }, BackpressureStrategy.ERROR);
    }

    /**
     * Basic Twitter API Sample
     */
    private static void displayTwitterTimeline() {
        try {
            Twitter twitter = TwitterConnector.getInstance();
            List<String> tweets = twitter.getHomeTimeline().stream()
                    .map(item -> item.getText())
                    .collect(Collectors.toList());
            tweets.forEach(System.out::println);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
