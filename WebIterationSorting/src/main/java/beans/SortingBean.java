package beans;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import services.Sorter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Homenko on 18.08.2016.
 */
@ManagedBean(name = "sortingBean")
@ViewScoped
public class SortingBean implements Serializable {

    private String userInput;

    @ManagedProperty(value = "#{sorter}")
    private Sorter sorter;

    @ManagedProperty("#{messages}")
    private ResourceBundle resourceBundle;

    @PostConstruct
    public void init() {
        System.out.println("Initializing form, application starts.");
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

        Deque<SortingResult> history = sorter.getHistory();
        history.addFirst(currentResult);
        if (history.size() > 5) {
            history.removeLast();
        }
    }

    private LineChartModel fillChart(int[] intArray) {
        LineChartModel chartOutput = new LineChartModel();
        LineChartSeries series = new LineChartSeries();

        for (int i = 0; i < intArray.length; i++) {
            series.set(i, intArray[i]);
        }

        chartOutput.addSeries(series);
        chartOutput.setTitle(resourceBundle.getString("resultPanel.labels.chart.title"));
        chartOutput.setAnimate(true);

        Axis yAxis = chartOutput.getAxis(AxisType.Y);
        if (sorter.getOrder()){
            yAxis.setMin(intArray[0]);
            yAxis.setMax(intArray[intArray.length - 1]);
            chartOutput.setLegendPosition("nw");
            series.setLabel(resourceBundle.getString("resultPanel.labels.chart.arrayAscend"));
        } else {
            yAxis.setMin(intArray[intArray.length - 1]);
            yAxis.setMax(intArray[0]);
            chartOutput.setLegendPosition("sw");
            series.setLabel(resourceBundle.getString("resultPanel.labels.chart.arrayDescend"));
        }
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
        return sorter.getHistory();
    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }
}
