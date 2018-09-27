package com.nyt.articles.data.network;

/**
 * Created by Raji on 26/09/2018.
 */
public class NTError {
    /**
     * Generic class for holding network/api errors
     * errorCode can also be added here for performing specific action on type of error
     */

    private String errorMessage;

    public NTError(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
