import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConnector {

    private static Twitter twitter;

    private static void createInstance() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("rlLAyou6f3ThtkYvGNjRDWPvI")
                .setOAuthConsumerSecret("Ev5WYewGBVxsifkssQhPksALH8TziVWRne77v8JdztYBsoVTFF")
                .setOAuthAccessToken("67405530-OXaOyQHZTRnXW7dBdVMLf2XyONV7SDe6uq2gOVsQz")
                .setOAuthAccessTokenSecret("nE7lkhsgbR97gkW1FZrrHNfZp4uI4ByeEW88mA8JCPgr6");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public static Twitter getInstance() {
        if(twitter == null) {
            createInstance();
        }
        return twitter;
    }
}
