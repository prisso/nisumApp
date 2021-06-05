package com.example.nisumapp.models

val testTerm = "in utero"

val testSong1 = Song(
    artistId = 112018,
    collectionId = 1440830905,
    trackId = 1440833845,
    artistName = "Nirvana",
    collectionName = "In Utero (20th Anniversary Super Deluxe Edition)",
    trackName = "All Apologies",
    previewUrl = "https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview128/v4/43/6d/ae/436daee2-5eed-0f8a-a8df-87daa7037244/mzaf_2667267594349403120.plus.aac.p.m4a",
    artworkUrl = "https://is3-ssl.mzstatic.com/image/thumb/Music114/v4/54/19/bc/5419bc71-1a66-fa03-dfc8-b502ce9ad18e/source/100x100bb.jpg",
    trackTimeMillis = 218744,
    country = "USA")

val testSong2 = Song(
    artistId = 112018,
    collectionId = 1440830905,
    trackId = 1440833856,
    artistName = "Nirvana",
    collectionName = "In Utero (20th Anniversary Super Deluxe Edition)",
    trackName = "The Man Who Sold the World",
    previewUrl = "https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview118/v4/26/3a/10/263a10d2-fdba-d302-5dae-473d6db84bbf/mzaf_3977923790151574376.plus.aac.p.m4a",
    artworkUrl = "https://is3-ssl.mzstatic.com/image/thumb/Music114/v4/54/19/bc/5419bc71-1a66-fa03-dfc8-b502ce9ad18e/source/100x100bb.jpg",
    trackTimeMillis = 274648,
    country = "USA"
)

val testSongs = listOf(testSong1, testSong2)
