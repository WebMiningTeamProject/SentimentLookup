package sentiment; /**
 * Created by Jan P. on 15.05.2017.
 */
import java.util.List;

public class SentimentProcessor {

    public static void main(String[] args) {
        System.out.println((getSentiment("I love you.")));
    }

    public static double getSentiment(String text){

        TextProcessor textProcessor = new TextProcessor();

        String pathToSWN = "./src/main/resources/SentiWordNet_3.0.0_20130122.txt";
        SentimentLookup sentimentLookup = new SentimentLookup(pathToSWN);
        List<String> bow = textProcessor.tokenizePOStagLemmatize(text);

        int numberOfWordsWithSentiment = 0;
        double totalSentimentScore = 0.0;

        for(String token : bow){

            // reduce noun granularity
            token = token
                    .replaceAll("_NN", "_N")
                    .replaceAll("_NNS", "_N")
                    .replaceAll("_NNP", "_N")
                    .replaceAll("_NNPS", "_N")
                    // reduce verb granularity
                    .replaceAll("_VB", "_V")
                    .replaceAll("_VBD", "_V")
                    .replaceAll("_VBG", "_V")
                    .replaceAll("_VBZ", "_V")
                    .replaceAll("_VP", "_V")
                    //reduce adjective granularity
                    .replaceAll("_JJ", "_A")
                    .replaceAll("_JJR", "_A")
                    .replaceAll("_JJS", "_A")
                    .replaceAll("_ADJP", "_A")
                    //reduce adverb granularity
                    .replaceAll("_RB", "_R")
                    .replaceAll("_RBR", "_R")
                    .replaceAll("_RBS", "_R")
                    .replaceAll("_ADVP", "_R");

                double sentimentScoreOfWord = 0.0;
                String word = "";
                String posTag = "";

                if(token.endsWith("_N")){
                    // get sentiment for nouns
                    word = token.toLowerCase().substring(0, token.length() - 2);
                    posTag = token.toLowerCase().substring(token.length()-1, token.length());
                    sentimentScoreOfWord = sentimentLookup.getSentimentForWord(word, posTag);

                    if(sentimentScoreOfWord != 500) {
                        // System.out.println(word + " (" + posTag + ") has sentiment: " + sentimentScoreOfWord);
                        totalSentimentScore = totalSentimentScore + sentimentScoreOfWord;
                        numberOfWordsWithSentiment++;
                    }


                } else if (token.endsWith("_V")){
                    // get sentiment for verbs
                    word = token.toLowerCase().substring(0, token.length() - 2);
                    posTag = token.toLowerCase().substring(token.length()-1, token.length());
                    sentimentScoreOfWord = sentimentLookup.getSentimentForWord(word, posTag);

                    if(sentimentScoreOfWord != 500) {
                        // System.out.println(word + " (" + posTag + ") has sentiment: " + sentimentScoreOfWord);
                        totalSentimentScore = totalSentimentScore + sentimentScoreOfWord;
                        numberOfWordsWithSentiment++;
                    }

                } else if (token.endsWith("_A")){
                    // get sentiment for adjectives
                    word = token.toLowerCase().substring(0, token.length() - 2);
                    posTag = token.toLowerCase().substring(token.length()-1, token.length());
                    sentimentScoreOfWord = sentimentLookup.getSentimentForWord(word, posTag);

                    if(sentimentScoreOfWord != 500) {
                        // System.out.println(word + " (" + posTag + ") has sentiment: " + sentimentScoreOfWord);
                        totalSentimentScore = totalSentimentScore + sentimentScoreOfWord;
                        numberOfWordsWithSentiment++;
                    }

                } else if (token.endsWith("_R")){
                    // get sentiment for adverbs
                    word = token.toLowerCase().substring(0, token.length() - 2);
                    posTag = token.toLowerCase().substring(token.length()-1, token.length());
                    sentimentScoreOfWord = sentimentLookup.getSentimentForWord(word, posTag);

                    if(sentimentScoreOfWord != 500) {
                        // System.out.println(word + " (" + posTag + ") has sentiment: " + sentimentScoreOfWord);
                        totalSentimentScore = totalSentimentScore + sentimentScoreOfWord;
                        numberOfWordsWithSentiment++;
                    }
            }

        } // end of for loop

        double averageSentimentInText = (totalSentimentScore / numberOfWordsWithSentiment);
        // System.out.println(averageSentimentInText);
        return averageSentimentInText;

    }
}
