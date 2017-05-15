import edu.stanford.nlp.tagger.maxent.MaxentTagger;
/**
 * Created by Jan Portisch on 15.05.2017.
 */
import java.io.IOException;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class MainClass {
    public static void main(String[] args) throws IOException,
            ClassNotFoundException {

        String a = "I love going to university.";
        MaxentTagger tagger =  new MaxentTagger("./src/main/resources/taggers/english-left3words-distsim.tagger");
        String tagged = tagger.tagString(a);
        System.out.println(tagged);


}
}
