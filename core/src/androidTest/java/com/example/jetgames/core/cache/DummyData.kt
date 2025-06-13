package com.example.jetgames.core.cache

import com.example.jetgames.core.cache.model.FavoriteEntity
import com.example.jetgames.core.cache.model.GenreEntity
import com.example.jetgames.core.cache.model.HomeFilterPreferencesEntity
import com.example.jetgames.core.cache.model.PlatformEntity
import com.example.jetgames.core.domain.model.favorites.Favorite
import com.example.jetgames.core.domain.model.platforms.Platform
import com.example.jetgames.core.domain.model.preferences.HomePreferences
import com.example.jetgames.core.domain.model.preferences.MetacriticPreference
import com.example.jetgames.core.remote.model.details.GameDetailsDto
import com.example.jetgames.core.remote.model.details.MetacriticPlatformDto
import com.example.jetgames.core.remote.model.games.*

internal object DummyData {
    // esrb rating
    val esrbRating = EsrbRatingDto(
        1, "Mature", "Mature"
    )

    // genre
    val genre = GenreDto(
        id = 4,
        name = "Action",
        slug = "action",
        games_count = 140141,
        image_background = ""
    )

    // platform
    val platform = PlatformDto(
        id = 2,
        name = "Playstation",
        slug = "playstation"
    )

    // Rating
    val rating = RatingDto(
        id = 4,
        title = "recommended",
        count = 1915,
        percent = 60.43
    )

    // screenshot
    val screenshot = ShortScreenshotDto(
        id = 1,
        image = ""
    )

    // tag
    val tag = TagDto(
        id = 31,
        name = "SinglePlayer",
        slug = "singleplayer",
        language = "eng",
        games_count = 142334,
        image_background = ""
    )

    // storeX
    val storeX = StoreXDto(
        id = 2,
        name = "Xbox-Store",
        slug = "xbox-store",
        domain = "microsoft.com",
        games_count = 12321,
        image_background = ""
    )

    // store
    val store = StoreDto(
        id = 12321,
        store = storeX
    )

    // platformXX
    val platformXX = PlatformXXDto(
        games_count = 5159,
        id = 1,
        name = "Xbox One",
        slug = "xbox-one",
        image = null,
        year_end = null,
        year_start = null,
        image_background = ""
    )

    // platformX
    val platformX = PlatformXDto(
        platform = platformXX,
        released_at = "2013-03-05",
        requirements_en = null,
        requirements_ru = null
    )

    // parent platform
    val parentPlatform = ParentPlatformDto(
        platform = platform
    )

    // game
    val game = GameDto(
        esrb_rating = esrbRating,
        genres = listOf(genre),
        id = 3
    )

    // metacritic platform
    val metacriticPlaform = MetacriticPlatformDto(
        metascore = 85,
        platform = platform,
        url = ""
    )

    val gameDetailsDto = GameDetailsDto(
        description = "The third game in a series, it holds nothing back from the player. Open world adventures of the renowned monster slayer Geralt of Rivia are now even on a larger scale.",
        esrb_rating = esrbRating,
        genres = listOf(genre),
        id = 3328,
        metacritic = 92,
        metacritic_platforms = listOf(metacriticPlaform),
        rating = 4.5,
        rating_top = 5,
        ratings = listOf(rating),
        ratings_count = 12312321,
        released = "12 Dec 2021",
        stores = listOf(store),
        tags = listOf(tag),
        website = "www.dummydomain.com"
    )

    // platform dto
    val platformDto = com.example.jetgames.core.remote.model.platforms.PlatformDto(
        id = 5,
        name = "Playstation 5"
    )

    // platform entity
    val platformEntity = PlatformEntity(
        id = 5,
        name = "Playstation 5"
    )

    // filter preferences entity
    val filterPreferencesEntity = HomeFilterPreferencesEntity(
        platforms = listOf(PlatformEntity(id = 5, name = "Playstation 5")),
        genres = listOf("Strategy", "Sports"),
        minMetacri = 0,
        maxMetacri = 100,
        order = "metacritic",
        direction = '-'
    )

    // filter preferences
    val filterPreferences = HomePreferences.HomeFilterPreferences(
        platforms = listOf(Platform(id = 5, name = "Playstation 5")),
        genres = emptyList(),
        metacriticPreference = MetacriticPreference(min = 55, max = 65)
    )

    // genre entity
    val genreEntity = GenreEntity(
        id = 1,
        name = "Action"
    )

    // genre dto
    val genreDto = com.example.jetgames.core.remote.model.genres.GenreDto(
        id = 1,
        image_background = "",
        name = "Action",
        slug = "action"
    )

    // favorite entity
    val favoriteEntity = FavoriteEntity(
        id = 1,
        image = null,
        name = "Grand Theft Auto V",
        rating = 4.95,
        releaseDate = "September 17 2013",
        metacritic = 97,
        icon = null
    )
    val favorite = Favorite(
        id = 1,
        image = null,
        name = "Grand Theft Auto V",
        rating = 4.95,
        releaseDate = "September 17 2013",
        metacri = 97,
        icon = null
    )
}
