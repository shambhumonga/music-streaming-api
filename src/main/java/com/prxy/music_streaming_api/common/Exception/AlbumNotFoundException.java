package com.prxy.music_streaming_api.common.Exception;

public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException(String message) {
        super(message);
    }

    public AlbumNotFoundException(Long id) {
        super("Album not found with id: " + id);
    }

    public static AlbumNotFoundException withArtistId(Long artistId) {
        return new AlbumNotFoundException("Album not found with artist id "+ artistId);
    }
}
