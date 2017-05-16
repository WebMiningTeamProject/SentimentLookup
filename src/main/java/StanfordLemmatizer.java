

/**
 * Created by D060249 on 16.05.2017.
 */
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.ling.CoreLabel;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;


/**
 * This can tokenize, lemmatize and annotate texts.
 */
public class StanfordLemmatizer {

    private StanfordCoreNLP pipeline;

    public StanfordLemmatizer() {
        // Create StanfordCoreNLP object properties, with POS tagging
        // (required for lemmatization), and lemmatization
        Properties props;
        props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma");

        // StanfordCoreNLP loads a lot of models, so you probably
        // only want to do this once per execution
        this.pipeline = new StanfordCoreNLP(props);
    }

    public List<String> tokenizePOStagLemmatize(String documentText)
    {
        List<String> lemmas = new LinkedList<String>();

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(documentText);

        // run all Annotators on this text
        this.pipeline.annotate(document);

        // Iterate over all of the sentences found
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for(CoreMap sentence: sentences) {
            // Iterate over all tokens in a sentence
            for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // Retrieve and add the lemma for each word into the list of lemmas
                lemmas.add(token.get(CoreAnnotations.LemmaAnnotation.class) + "_" + token.get(CoreAnnotations.PartOfSpeechAnnotation.class));

            }
        }
        return lemmas;
    }
}