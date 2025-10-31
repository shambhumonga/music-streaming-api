package com.prxy.music_streaming_api.artist;

import com.prxy.music_streaming_api.song.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Long> {
    Page<Song> findSongsById(Long id, Pageable pageable);
}
