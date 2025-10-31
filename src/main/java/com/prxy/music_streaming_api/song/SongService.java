package com.prxy.music_streaming_api.song;

import com.prxy.music_streaming_api.album.Album;
import com.prxy.music_streaming_api.album.AlbumRepo;
import com.prxy.music_streaming_api.artist.Artist;
import com.prxy.music_streaming_api.artist.ArtistRepo;
import com.prxy.music_streaming_api.common.Exception.AlbumNotFoundException;
import com.prxy.music_streaming_api.common.Exception.ArtistNotFoundException;
import com.prxy.music_streaming_api.common.Exception.SongNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepo songRepo;
    private final AlbumRepo albumRepo;
    private final ArtistRepo artistRepo;
    private final SongMapper songMapper;

    public Page<SongDTO> getAllSongs(Pageable pageable) {
        Page<Song> songs = songRepo.findAll(pageable);
        return songs.map(songMapper::toSongDTO);
    }

    public SongDTO getSongById(Long id) {
        Song song = songRepo.findById(id).orElseThrow(() -> new SongNotFoundException(id));
        return songMapper.toSongDTO(song);
    }

    @Transactional
    public SongDTO createSong(CreateSongRequest request) {
        Song song = songMapper.toSongEntity(request);
        Album album = albumRepo.findById(request.albumId())
                .orElseThrow(() -> new AlbumNotFoundException(request.albumId()));
        List<Artist> artists = artistRepo.findAllById(request.artistIds());
        if (artists.size() != request.artistIds().size()) {
            throw ArtistNotFoundException.someArtistsNotFound();
        }
        song.setAlbum(album);
        song.setArtists(artists);

        Song saved = songRepo.save(song);

        return songMapper.toSongDTO(saved);
    }

    @Transactional
    public SongDTO updateSong(Long id, UpdateSongRequest request) {
        Song song = songRepo.findById(id)
                .orElseThrow(() -> new SongNotFoundException(id));
        request.title().ifPresent(song::setTitle);
        request.albumId().ifPresent(
            albumId -> {
                Album album = albumRepo.findById(albumId)
                        .orElseThrow(() -> new AlbumNotFoundException(albumId));
                song.setAlbum(album);
            }
        );
        request.artistIds().ifPresent(
            artistIds -> {
                List<Artist> artists = artistRepo.findAllById(artistIds);
                if (artists.size() != artistIds.size()) {
                    throw ArtistNotFoundException.someArtistsNotFound();
                }
                song.setArtists(artists);
            }
        );
        request.duration().ifPresent(song::setDuration);

        return songMapper.toSongDTO(song);
    }

    @Transactional
    public void deleteSong(Long id) {
        if (!songRepo.existsById(id)) {
            throw new SongNotFoundException();
        }
        songRepo.deleteById(id);
    }

    public SongDTO searchSongByTitle(String title) {
        Optional<Song> songOpt = songRepo.findByTitleContainingIgnoreCase(title);
        Song song = songOpt.orElseThrow(SongNotFoundException::new);
        return songMapper.toSongDTO(song);
    }

    public Page<SongSummaryDTO> getSongsByAlbumId(Long albumId, Pageable pageable) {
        Page<Song> songs = songRepo.findAllByAlbumId(albumId, pageable);
        if(songs.isEmpty())        {
            throw SongNotFoundException.withAlbumId(albumId);
        }
        return songs.map(songMapper::toSongSummaryDTO);
    }

    public Page<SongSummaryDTO> getSongsByArtistId(Long artistId, Pageable pageable) {
        Page<Song> songs = songRepo.findAllByArtistsId(artistId, pageable);
        if(songs.isEmpty())        {
            throw SongNotFoundException.withArtistId(artistId);
        }
        return songs.map(songMapper::toSongSummaryDTO);
    }
}
