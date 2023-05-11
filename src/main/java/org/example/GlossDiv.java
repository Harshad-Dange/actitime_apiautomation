package org.example;

public class GlossDiv {

    private String title;
    private GlossList glossList;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setGlossList(GlossList glossList) {
        this.glossList = glossList;
    }

    public GlossList getGlossList() {
        return glossList;
    }
}
