INSERT INTO artists (name, genre) VALUES
('The Rock Band', 'Rock'),
('Electro Star', 'Electronic');

INSERT INTO albums (title, release_date, artist_id) VALUES
('Stadium Hits', '2023-10-15', 1),
('Digital Dreams', '2024-01-20', 2);

INSERT INTO songs (title, duration, album_id) VALUES
('Power Chord Anthem', '3:45', 1),
('Midnight Drive', '4:10', 2),
('Acoustic Break', '2:50', 1);

INSERT INTO playlists (name, description) VALUES
('Workout Jams', 'High energy tracks for the gym.'),
('Chill Vibes', 'Relaxing background music.');

INSERT INTO playlist_songs (playlist_id, song_id) VALUES
(1, 1),
(1, 2),
(2, 3);

INSERT INTO song_artists (song_id, artist_id) VALUES
(1, 1),
(2, 2),
(3, 1);