package pojo;

import java.util.Map;

public class CreateWorkFlowResponsePojo {

    private long id;
    private String name;
    private String type;
    private Map<String, Boolean> allowedActions;

//    public CreateWorkFlowResponsePojo(){
//        System.out.println("This is non param constructor");
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Boolean> getAllowedActions() {
        return allowedActions;
    }

    public void setAllowedActions(Map<String, Boolean> allowedActions) {
        this.allowedActions = allowedActions;
    }
}
