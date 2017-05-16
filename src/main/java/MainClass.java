import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/**
 * Created by Jan Portisch on 15.05.2017.
 */
import java.io.IOException;
import java.util.List;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class MainClass {
    public static void main(String[] args) throws IOException,
            ClassNotFoundException {

        StanfordLemmatizer sl = new StanfordLemmatizer();
        List<String> bow = sl.tokenizePOStagLemmatize("This is my book.");



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
                    .replaceAll("VBZ", "_V")
                    //reduce adjective granularity
                    .replaceAll("_JJ", "_A")
                    .replaceAll("_JJR", "_A")
                    .replaceAll("_JJS", "_A")
                    //reduce adverb granularity
                    .replaceAll("_RB", "_R")
                    .replaceAll("_RBR", "_R")
                    .replaceAll("_RBS", "_R");

            if(token.endsWith("_N")){
                System.out.println(token);
            }

        }


}
}
