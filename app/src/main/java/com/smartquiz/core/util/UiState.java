package com.smartquiz.core.util;

public class UiState {
    private final boolean loading;
    private final String message;

    public UiState(boolean loading, String message) {
        this.loading = loading;
        this.message = message;
    }

    public boolean isLoading() {
        return loading;
    }

    public String getMessage() {
        return message;
    }
}
