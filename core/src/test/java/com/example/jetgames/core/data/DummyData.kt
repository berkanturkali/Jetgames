package com.example.jetgames.core.data

import com.example.jetgames.core.domain.model.games.ParentPlatform
import com.example.jetgames.core.remote.model.games.*

internal object DummyData {
    //esrb rating
    val esrbRating = EsrbRatingDto(
        1, "Mature", "Mature"
    )

    //genre
    val genre = GenreDto(
        id = 4,
        name = "Action",
        slug = "action",
        games_count = 140141,
        image_background = ""
    )

    //platform
    val platform = PlatformDto(
        id = 2,
        name = "Playstation",
        slug = "playstation"
    )

    //Rating
    val rating = RatingDto(
        id = 4,
        title = "recommended",
        count = 1915,
        percent = 60.43
    )

    //screenshot
    val screenshot = ShortScreenshotDto(
        id = 1,
        image = ""
    )
    //tag
    val tag = TagDto(
        id = 31,
        name = "SinglePlayer",
        slug = "singleplayer",
        language = "eng",
        games_count = 142334,
        image_background = ""
    )

    //storeX
    val storeX = StoreXDto(
        id = 2,
        name = "Xbox-Store",
        slug = "xbox-store",
        domain = "microsoft.com",
        games_count = 12321,
        image_background = ""
        )
    //store
    val store = StoreDto(
        id = 12321,
        store = storeX
    )

    //platformXX
    val platformXX = PlatformXXDto(
        games_count = 5159,
        id=1,
        name="Xbox One",
        slug = "xbox-one",
        image=null,
        year_end = null,
        year_start = null,
        image_background = ""
    )

    //platformX
    val platformX = PlatformXDto(
        platform = platformXX,
        released_at = "2013-03-05",
        requirements_en = null,
        requirements_ru = null
    )
    //parent platform
    val parentPlatform  = ParentPlatformDto(
        platform = platform
    )
}