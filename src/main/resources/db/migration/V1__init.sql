CREATE TABLE artists (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    genre VARCHAR(255)
);

CREATE TABLE albums (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    release_date VARCHAR(255),
    artist_id BIGINT NOT NULL,
    CONSTRAINT fk_artist
        FOREIGN KEY (artist_id)
        REFERENCES artists(id)
        ON DELETE SET NULL
);

CREATE TABLE songs (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    duration VARCHAR(255),
    album_id BIGINT,
    CONSTRAINT fk_album
        FOREIGN KEY (album_id)
        REFERENCES albums(id)
        ON DELETE SET NULL
);

CREATE TABLE playlists (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE song_artists (
    song_id BIGINT NOT NULL,
    artist_id BIGINT NOT NULL,
    PRIMARY KEY (song_id, artist_id),
    CONSTRAINT fk_sp_song
        FOREIGN KEY (song_id)
        REFERENCES songs(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_sp_artist
        FOREIGN KEY (artist_id)
        REFERENCES artists(id)
        ON DELETE CASCADE
);

CREATE TABLE playlist_songs (
    playlist_id BIGINT NOT NULL,
    song_id BIGINT NOT NULL,
    PRIMARY KEY (playlist_id, song_id),
    CONSTRAINT fk_ps_playlist
        FOREIGN KEY (playlist_id)
        REFERENCES playlists(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_ps_song
        FOREIGN KEY (song_id)
        REFERENCES songs(id)
        ON DELETE CASCADE
);