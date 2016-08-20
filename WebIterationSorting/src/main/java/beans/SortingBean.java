package beans;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Homenko on 18.08.2016.
 */
@ManagedBean(name = "sortingBean")
@SessionScoped
public class SortingBean implements Serializable {

    private String userInput;
    private String resultOutput;
    private DefaultDiagramModel diagramResult;
    private LineChartModel chart = new LineChartModel();
    private LineChartModel chart1 = new LineChartModel();

    @PostConstruct
    public void init() {
        int[] ints = {0, 0, 0, 0, 0};
        fillChart(ints);
    }

    public void sort() {

        String[] spitedInput = getUserInput().split(" ");
        int[] intArray = new int[spitedInput.length];

        for (int i = 0; i < spitedInput.length; i++) {
            if (!"".equals(spitedInput[i])){
                intArray[i] = Integer.parseInt(spitedInput[i]);
            }
        }

        sort(intArray);
        fillChart(intArray);
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

    private void fillChart(int[] intArray) {
        chart = new LineChartModel();
        LineChartSeries series = new LineChartSeries();
        series.setLabel("Yours array");

        for (int i = 0; i < intArray.length; i++) {
            series.set(i + 1, intArray[i]);
        }

        chart.addSeries(series);
        chart.setTitle("Yours array in chart");
        chart.setAnimate(true);
        chart.setLegendPosition("se");
        Axis yAxis = chart.getAxis(AxisType.Y);
        yAxis.setMin(intArray[0]);
        yAxis.setMax(intArray[intArray.length - 1]);
        Axis xAxis = chart.getAxis(AxisType.X);
        xAxis.setMin(1);
        yAxis.setMax(intArray.length + 1);
    }

    private void fillDiagram(int[] intArray) {
        diagramResult = new DefaultDiagramModel();
        diagramResult.setMaxConnections(-1);

        for (int i : intArray) {
            Element element = new Element("" + i);
            element.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
            element.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
            diagramResult.addElement(element);
        }

        List<Element> elements = diagramResult.getElements();

        for (int i = 0; i < elements.size() - 1; i++) {
            elements.get(i).setX("" + i*40 + 5);
            elements.get(i).setY("" + i*20 + 5);
            Connection connection = new Connection(elements.get(i).getEndPoints().get(0), elements.get(i + 1).getEndPoints().get(1));
            diagramResult.connect(connection);
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

    public DefaultDiagramModel getDiagramResult() {
        return diagramResult;
    }

    public void setDiagramResult(DefaultDiagramModel diagramResult) {
        this.diagramResult = diagramResult;
    }

    public LineChartModel getChart() {
        return chart;
    }

    public void setChart(LineChartModel chart) {
        this.chart = chart;
    }
}
