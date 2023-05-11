package org.example;

public class Glossary {

    private  String title;
    private GlossDiv glossDiv;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setGlossDiv(GlossDiv glossDiv) {
        this.glossDiv = glossDiv;
    }

    public GlossDiv getGlossDiv() {
        return glossDiv;
    }
}
