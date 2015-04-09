package Objects;

import java.util.Random;

/**
 * Created by macbook (Stephen) on 2/12/15.
 */

public class SortedData implements Comparable<SortedData>{

    //declaring variables
    //  desc: arbitrary String
    //     sentence: String containing the value and dataId
    //           dataId: distinct identifier
    //        value: number to be sorted
    private String desc, sentence;
    private int dataId, value;

    public SortedData(int id_in) {

        // Function's needed variables
        Random random = new Random();
        int minValue = 0;
        int maxValue = 25;
        int descriptionLength = 10;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        // identify
        this.dataId = id_in;
        this.value = 0;
        this.desc = "";
        this.sentence = "";

        // assign a random sorting value
        this.value = random.nextInt((maxValue - minValue) + 1) + minValue;

        // random string
        for (int i = 0; i < descriptionLength; i++)
            this.desc += alphabet.charAt(random.nextInt(alphabet.length()));

        // constructed sentence
        this.sentence = "The data [" + this.dataId + "] has value [" + this.value + "].";
    }

    public SortedData(String description_in, String sentence_in, int id_in, int value_in) {
        this.desc = description_in;
        this.sentence = sentence_in;
        this.dataId = id_in;
        this.value = value_in;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getSentence() {
        return this.sentence;
    }

    public int getDataId() {
        return this.dataId;
    }

    public int getValue() {
        return this.value;
    }
    public void setDesc(String description_in) {
         this.desc = description_in;
    }

    public void setSentence(String sentence_in) {
         this.sentence =sentence_in;
    }

    public void setDataId(int id_in) {
         this.dataId = id_in;
    }

    public void setValue(int value_in) {
         this.value = value_in;
    }

    public int compareTo (SortedData otherData)
    {
        int  relation = 0;

        // Primary, sort by descending value
        relation = (otherData.getValue() - this.getValue());

        // Secondary, sort by ascending dataId
        if (relation == 0)
            relation = (this.getDataId() - otherData.getDataId());

        return relation;
    }

}
