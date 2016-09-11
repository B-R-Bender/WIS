package beans;

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
 * Created by bender on 28.08.16.
 */
@ManagedBean
@RequestScoped
public class TestSortingBean implements Serializable {

    private Sorter sorter;
    private List<SortingResult> testReport;
    private Map<String, String> testLinks;
    private int randomArrayLength;

    @ManagedProperty("#{messages}")
    private ResourceBundle resourceBundle;

    @PostConstruct
    public void init() {
        sorter = new Sorter();
        testReport = new ArrayList<>();
        testLinks = new HashMap<>();
        randomArrayLength = 50000;
        testLinks.put(resourceBundle.getString("inputForm.labels.sortType.bubbleSort"), "https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D0%BF%D1%83%D0%B7%D1%8B%D1%80%D1%8C%D0%BA%D0%BE%D0%BC");
        testLinks.put(resourceBundle.getString("inputForm.labels.sortType.bubbleSort"), "https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D0%BF%D1%83%D0%B7%D1%8B%D1%80%D1%8C%D0%BA%D0%BE%D0%BC");
        testLinks.put(resourceBundle.getString("inputForm.labels.sortType.selectionSort"), "https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D0%B2%D1%8B%D0%B1%D0%BE%D1%80%D0%BE%D0%BC");
        testLinks.put(resourceBundle.getString("inputForm.labels.sortType.selectionSort"), "https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D0%B2%D1%8B%D0%B1%D0%BE%D1%80%D0%BE%D0%BC");
        testLinks.put(resourceBundle.getString("inputForm.labels.sortType.insertionSort"), "https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D0%B2%D1%81%D1%82%D0%B0%D0%B2%D0%BA%D0%B0%D0%BC%D0%B8");
        testLinks.put(resourceBundle.getString("inputForm.labels.sortType.insertionSort"), "https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D0%B2%D1%81%D1%82%D0%B0%D0%B2%D0%BA%D0%B0%D0%BC%D0%B8");
    }

    public void runTests() {
        int[] testArray = generateArray();
        int[] cloneTestArray = new int[generateArray().length];
        System.arraycopy(testArray, 0, cloneTestArray, 0, testArray.length);
        long timeBeforeSort;
        long timeAfterSort;
        long timeElapsed;

        SortingResult bubbleSort = new SortingResult();
        bubbleSort.setInputArray(testArray);
        bubbleSort.setSortingType(resourceBundle.getString("inputForm.labels.sortType.bubbleSort"));
        timeBeforeSort = System.currentTimeMillis();
        sorter.bubbleSort(testArray);
        timeAfterSort = System.currentTimeMillis();
        timeElapsed = timeAfterSort - timeBeforeSort;
        bubbleSort.setTimeElapsed(timeElapsed);
        testReport.add(bubbleSort);
        System.arraycopy(cloneTestArray, 0, testArray, 0, cloneTestArray.length);

        SortingResult bubbleImprovedSort = new SortingResult();
        bubbleImprovedSort.setInputArray(testArray);
        bubbleImprovedSort.setSortingType(resourceBundle.getString("inputForm.labels.sortType.bubbleSort") +
                " " +
                resourceBundle.getString("testingPanel.labels.improved"));
        timeBeforeSort = System.currentTimeMillis();
        sorter.bubbleSortImproved(testArray);
        timeAfterSort = System.currentTimeMillis();
        timeElapsed = timeAfterSort - timeBeforeSort;
        bubbleImprovedSort.setTimeElapsed(timeElapsed);
        testReport.add(bubbleImprovedSort);
        System.arraycopy(cloneTestArray, 0, testArray, 0, cloneTestArray.length);

        SortingResult selectionSort = new SortingResult();
        selectionSort.setInputArray(testArray);
        selectionSort.setSortingType(resourceBundle.getString("inputForm.labels.sortType.selectionSort"));
        timeBeforeSort = System.currentTimeMillis();
        sorter.selectionSort(testArray);
        timeAfterSort = System.currentTimeMillis();
        timeElapsed = timeAfterSort - timeBeforeSort;
        selectionSort.setTimeElapsed(timeElapsed);
        testReport.add(selectionSort);
        System.arraycopy(cloneTestArray, 0, testArray, 0, cloneTestArray.length);

        SortingResult selectionSortImproved = new SortingResult();
        selectionSortImproved.setInputArray(testArray);
        selectionSortImproved.setSortingType(resourceBundle.getString("inputForm.labels.sortType.selectionSort") +
                " " +
                resourceBundle.getString("testingPanel.labels.improved"));
        timeBeforeSort = System.currentTimeMillis();
        sorter.selectionSortImproved(testArray);
        timeAfterSort = System.currentTimeMillis();
        timeElapsed = timeAfterSort - timeBeforeSort;
        selectionSortImproved.setTimeElapsed(timeElapsed);
        testReport.add(selectionSortImproved);
        System.arraycopy(cloneTestArray, 0, testArray, 0, cloneTestArray.length);

        SortingResult insertionSort = new SortingResult();
        insertionSort.setInputArray(testArray);
        insertionSort.setSortingType(resourceBundle.getString("inputForm.labels.sortType.insertionSort"));
        timeBeforeSort = System.currentTimeMillis();
        sorter.insertionSort(testArray);
        timeAfterSort = System.currentTimeMillis();
        timeElapsed = timeAfterSort - timeBeforeSort;
        insertionSort.setTimeElapsed(timeElapsed);
        testReport.add(insertionSort);
        System.arraycopy(cloneTestArray, 0, testArray, 0, cloneTestArray.length);

        SortingResult insertionSortImproved = new SortingResult();
        insertionSortImproved.setInputArray(testArray);
        insertionSortImproved.setSortingType(resourceBundle.getString("inputForm.labels.sortType.insertionSort") +
                " " +
                resourceBundle.getString("testingPanel.labels.improved"));
        timeBeforeSort = System.currentTimeMillis();
        sorter.insertionSortImproved(testArray);
        timeAfterSort = System.currentTimeMillis();
        timeElapsed = timeAfterSort - timeBeforeSort;
        insertionSortImproved.setTimeElapsed(timeElapsed);
        testReport.add(insertionSortImproved);
        System.arraycopy(cloneTestArray, 0, testArray, 0, cloneTestArray.length);

        FacesMessage doneMessage = new FacesMessage();
        doneMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        doneMessage.setSummary("Tests completed");
        doneMessage.setDetail("All tests was completed successfully, checkout report.");
        FacesContext.getCurrentInstance().addMessage("?", doneMessage);
    }

    public void clearResults() {
        testReport.clear();
    }

    public String getLink(String name) {
        return testLinks.get(name);
    }

    private int[] generateArray() {
        int[] generatedArray = new int[randomArrayLength];
        Random random = new Random();

        for (int i = 0; i < generatedArray.length; i++) {
            generatedArray[i] = random.nextInt(randomArrayLength);
        }

        return generatedArray;
    }

    public Sorter getSorter() {
        return sorter;
    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public List<SortingResult> getTestReport() {
        return testReport;
    }

    public void setTestReport(List<SortingResult> testReport) {
        this.testReport = testReport;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public Map<String, String> getTestLinks() {
        return testLinks;
    }

    public void setTestLinks(Map<String, String> testLinks) {
        this.testLinks = testLinks;
    }

    public int getRandomArrayLength() {
        return randomArrayLength;
    }

    public void setRandomArrayLength(int randomArrayLength) {
        this.randomArrayLength = randomArrayLength;
    }
}
