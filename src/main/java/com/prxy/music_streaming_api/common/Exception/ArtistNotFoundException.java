package com.prxy.music_streaming_api.common.Exception;

public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(String message) {
        super(message);
    }

    public ArtistNotFoundException(Long artistId) {
        super("Artist not found with id: " + artistId);
    }

    public static ArtistNotFoundException someArtistsNotFound() {
        return new ArtistNotFoundException("One or more artists not found");
    }
}
