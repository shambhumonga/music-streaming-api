package com.prxy.music_streaming_api.song;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.prxy.music_streaming_api.common.API.ApiPath.BASE_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_PATH + "/songs")
public class SongController {

    private final SongService songService;

    @GetMapping
    public ResponseEntity<Page<SongDTO>> getAllSongs(Pageable pageable) {
        Page<SongDTO> songs = songService.getAllSongs(pageable);
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDTO> getSongById(@PathVariable Long id) {
        SongDTO song = songService.getSongById(id);
        return ResponseEntity.ok(song);
    }

    @PostMapping
    public ResponseEntity<SongDTO> createSong(@RequestBody CreateSongRequest request){
        SongDTO song = songService.createSong(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(song);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SongDTO> updateSong(@PathVariable Long id, @RequestBody UpdateSongRequest request){
        SongDTO song = songService.updateSong(id, request);
        return ResponseEntity.ok(song);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<SongDTO> searchSongByTitle(@RequestParam String title) {
        SongDTO song = songService.searchSongByTitle(title);
        return ResponseEntity.ok(song);
    }

    @GetMapping("/album/{albumId}")
    public ResponseEntity<Page<SongSummaryDTO>> getSongsByAlbumId(@PathVariable Long albumId, Pageable pageable) {
        Page<SongSummaryDTO> songs = songService.getSongsByAlbumId(albumId, pageable);
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<Page<SongSummaryDTO>> getSongsByArtistId(@PathVariable Long artistId, Pageable pageable) {
        Page<SongSummaryDTO> songs = songService.getSongsByArtistId(artistId, pageable);
        return ResponseEntity.ok(songs);
    }
}
