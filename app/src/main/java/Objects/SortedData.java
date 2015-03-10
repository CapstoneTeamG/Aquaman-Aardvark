package Objects;

import java.util.Random;

/**
 * Created by macbook (Stephen) on 2/12/15.
 */

public class SortedData implements Comparable<SortedData>{

    //declaring variables
    //  description: arbitrary String
    //     sentence: String containing the value and id
    //           id: distinct identifier
    //        value: number to be sorted
    private String description, sentence;
    private int id, value;

    public SortedData(int id_in) {

        // Function's needed variables
        Random random = new Random();
        int minValue = -50;
        int maxValue = 500;
        int descriptionLength = 10;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        // identify
        this.id = id_in;
        this.value = 0;
        this.description = "";
        this.sentence = "";

        // assign a random sorting value
        this.value = random.nextInt((maxValue - minValue) + 1) + minValue;

        // random string
        for (int i = 0; i < descriptionLength; i++)
            this.description += alphabet.charAt(random.nextInt(alphabet.length()));

        // constructed sentence
        this.sentence = "The data [" + this.id + "] has value [" + this.value + "].";
    }

    public SortedData(String description_in, String sentence_in, int id_in, int value_in) {
        this.description = description_in;
        this.sentence = sentence_in;
        this.id = id_in;
        this.value = value_in;
    }

    public String getDescription() {
        return this.description;
    }

    public String getSentence() {
        return this.sentence;
    }

    public int getId() {
        return this.id;
    }

    public int getValue() {
        return this.value;
    }
    public void setDescription(String description_in) {
         this.description = description_in;
    }

    public void setSentence(String sentence_in) {
         this.sentence =sentence_in;
    }

    public void setId(int id_in) {
         this.id = id_in;
    }

    public void setValue(int value_in) {
         this.value = value_in;
    }

    public int compareTo (SortedData otherData)
    {
        int  relation = 0;

        // Primary, sort by descending value
        relation = (otherData.getValue() - this.getValue());

        // Secondary, sort by ascending id
        if (relation == 0)
            relation = (this.getId() - otherData.getId());

        return relation;
    }

}
