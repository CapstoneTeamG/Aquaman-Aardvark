package Objects;

import austin.aquaman_aardvark.Sort;

/**
 * Created by macbook on 2/12/15.
 */
public class SortedData {

//declaring variables
    private String description, sentence;
    private int id, sort;

    public SortedData(){

    }

    public SortedData(String info, String words, int number, int arrangement){
        this.description = info;
        this.sentence = words;
        this.id = number;
        this.sort = arrangement;
    }

    public String getDescription(){
        return this.description;
    }

    public String getSentence(){
        return this.sentence;
    }

    public int getId(){
        return this.id;
    }

    public int getSort(){
        return this.sort;
    }

}
