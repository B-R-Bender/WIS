package beans;

import org.primefaces.model.chart.LineChartModel;

/**
 * Created by bender on 20.08.16.
 */
public class SortingResult {

    private int[] inputArray;
    private String sortingType;
    private long timeElapsed;
    private String resultOutput;
    private LineChartModel chartOutput;

    public int[] getInputArray() {
        return inputArray;
    }

    public void setInputArray(int[] inputArray) {
        this.inputArray = inputArray;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public String getSortingType() {
        return sortingType;
    }

    public void setSortingType(String sortingType) {
        this.sortingType = sortingType;
    }

    public LineChartModel getChartOutput() {
        return chartOutput;
    }

    public void setChartOutput(LineChartModel chartOutput) {
        this.chartOutput = chartOutput;
    }

    public String getResultOutput() {
        return resultOutput;
    }

    public void setResultOutput(String resultOutput) {
        this.resultOutput = resultOutput;
    }
}
