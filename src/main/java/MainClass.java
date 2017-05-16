import database.ArticlesFromDb;
import database.DatabaseHandler;
import sentiment.SentimentProcessor;

import java.util.List;

/**
 * Created by Jan P. on 16.05.2017.
 */
public class MainClass {

    /**
     * Execute this method to create sentiment ratings for articles for which not sentiment has yet been calculated.
     * (a delta mechanism is implemented)
     * @param args
     */
    public static void main(String[] args) {

        DatabaseHandler db = DatabaseHandler.setUpDatabaseHandler();
        if(db == null){
            System.out.println("Could not setup database handlers");
            return;
        }

        // get the list of articles that were not previously processed
        List<ArticlesFromDb> articles = db.listOfArticlesNotProcessed();

        for(ArticlesFromDb article : articles){
            System.out.println("Processing: " + article.getSource_uri());
            double sentimentScore = 0.0;


            if(article.getText() == null){
                // skip empty articles
                continue;
            }

            sentimentScore = SentimentProcessor.getSentiment(article.getText());
            int sentimentScoreBinary = 0;
            if(sentimentScore > 0){
                sentimentScoreBinary = 1;
            }

            // write the sentiment score to the database
            db.writeSentiment(article.getSource_uri(), sentimentScore, sentimentScoreBinary);

            System.out.println("Sentiment: " + sentimentScore + "\n");

        }

        System.out.println("Done.");

    }
}
