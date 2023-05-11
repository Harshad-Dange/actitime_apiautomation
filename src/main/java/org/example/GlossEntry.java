package org.example;

public class GlossEntry {
        private int id;
        private String sortAs;
        private String glossTerm;
        private String acronym;
        private String abbrev;
        private GlossDef glossDef;
        private String glossSee;
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setSortAs(String sortAs) {
        this.sortAs = sortAs;
    }
    public String getSortAs() {
        return sortAs;
    }

    public void setGlossTerm(String glossTerm) {
        this.glossTerm = glossTerm;
    }
    public String getGlossTerm(){
        return glossTerm;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public void setGlossDef(GlossDef glossDef) {
        this.glossDef = glossDef;
    }

    public GlossDef getGlossDef() {
        return glossDef;
    }

    public void setGlossSee(String glossSee) {
        this.glossSee = glossSee;
    }

    public String getGlossSee() {
        return glossSee;
    }
}
