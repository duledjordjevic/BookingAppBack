package com.booking.project.exception;

public class ResourceConflictException extends RuntimeException{
    private static final long serialVersionUID = 1791564636123821405L;

    private int resourceId;

    public ResourceConflictException(int resourceId, String message) {
        super(message);
        this.setResourceId(resourceId);
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
