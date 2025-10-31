package com.prxy.music_streaming_api.playlist;

import com.prxy.music_streaming_api.common.Exception.PlaylistNotFoundException;
import com.prxy.music_streaming_api.common.Exception.SongAlreadyInPlaylistException;
import com.prxy.music_streaming_api.common.Exception.SongNotFoundException;
import com.prxy.music_streaming_api.common.Exception.SongNotInPlaylistException;
import com.prxy.music_streaming_api.song.Song;
import com.prxy.music_streaming_api.song.SongRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepo playlistRepo;
    private final SongRepo songRepo;
    private final PlaylistMapper playlistMapper;

    public Page<PlaylistSummaryDTO> getAllPlaylists(Pageable pageable) {
        Page<Playlist> playlists = playlistRepo.findAll(pageable);
        return playlists.map(playlistMapper::toPlaylistSummaryDTO);
    }

    public PlaylistSummaryDTO getPlaylistById(Long id) {
        Playlist playlist = playlistRepo.findById(id)
                .orElseThrow(() -> new PlaylistNotFoundException(id));
        return playlistMapper.toPlaylistSummaryDTO(playlist);
    }

    public PlaylistSummaryDTO createPlaylist(CreatePlaylistRequest request) {
        Playlist playlist = playlistMapper.toPlaylistEntity(request);
        Playlist saved = playlistRepo.save(playlist);
        return playlistMapper.toPlaylistSummaryDTO(saved);
    }

    @Transactional
    public PlaylistSummaryDTO updatePlaylist(Long id, UpdatePlaylistRequest request) {
        Playlist playlist = playlistRepo.findById(id)
                .orElseThrow(() -> new PlaylistNotFoundException(id));
        request.name().ifPresent(playlist::setName);
        request.description().ifPresent(playlist::setDescription);
        return playlistMapper.toPlaylistSummaryDTO(playlist);
    }

    public void deletePlaylist(Long id) {
        if(!playlistRepo.existsById(id)) {
            throw new PlaylistNotFoundException(id);
        }
        playlistRepo.deleteById(id);
    }

    public PlaylistDTO addSongToPlaylist(Long playlistId, Long songId) {
        Playlist playlist = playlistRepo.findById(playlistId)
                .orElseThrow(() -> new PlaylistNotFoundException(playlistId));
        Song song = songRepo.findById(songId)
                .orElseThrow(() -> new SongNotFoundException(songId));
        List<Song> songs = playlist.getSongs();
        if(songs.contains(song)) {
            throw new SongAlreadyInPlaylistException(playlistId, songId);
        }
        songs.add(song);
        playlist.setSongs(songs);
        Playlist updatedPlaylist = playlistRepo.save(playlist);
        return playlistMapper.toPlaylistDTO(updatedPlaylist);
    }

    public void removeSongFromPlaylist(Long playlistId, Long songId) {
        Playlist playlist = playlistRepo.findById(playlistId)
                .orElseThrow(() -> new PlaylistNotFoundException(playlistId));
        Song song = songRepo.findById(songId)
                .orElseThrow(() -> new SongNotFoundException(songId));
        List<Song> songs = playlist.getSongs();
        if(!songs.contains(song)) {
            throw new SongNotInPlaylistException(playlistId, songId);
        }
        songs.remove(song);
        playlist.setSongs(songs);
        playlistRepo.save(playlist);
    }

    public PlaylistDTO getPlaylistByIdWithSongs(Long id) {
        Playlist playlist = playlistRepo.findById(id)
                .orElseThrow(() -> new PlaylistNotFoundException(id));
        return playlistMapper.toPlaylistDTO(playlist);
    }
}
