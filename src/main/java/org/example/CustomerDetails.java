package org.example;

public class CustomerDetails {
    private int id;
    private String name;
    private boolean archived;
    private long created;
    //    private Map<String, Boolean> allowedActions;
    private Actions allowedActions;
    private String description;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public boolean getArchived() {
        return archived;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getCreated() {
        return created;
    }

    //    public void setAllowedActions(Map<String, Boolean> allowedActions){
//        this.allowedActions= allowedActions;
//    }
//    public Map<String, Boolean> getAllowedActions(){
//        return allowedActions;
//    }
    public void setAllowedActions(Actions allowedActions) {
        this.allowedActions = allowedActions;
    }

    public Actions getAllowedActions() {
        return allowedActions;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}

