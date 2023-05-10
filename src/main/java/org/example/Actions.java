package org.example;

public class Actions {
    private boolean canModify;
    private boolean canDelete;
    public void setCanModify(boolean canModify){
        this.canModify= canModify;
    }
    public boolean getCanModify(){
        return  canModify;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }
    public boolean getCanDelete(){
        return canDelete;
    }
}
