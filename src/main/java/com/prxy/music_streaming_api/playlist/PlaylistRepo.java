package com.prxy.music_streaming_api.playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepo extends JpaRepository<Playlist, Long> {
}
