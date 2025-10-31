package com.prxy.music_streaming_api.song;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepo extends JpaRepository<Song, Long> {
//    @Query("SELECT s FROM Song s WHERE LOWER(s.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Optional<Song> findByTitleContainingIgnoreCase(String title);

//    @Query("SELECT s FROM Song s JOIN s.album a WHERE a.id = :albumId)")
    Page<Song> findAllByAlbumId(Long albumId, Pageable pageable);

//    @Query("SELECT s FROM Song s JOIN s.artists ar WHERE ar.id = :artistId")
    Page<Song> findAllByArtistsId(Long artistId, Pageable pageable);
}
