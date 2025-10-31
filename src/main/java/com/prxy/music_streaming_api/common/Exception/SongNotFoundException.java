package com.prxy.music_streaming_api.common.Exception;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException() {
        super("Song not found");
    }

    public SongNotFoundException(Long id) {
        super("Song not found with id: " + id);
    }

    public static SongNotFoundException withAlbumId(Long albumId) {
        return new SongNotFoundException("Song not found with album id: " + albumId);
    }

    public static SongNotFoundException withArtistId(Long artistId) {
        return new SongNotFoundException("Song not found with artist id: " + artistId);
    }

    public static SongNotFoundException someSongsNotFound() {
        return new SongNotFoundException("Some songs were not found");
    }

    private SongNotFoundException(String message) {
        super(message);
    }
}
