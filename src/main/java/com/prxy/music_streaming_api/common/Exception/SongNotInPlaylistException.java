package com.prxy.music_streaming_api.common.Exception;

public class SongNotInPlaylistException extends RuntimeException {
    public SongNotInPlaylistException(String message) {
        super(message);
    }
    public SongNotInPlaylistException(Long songId, Long playlistId) {
        super("Song with id " + songId + " does not exist in playlist with id " + playlistId);
    }
}
