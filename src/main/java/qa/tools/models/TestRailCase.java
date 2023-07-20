package qa.tools.models;

public class TestRailCase {

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    private String assigned;
    private String failRatio;
    private String titleHyperlinkAddress;
    private String titleHyperlinkLabel;
    private String caseHyperlinkAddress;
    private String caseHyperlinkLabel;
    private String testRailStatus;
    private String section;
    private String description;
    private String solution;
    private String solHyperlinkAddress;
    private String solHyperlinkLabel;
    private String status;

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public TestRailCase(String titleHyperlinkAddress, String titleHyperlinkLabel, String caseHyperlinkAddress,
            String caseHyperlinkLabel, String testRailStatus, String section) {
        this.assigned = "";
        this.failRatio = "";
        this.titleHyperlinkAddress = titleHyperlinkAddress;
        this.titleHyperlinkLabel = titleHyperlinkLabel;
        this.caseHyperlinkAddress = caseHyperlinkAddress;
        this.caseHyperlinkLabel = caseHyperlinkLabel;
        this.testRailStatus = testRailStatus;
        this.section = section;
        this.description = "";
        this.solution = "";
        this.solHyperlinkAddress = "";
        this.status = "-";
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public TestRailCase(String failRatio, String titleHyperlinkAddress, String titleHyperlinkLabel,
            String caseHyperlinkAddress, String caseHyperlinkLabel, String testRailStatus, String section) {
        this.assigned = "";
        this.failRatio = failRatio;
        this.titleHyperlinkAddress = titleHyperlinkAddress;
        this.titleHyperlinkLabel = titleHyperlinkLabel;
        this.caseHyperlinkAddress = caseHyperlinkAddress;
        this.caseHyperlinkLabel = caseHyperlinkLabel;
        this.testRailStatus = testRailStatus;
        this.section = section;
        this.description = "";
        this.solution = "";
        this.solHyperlinkAddress = "";
        this.status = "-";
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public TestRailCase(String assigned, String failRatio, String titleHyperlinkAddress, String titleHyperlinkLabel,
            String caseHyperlinkAddress, String caseHyperlinkLabel, String testRailStatus, String section,
            String description, String solution, String solHyperlinkAddress, String solHyperlinkLabel, String status) {
        this.assigned = assigned;
        this.failRatio = failRatio;
        this.titleHyperlinkAddress = titleHyperlinkAddress;
        this.titleHyperlinkLabel = titleHyperlinkLabel;
        this.caseHyperlinkAddress = caseHyperlinkAddress;
        this.caseHyperlinkLabel = caseHyperlinkLabel;
        this.testRailStatus = testRailStatus;
        this.section = section;
        this.description = description;
        this.solution = solution;
        this.solHyperlinkAddress = solHyperlinkAddress;
        this.solHyperlinkLabel = solHyperlinkLabel;
        this.status = status;
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public String getFailRatio() {
        return failRatio;
    }

    public void setFailRatio(String failRatio) {
        this.failRatio = failRatio;
    }

    public String getTitleHyperlinkAddress() {
        return titleHyperlinkAddress;
    }

    public void setTitleHyperlinkAddress(String titleHyperlinkAddress) {
        this.titleHyperlinkAddress = titleHyperlinkAddress;
    }

    public String getTitleHyperlinkLabel() {
        return titleHyperlinkLabel;
    }

    public void setTitleHyperlinkLabel(String titleHyperlinkLabel) {
        this.titleHyperlinkLabel = titleHyperlinkLabel;
    }

    public String getCaseHyperlinkAddress() {
        return caseHyperlinkAddress;
    }

    public void setCaseHyperlinkAddress(String caseHyperlinkAddress) {
        this.caseHyperlinkAddress = caseHyperlinkAddress;
    }

    public String getCaseHyperlinkLabel() {
        return caseHyperlinkLabel;
    }

    public void setCaseHyperlinkLabel(String caseHyperlinkLabel) {
        this.caseHyperlinkLabel = caseHyperlinkLabel;
    }

    public String getTestRailStatus() {
        return testRailStatus;
    }

    public void setTestRailStatus(String testRailStatus) {
        this.testRailStatus = testRailStatus;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getSolHyperlinkAddress() {
        return solHyperlinkAddress;
    }

    public void setSolHyperlinkAddress(String solHyperlinkAddress) {
        this.solHyperlinkAddress = solHyperlinkAddress;
    }

    public String getSolHyperlinkLabel() {
        return solHyperlinkLabel;
    }

    public void setSolHyperlinkLabel(String solHyperlinkLabel) {
        this.solHyperlinkLabel = solHyperlinkLabel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
