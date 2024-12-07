package com.geotraveljournal.app.responses.core;

public class GoodResponse {
    private final boolean status;
    private final Object result;

    public GoodResponse(boolean status, Object result) {
        this.status = status;
        this.result = result;
    }

    public boolean isStatus() {
        return status;
    }

    public Object getResult() {
        return result;
    }
}
