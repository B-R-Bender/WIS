package beans;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import services.Sorter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Homenko on 18.08.2016.
 */
@ManagedBean(name = "sortingBean")
@SessionScoped
public class SortingBean implements Serializable {

    private String userInput;
    private Deque<SortingResult> history;

    @ManagedProperty(value = "#{sorter}")
    private Sorter sorter;

    @PostConstruct
    public void init() {
        history = new LinkedList<>();
    }

    public void sort() {

        SortingResult currentResult = new SortingResult();
        String[] spitedInput = getUserInput().split(" ");
        int[] inputArray = new int[spitedInput.length];

        for (int i = 0; i < spitedInput.length; i++) {
            if (!"".equals(spitedInput[i])){
                inputArray[i] = Integer.parseInt(spitedInput[i]);
            }
        }

        int[] sortedArray = sorter.sort(inputArray);
        currentResult.setResultOutput(Arrays.toString(sortedArray));
        currentResult.setChartOutput(fillChart(sortedArray));

        history.addFirst(currentResult);
        if (history.size() > 5) {
            history.removeLast();
        }
    }

    private LineChartModel fillChart(int[] intArray) {
        LineChartModel chartOutput = new LineChartModel();
        LineChartSeries series = new LineChartSeries();
        series.setLabel("Yours array");

        for (int i = 0; i < intArray.length; i++) {
            series.set(i, intArray[i]);
        }

        chartOutput.addSeries(series);
        chartOutput.setTitle("Yours array in chartOutput");
        chartOutput.setAnimate(true);
        chartOutput.setLegendPosition("se");
        Axis yAxis = chartOutput.getAxis(AxisType.Y);
        yAxis.setMin(intArray[0]);
        yAxis.setMax(intArray[intArray.length - 1]);
        Axis xAxis = chartOutput.getAxis(AxisType.X);
        xAxis.setMin(0);
        return chartOutput;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public Deque<SortingResult> getHistory() {
        return history;
    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }
}
