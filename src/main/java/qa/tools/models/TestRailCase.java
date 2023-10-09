/*
 * Copyright (c) 2023. Carlos.GarciaSilva@solera.com
 * All rights reserved to QapterClaims FR team
 */

package qa.tools.models;

public class TestRailCase {

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    private String assigned;
    private final String titleHyperlinkAddress;
    private final String titleHyperlinkLabel;
    private final String caseHyperlinkAddress;
    private final String caseHyperlinkLabel;
    private final String testRailStatus;
    private final String section;
    private String description;
    private String solution;
    private String solHyperlinkAddress;
    private String solHyperlinkLabel;
    private String status;

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public TestRailCase(String[] titleHyperlink, String[] caseHyperlink, String testRailStatus,
            String section) {
        this.assigned = "";
        this.titleHyperlinkAddress = titleHyperlink[0];
        this.titleHyperlinkLabel = titleHyperlink[1];
        this.caseHyperlinkAddress = caseHyperlink[0];
        this.caseHyperlinkLabel = caseHyperlink[1];
        this.testRailStatus = testRailStatus;
        this.section = section;
        this.description = "";
        this.solution = "";
        this.solHyperlinkAddress = "";
        this.status = "-";
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public TestRailCase(String assigned, String[] titleHyperlink, String[] caseHyperlink,
            String testRailStatus, String section, String description, String solution, String[] solHyperlink,
            String status) {
        this.assigned = assigned;
        this.titleHyperlinkAddress = titleHyperlink[0];
        this.titleHyperlinkLabel = titleHyperlink[1];
        this.caseHyperlinkAddress = caseHyperlink[0];
        this.caseHyperlinkLabel = caseHyperlink[1];
        this.testRailStatus = testRailStatus;
        this.section = section;
        this.description = description;
        this.solution = solution;
        this.solHyperlinkAddress = solHyperlink[0];
        this.solHyperlinkLabel = solHyperlink[1];
        this.status = status;
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }
    

    public String getTitleHyperlinkAddress() {
        return titleHyperlinkAddress;
    }


    public String getTitleHyperlinkLabel() {
        return titleHyperlinkLabel;
    }


    public String getCaseHyperlinkAddress() {
        return caseHyperlinkAddress;
    }


    public String getCaseHyperlinkLabel() {
        return caseHyperlinkLabel;
    }


    public String getTestRailStatus() {
        return testRailStatus;
    }


    public String getSection() {
        return section;
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
