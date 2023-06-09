package pojo;

import java.util.List;
import java.util.Map;

public class SamplePojo {

    private Map<String,Object> info;
    private List<Map<String, Object>> results;

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

    public List<Map<String, Object>> getResults() {
        return results;
    }

    public void setResults(List<Map<String, Object>> results) {
        this.results = results;
    }
}
