package services;

import beans.SortingResult;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.*;

/**
 * Created by bender on 20.08.16.
 */
@ManagedBean(name = "sorter")
@SessionScoped
public class Sorter implements Serializable {

    private boolean order;
    private String sortingType;
    private Deque<SortingResult> history;

    @PostConstruct
    public void init() {
        history = new LinkedList<>();
    }

    public Sorter() {
    }

    public int[] sort(int[] arrayToSort) {

        switch (sortingType) {
            case "Bubble sort":
                return bubbleSort(arrayToSort);
            case "Selection sort":
                return selectionSort(arrayToSort);
            case "Insertion sort":
                return insertionSort(arrayToSort);
            default:
                return bubbleSort(arrayToSort);
        }
    }

    /**
     * Strait method of bubble sort
     * @param arrayToSort
     * @return
     */
    public int[] bubbleSort(int[] arrayToSort) {
        for (int i = 0; i < arrayToSort.length - 1; i++) {
            for (int j = 0; j < arrayToSort.length - 1 - i; j++) {
                if (compareWithDefinedSortingOrder(arrayToSort[j], arrayToSort[j+1])){
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
    public int[] bubbleSortImproved(int[] arrayToSort) {
        boolean isArraySorted;
        for (int i = 0; i < arrayToSort.length - 1; i++) {
            isArraySorted = true;
            for (int j = 0; j < arrayToSort.length - 1 - i; j++) {
                if (compareWithDefinedSortingOrder(arrayToSort[j], arrayToSort[j+1])){
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
     * Insertion sort.
     * @param arrayToSort
     * @return
     */
    public int[] insertionSort(int[] arrayToSort) {

        for (int outerLeftBorder = 1; outerLeftBorder < arrayToSort.length; outerLeftBorder++) {
            int currentElement = arrayToSort[outerLeftBorder];

            for (int innerBorder = outerLeftBorder - 1; innerBorder >= 0; innerBorder--) {
                if (compareWithDefinedSortingOrder(arrayToSort[innerBorder], currentElement)) {
                    arrayToSort[innerBorder + 1] = arrayToSort[innerBorder];
                    arrayToSort[innerBorder] = currentElement;
                } else {
                    break;
                }
            }
        }
        return arrayToSort;
    }
    /**
     * Improved method of Insertion sort.
     * searches for index of currently sorting element of array in sorted part of given arrayToSort using binarySearch
     * @param arrayToSort
     * @return
     */
    public int[] insertionSortImproved(int[] arrayToSort) {

        for (int outerLeftBorder = 1; outerLeftBorder < arrayToSort.length; outerLeftBorder++) {
            int currentElement = arrayToSort[outerLeftBorder];
            int positionForCurrentElement = binarySearch(arrayToSort, currentElement, 0, outerLeftBorder - 1);

            if (positionForCurrentElement < 0) {
                positionForCurrentElement = -(positionForCurrentElement + 1);
            }

            System.arraycopy(arrayToSort, positionForCurrentElement,
                    arrayToSort, positionForCurrentElement + 1,
                    outerLeftBorder - positionForCurrentElement);
            arrayToSort[positionForCurrentElement] = currentElement;
        }
        return arrayToSort;
    }

    /**
     * Strait classic selection sort
     * @param arrayToSort
     * @return
     */
    public int[] selectionSort(int[] arrayToSort) {

        for (int border = 0; border < arrayToSort.length; border++) {
            for (int currentElement = border + 1; currentElement < arrayToSort.length; currentElement++) {
                if (compareWithDefinedSortingOrder(arrayToSort[border], arrayToSort[currentElement])) {
                    int temp = arrayToSort[currentElement];
                    arrayToSort[currentElement] = arrayToSort[border];
                    arrayToSort[border] = temp;
                }
            }
        }

        return arrayToSort;
    }

    /**
     * Improved selection sort
     * @param arrayToSort
     * @return
     */
    public int[] selectionSortImproved(int[] arrayToSort) {

        for (int border = 0; border < arrayToSort.length; border++) {
            int min = arrayToSort[border];
            int minIndex = border;
            for (int currentElement = border + 1; currentElement < arrayToSort.length; currentElement++) {
                if (compareWithDefinedSortingOrder(min, arrayToSort[currentElement])) {
                    min = arrayToSort[currentElement];
                    minIndex = currentElement;
                }
            }
            arrayToSort[minIndex] = arrayToSort[border];
            arrayToSort[border] = min;
        }
        return arrayToSort;
    }

    private int binarySearch (int[] arrayToSearch, int elementToSearchFor, int indexFrom, int indexTo){
        int leftBorderIndex = indexFrom;
        int rightBorderIndex = indexTo;
        while (leftBorderIndex <= rightBorderIndex) {
            int position = rightBorderIndex + leftBorderIndex >>> 1;
            int currentElement = arrayToSearch[position];

            if (compareWithDefinedSortingOrder(elementToSearchFor, currentElement)) {
                leftBorderIndex = position + 1;
            } else if (compareWithDefinedSortingOrder(currentElement, elementToSearchFor)){
                rightBorderIndex = position - 1;
            } else {
                return position;
            }
        }

        return -leftBorderIndex - 1;
    }

    private boolean compareWithDefinedSortingOrder(int one, int two) {
        if (order) {
            return one > two;
        }
        return !(one > two);
    }

    public boolean getOrder() {
        return order;
    }

    public void setOrder(boolean order) {
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

}