package com.prxy.music_streaming_api.album;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepo extends JpaRepository<Album, Long> {
    Page<Album> findByArtistId(Long artistId, Pageable pageable);
}
