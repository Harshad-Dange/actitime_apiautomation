package org.example;

import java.util.List;
import java.util.Map;

public class EmployeeDetails {

    private List<Map<String, Object>> data;
    private String city;
    private String companyName;

    public void setData(List<Map<String, Object>> data){
        this.data = data;
    }
    public List<Map<String, Object>> getData(){
        return data;
    }
    public void setCity(String city){
        this.city=city;
    }
    public String getCity(){
        return city;
    }
    public void setCompanyName(String companyName){
        this.companyName =companyName;
    }
    public String getCompanyName(){
        return companyName;
    }
}
