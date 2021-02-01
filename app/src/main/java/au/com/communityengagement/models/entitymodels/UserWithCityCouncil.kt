package au.com.communityengagement.models.entitymodels

import androidx.room.Embedded
import androidx.room.Relation

class UserWithCityCouncil(
    @Embedded var user: User,

    @Relation(
        parentColumn = User.CITY_COUNCIL_ID,
        entityColumn = CityCouncil.ID,
        entity = CityCouncil::class
    )
    var cityCouncil: CityCouncil?
) {

}