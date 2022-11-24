package de.igweb.igdatastores.snapshot;

import com.google.gson.Gson;
import lombok.Builder;

@Builder
@SuppressWarnings("unused")
public class DataStoreSnapshot {

    private static final Gson GSON = new Gson();

    private final long id;

    private final long timestamp;

    private final String dataClassName;

    private final String data;

    /**
     * @return The ID of the snapshot.
     */
    public long getId() {
        return id;
    }

    /**
     * @return The timestamp of the snapshot.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @return The name of the class of the data.
     */
    public String getDataClassName() {
        return dataClassName;
    }

    /**
     * @return The data of the snapshot.
     */
    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return GSON.toJson(this);
    }

    /**
     * Parses the given JSON String to a DataStoreSnapshot.
     *
     * @param json The JSON String to parse.
     * @return The parsed DataStoreSnapshot.
     */
    public static DataStoreSnapshot fromString(String json) {
        return GSON.fromJson(json, DataStoreSnapshot.class);
    }

}
