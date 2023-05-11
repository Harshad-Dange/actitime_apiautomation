package org.example;

import java.util.List;

public class GlossDef {
    private String para;
    private List<String> glossSeeAlso;

    public void setPara(String para){
        this.para= para;
    }
    public String getPara(){
        return para;
    }
    public void setGlossSeeAlso(List<String> glossSeeAlso){
        this.glossSeeAlso= glossSeeAlso;
    }
    public List<String> getGlossSeeAlso(){
        return glossSeeAlso;
    }



}
