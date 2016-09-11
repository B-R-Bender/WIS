package beans;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import services.LocaleBean;
import services.Sorter;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Homenko on 18.08.2016.
 */
@ManagedBean(name = "sortingBean")
@RequestScoped
public class SortingBean implements Serializable {

    private String userInput;

    @ManagedProperty(value = "#{sorter}")
    private Sorter sorter;

    @ManagedProperty("#{messages}")
    private ResourceBundle resourceBundle;

    @ManagedProperty("#{localeBean}")
    private LocaleBean locale;

    @PostConstruct
    public void init() {
        System.out.println("Initializing form, application starts.");
    }

    public void sort() {

        SortingResult currentResult = new SortingResult();
        String[] splittedInput = getUserInput().split(" ");
        int[] inputArray = new int[splittedInput.length];
        int splittedInputIndex = 0;

        try {
            for (; splittedInputIndex < splittedInput.length; splittedInputIndex++) {
                if (!"".equals(splittedInput[splittedInputIndex])) {
                    inputArray[splittedInputIndex] = Integer.parseInt(splittedInput[splittedInputIndex]);
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

        } catch (NumberFormatException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                    resourceBundle.getString("inputForm.message.error.errorMSG"),
                                                    splittedInput[splittedInputIndex] +
                                                            " " +
                                                            resourceBundle.getString("inputForm.message.error.NAN"));
            FacesContext.getCurrentInstance().addMessage("mainForm", message);
        }
    }

    private LineChartModel fillChart(int[] intArray) {
        LineChartModel chartOutput = new LineChartModel();
        LineChartSeries series = new LineChartSeries();

        for (int i = 0; i < intArray.length; i++) {
            series.set(i, intArray[i]);
        }

        chartOutput.addSeries(series);
        String chartTitle = null;
        switch (sorter.getSortingType()) {
            case "Bubble sort":
                chartTitle = resourceBundle.getString("resultPanel.labels.chart.title") +
                        " - " +
                        resourceBundle.getString("inputForm.labels.sortType.bubbleSort");
                break;
            case "Selection sort":
                chartTitle = resourceBundle.getString("resultPanel.labels.chart.title") +
                        " - " +
                        resourceBundle.getString("inputForm.labels.sortType.selectionSort");
                break;
            case "Insertion sort":
                chartTitle = resourceBundle.getString("resultPanel.labels.chart.title") +
                        " - " +
                        resourceBundle.getString("inputForm.labels.sortType.insertionSort");
                break;
        }
        chartOutput.setTitle(chartTitle);
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

    public void clear() {
        userInput = "";
        sorter.setOrder(false);
        sorter.setSortingType(null);
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

    public LocaleBean getLocale() {
        return locale;
    }

    public void setLocale(LocaleBean locale) {
        this.locale = locale;
    }
}
