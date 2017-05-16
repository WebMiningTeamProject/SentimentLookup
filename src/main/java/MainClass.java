import database.ArticlesFromDb;
import database.DatabaseHandler;
import sentiment.SentimentProcessor;

import java.util.List;

/**
 * Created by Jan P. on 16.05.2017.
 */
public class MainClass {


    public static void main(String[] args) {

        DatabaseHandler db = DatabaseHandler.setUpDatabaseHandler();
        if(db == null){
            System.out.println("Could not setup database handlers");
            return;
        }

        List<ArticlesFromDb> articles = db.listOfNotProcessedArticles(100);

        for(ArticlesFromDb article : articles){
            System.out.println("Processing: " + article.getSource_uri());
            double sentimentScore = 0.0;


            if(article.getText() == null){
                continue;
            }

            sentimentScore = SentimentProcessor.getSentiment(article.getText());
            int sentimentScoreBinary = 0;
            if(sentimentScore > 0){
                sentimentScoreBinary = 1;
            }
            db.writeSentiment(article.getSource_uri(), sentimentScore, sentimentScoreBinary);

            System.out.println("Sentiment: " + sentimentScore + "\n");

        }




    }




}
