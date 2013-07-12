package com.treasure_data.androidsdk.apiclient;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.treasure_data.androidsdk.apiclient.DefaultApiClient.ApiError;
import com.treasure_data.androidsdk.util.Log;

public class TdTableImporter {
    private static final String TAG = TdTableImporter.class.getSimpleName();
    private final ApiClient apiClient;

    public TdTableImporter(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public void output(String database, String table, byte[] data) throws IOException, ApiError {
        // TODO: create database
        try {
            apiClient.importTable(database, table, data);
        }
        catch (FileNotFoundException e) {
            Log.i(TAG, "creating new table");
            apiClient.createTable(database, table);
            apiClient.importTable(database, table, data);
        }
    }
}
