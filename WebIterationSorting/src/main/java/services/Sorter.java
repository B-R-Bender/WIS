package services;

import beans.SortingResult;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by bender on 20.08.16.
 */
@ManagedBean(name = "sorter")
@SessionScoped
public class Sorter implements Serializable {

    private String order;
    private String sortingType;
    private Deque<SortingResult> history;

    @PostConstruct
    public void init() {
        history = new LinkedList<>();
    }

    public Sorter() {
    }

    public void sortAscend() {

    }

    public void sortDescend() {

    }

    public int[] sort(int[] arrayToSort) {

        int[] result;

        result = bubbleSort(arrayToSort);

        return result;
    }

    /**
     * Strait method of bubble sort
     * @param arrayToSort
     * @return
     */
    private int[] bubbleSort(int[] arrayToSort) {
        for (int i = 0; i < arrayToSort.length - 1; i++) {
            for (int j = 0; j < arrayToSort.length - 1 - i; j++) {
                if (arrayToSort[j] > arrayToSort[j+1]){
                    int temp = arrayToSort[j + 1];
                    arrayToSort[j + 1] = arrayToSort[j];
                    arrayToSort[j] = temp;
                }
            }
        }
        return arrayToSort;
    }

    /**
     * Improved method of bubble sort.
     * Breaks iteration if array was sorted before loop ends
     * @param arrayToSort
     * @return
     */
    private int[] bubbleSortImproved(int[] arrayToSort) {
        boolean isArraySorted;
        for (int i = 0; i < arrayToSort.length - 1; i++) {
            isArraySorted = true;
            for (int j = 0; j < arrayToSort.length - 1 - i; j++) {
                if (arrayToSort[j] > arrayToSort[j+1]){
                    int temp = arrayToSort[j + 1];
                    arrayToSort[j + 1] = arrayToSort[j];
                    arrayToSort[j] = temp;
                    isArraySorted = false;
                }
            }
            if (isArraySorted) {
                break;
            }
        }
        return arrayToSort;
    }

    /**
     * Improved method of bubble sort.
     * Breaks iteration if array was sorted before loop ends
     * @param arrayToSort
     * @return
     */
    private int[] insertionSort(int[] arrayToSort) {
        boolean isArraySorted;
        for (int i = 0; i < arrayToSort.length - 1; i++) {
            isArraySorted = true;
            for (int j = 0; j < arrayToSort.length - 1 - i; j++) {
                if (arrayToSort[j] > arrayToSort[j+1]){
                    int temp = arrayToSort[j + 1];
                    arrayToSort[j + 1] = arrayToSort[j];
                    arrayToSort[j] = temp;
                    isArraySorted = false;
                }
            }
            if (isArraySorted) {
                break;
            }
        }
        return arrayToSort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSortingType() {
        return sortingType;
    }

    public void setSortingType(String sortingType) {
        this.sortingType = sortingType;
    }

    public Deque<SortingResult> getHistory() {
        return history;
    }

    public void setHistory(Deque<SortingResult> history) {
        this.history = history;
    }
}