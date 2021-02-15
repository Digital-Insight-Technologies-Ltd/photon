package de.komoot.photon.query;

public class LookupRequest {
    private String placeId;
    private String language;

    public LookupRequest(String placeId, String language) {
        this.placeId = placeId;
        this.language = language;
    }

    public String getPlaceId() { return placeId; }

    public String getLanguage() { return language; }
}
