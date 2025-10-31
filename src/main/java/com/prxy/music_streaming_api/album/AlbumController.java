package com.prxy.music_streaming_api.album;

import com.prxy.music_streaming_api.song.SongService;
import com.prxy.music_streaming_api.song.SongSummaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.prxy.music_streaming_api.common.API.ApiPath.BASE_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_PATH + "/albums")
public class AlbumController {
    private final AlbumService albumService;
    private final SongService songService;

    @GetMapping
    public ResponseEntity<Page<AlbumDTO>> getAllAlbums(Pageable pageable) {
        Page<AlbumDTO> albums = albumService.getAllAlbums(pageable);
        return ResponseEntity.ok(albums);
    }

    @PostMapping
    public ResponseEntity<AlbumDTO> createAlbum(@RequestBody CreateAlbumRequest request){
        AlbumDTO album = albumService.createAlbum(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(album);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable Long id, @RequestBody UpdateAlbumRequest request) {
        AlbumDTO album = albumService.updateAlbum(id, request);
        return ResponseEntity.ok(album);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable Long id) {
        AlbumDTO album = albumService.getAlbumById(id);
        return ResponseEntity.ok(album);
    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<Page<SongSummaryDTO>> getSongsByAlbum(@PathVariable Long id, Pageable pageable) {
        Page<SongSummaryDTO> songs = songService.getSongsByAlbumId(id, pageable);
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<Page<AlbumSummaryDTO>> getAlbumsByArtistId(@PathVariable Long artistId, Pageable pageable) {
        Page<AlbumSummaryDTO> albums = albumService.getAlbumsByArtistId(artistId, pageable);
        return ResponseEntity.ok(albums);
    }
}
