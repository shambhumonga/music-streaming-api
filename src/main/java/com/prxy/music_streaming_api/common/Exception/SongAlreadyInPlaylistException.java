package com.prxy.music_streaming_api.common.Exception;

public class SongAlreadyInPlaylistException extends RuntimeException {
    public SongAlreadyInPlaylistException(String message) {
        super(message);
    }
    public SongAlreadyInPlaylistException(Long songId, Long playlistId) {
        super("Song with id " + songId + " is already in playlist with id " + playlistId);
    }
}
