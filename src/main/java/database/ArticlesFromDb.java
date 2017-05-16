package database;

/**
 * Created by Jan P. on 16.05.2017.
 */
/*
 * This class contains the BOW and URI for each news article.
 */
public class ArticlesFromDb {

    private String text;
    private String source_uri;


    public ArticlesFromDb(String source_uri, String text){
        this.setText(text);
        this.setSource_uri(source_uri);
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }


    public String getSource_uri() {
        return source_uri;
    }


    public void setSource_uri(String source_uri) {
        this.source_uri = source_uri;
    }

}