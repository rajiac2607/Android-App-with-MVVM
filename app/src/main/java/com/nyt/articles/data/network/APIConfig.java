package com.nyt.articles.data.network;

/**
 * Created by Raji on 26/09/2018.
 */
public final class APIConfig {
    private APIConfig() {
    }


    public static final String BASE_URL = "http://api.nytimes.com/";
    public static final long CONNECTION_TIMEOUT = 30000;
    public static final long READ_TIMEOUT = 30000;
    public static final long WRITE_TIMEOUT = 30000;

    //API related constants

    public static final String NYT_API_KEY ="54e5496eb75443aea29abca3eda6dbf6";
    public static final String NYT_API_SECTION="all-sections";
    public static final int NYT_API_INDEX =7;

    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_FAILURE = "FAILURE";
}
