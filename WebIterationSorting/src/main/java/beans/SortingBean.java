package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Homenko on 18.08.2016.
 */
@ManagedBean(name = "sortingBean")
@SessionScoped
public class SortingBean implements Serializable {

    private String userInput;
    private String resultOutput;

    public void sort() {

        String[] spitedInput = getUserInput().split(" ");
        int[] intArray = new int[spitedInput.length];

        for (int i = 0; i < spitedInput.length; i++) {
            if (!"".equals(spitedInput[i])){
                intArray[i] = Integer.parseInt(spitedInput[i]);
            }
        }

        sort(intArray);

        setResultOutput(Arrays.toString(intArray));
    }

    private void sort(int[] arrayToSort) {
        for (int i = 0; i < arrayToSort.length - 1; i++) {
            for (int j = 0; j < arrayToSort.length - 1 - i; j++) {
                if (arrayToSort[j] > arrayToSort[j+1]){
                    int temp = arrayToSort[j + 1];
                    arrayToSort[j + 1] = arrayToSort[j];
                    arrayToSort[j] = temp;
                }
            }
        }
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String getResultOutput() {
        return resultOutput;
    }

    public void setResultOutput(String resultOutput) {
        this.resultOutput = resultOutput;
    }
}
