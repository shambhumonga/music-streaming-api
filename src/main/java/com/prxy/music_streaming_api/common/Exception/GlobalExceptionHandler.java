package com.prxy.music_streaming_api.common.Exception;

import com.prxy.music_streaming_api.common.API.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(SongNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleSongNotFoundException(SongNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(PlaylistNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlePlaylistNotFoundException(PlaylistNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(AlbumNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleAlbumNotFoundException(AlbumNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ArtistNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleArtistNotFoundException(ArtistNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(SongAlreadyInPlaylistException.class)
    public ResponseEntity<ApiErrorResponse> handleSongAlreadyInPlaylistException(SongAlreadyInPlaylistException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(SongNotInPlaylistException.class)
    public ResponseEntity<ApiErrorResponse> handleSongNotInPlaylistException(SongNotFoundException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleRuntimeException(RuntimeException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occured : " + ex.getMessage());
    }

    private ResponseEntity<ApiErrorResponse> buildResponse(HttpStatus httpStatus, String message) {
        ApiErrorResponse error = new ApiErrorResponse(
                httpStatus.value(),
                message,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, httpStatus);
    }
}
