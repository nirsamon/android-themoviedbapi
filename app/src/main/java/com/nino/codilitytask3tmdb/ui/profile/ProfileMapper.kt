package com.nino.codilitytask3tmdb.ui.profile

import com.nino.codilitytask3tmdb.data.remote.ProfileResponse
import com.nino.codilitytask3tmdb.util.Mapper
import com.nino.codilitytask3tmdb.util.toImdbProfileUrl
import com.nino.codilitytask3tmdb.util.toOriginalUrl
import javax.inject.Inject

class ProfileMapper @Inject constructor() : Mapper<ProfileResponse, Profile> {
    override fun map(input: ProfileResponse) = Profile(
        name = input.name,
        biography = input.biography,
        birthday = input.birthday.orEmpty(),
        placeOfBirth = input.placeOfBirth.orEmpty(),
        alsoKnownAs = input.alsoKnownAs,
        imdbProfileUrl = input.imdbId?.toImdbProfileUrl(),
        profilePhotoUrl = input.profilePath?.toOriginalUrl().orEmpty(),
        knownFor = input.knownForDepartment,
    )
}
