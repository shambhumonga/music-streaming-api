package com.prxy.music_streaming_api.common.Exception;

public class PlaylistNotFoundException extends RuntimeException {
    public PlaylistNotFoundException(String message) {
        super(message);
    }

    public PlaylistNotFoundException(Long id) {
        super("Playlist not found with id: " + id);
    }
}
