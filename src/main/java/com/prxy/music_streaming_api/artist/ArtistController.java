package com.prxy.music_streaming_api.artist;

import com.prxy.music_streaming_api.album.AlbumService;
import com.prxy.music_streaming_api.album.AlbumSummaryDTO;
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
@RequestMapping(BASE_PATH + "/artists")
public class ArtistController {
    private final ArtistService artistService;
    private final SongService songService;
    private final AlbumService albumService;

    @GetMapping
    public ResponseEntity<Page<ArtistDTO>> getAllArtists(Pageable pageable) {
        Page<ArtistDTO> artists = artistService.getAllArtists(pageable);
        return ResponseEntity.ok(artists);
    }

    @PostMapping
    public ResponseEntity<ArtistDTO> createArtist(@RequestBody CreateArtistRequest request){
        ArtistDTO artist = artistService.createArtist(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(artist);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ArtistDTO> updateArtist(@PathVariable Long id, @RequestBody UpdateArtistRequest request) {
        ArtistDTO artist = artistService.updateArtist(id, request);
        return ResponseEntity.ok(artist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable Long id) {
        ArtistDTO artist = artistService.getArtistById(id);
        return ResponseEntity.ok(artist);
    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<Page<SongSummaryDTO>> getSongsByArtist(@PathVariable Long id, Pageable pageable) {
        Page<SongSummaryDTO> songs = songService.getSongsByArtistId(id, pageable);
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/{id}/albums")
    public ResponseEntity<Page<AlbumSummaryDTO>> getAlbumsByArtist(@PathVariable Long id, Pageable pageable) {
        Page<AlbumSummaryDTO> albums = albumService.getAlbumsByArtistId(id, pageable);
        return ResponseEntity.ok(albums);
    }
}