package com.prxy.music_streaming_api.playlist;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.prxy.music_streaming_api.common.API.ApiPath.BASE_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_PATH + "/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;

    @GetMapping
    public ResponseEntity<Page<PlaylistSummaryDTO>> getAllPlaylists(Pageable pageable) {
        Page<PlaylistSummaryDTO> playlists = playlistService.getAllPlaylists(pageable);
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistSummaryDTO> getPlaylistById(@PathVariable Long id) {
        PlaylistSummaryDTO playlist = playlistService.getPlaylistById(id);
        return ResponseEntity.ok(playlist);
    }

    @PostMapping
    public ResponseEntity<PlaylistSummaryDTO> createPlaylist(@RequestBody CreatePlaylistRequest request) {
        PlaylistSummaryDTO playlist = playlistService.createPlaylist(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(playlist);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlaylistSummaryDTO> updatePlaylist(@PathVariable Long id, @RequestBody UpdatePlaylistRequest request) {
        PlaylistSummaryDTO playlist = playlistService.updatePlaylist(id, request);
        return ResponseEntity.ok(playlist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlaylist(@PathVariable Long id) {
        playlistService.deletePlaylist(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<PlaylistDTO> getPlaylistWithSongs(@PathVariable Long id) {
        PlaylistDTO playlist = playlistService.getPlaylistByIdWithSongs(id);
        return ResponseEntity.ok(playlist);
    }

    @PostMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<PlaylistDTO> addSongToPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        PlaylistDTO playlist = playlistService.addSongToPlaylist(playlistId, songId);
        return ResponseEntity.status(HttpStatus.CREATED).body(playlist);
    }

    @DeleteMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<?> removeSongFromPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        playlistService.removeSongFromPlaylist(playlistId, songId);
        return ResponseEntity.noContent().build();
    }
}
