package beans;

import org.primefaces.model.chart.LineChartModel;

/**
 * Created by bender on 20.08.16.
 */
public class SortingResult {

    private LineChartModel chartOutput;
    private String resultOutput;

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
