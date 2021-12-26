package breakingbbaddemoapp.utils

import breakingbbaddemoapp.constants.SeriesSpecificConstants
import breakingbbaddemoapp.models.PostGsonModel
import breakingbbaddemoapp.models.SimplifiedCharacterObject

class FilteringTools {

    fun filterResults(list: List<SimplifiedCharacterObject>,
                      filterNamePhrase: String?,
                      filterSeason: Int?
    ): List<SimplifiedCharacterObject> {
        val newList = filterResultsByName(list, filterNamePhrase)
        return filterResultsBySeason(newList, filterSeason)
    }

    private fun filterResultsByName(list: List<SimplifiedCharacterObject>,
                                    filterNamePhrase: String?
    ): List<SimplifiedCharacterObject> {
        if (!filterNamePhrase.isNullOrEmpty()) {
            return list.filter { (it.name.toLowerCase()).contains(filterNamePhrase.toLowerCase()) }
        } else {
            return list
        }
    }

    private fun filterResultsBySeason(list: List<SimplifiedCharacterObject>,
                                      filterSeason: Int?
    ): List<SimplifiedCharacterObject> {
        if (filterSeason != null && filterSeason != 0) {
            if (filterSeason > SeriesSpecificConstants.NUMBER_OF_LAST_BREAKING_BAD_SEASON_EVER_CREATED) {
                val betterCallSaulSeasonNumber = filterSeason - SeriesSpecificConstants.NUMBER_OF_LAST_BREAKING_BAD_SEASON_EVER_CREATED
                return list.filter { it.betterCallSaulSeasonAppearance.contains(betterCallSaulSeasonNumber) }
            } else {
                return list.filter { it.breakingBadSeasonAppearance.contains(filterSeason) }
            }
        } else {
            return list
        }
    }

    fun filterResults2(list2: List<PostGsonModel>,
                       filterTitle: String?,
                       filterAuthor: String?
    ): List<PostGsonModel> {
        val newList2 = filterResultsByTitle(list2, filterTitle)
        return filterResultsByAuthor(newList2, filterAuthor)
    }

    private fun filterResultsByTitle(list2: List<PostGsonModel>,
                                    filterTitle: String?
    ): List<PostGsonModel> {
        if (!filterTitle.isNullOrEmpty()) {
            return list2.filter { (it.name.toLowerCase()).contains(filterTitle.toLowerCase()) }
        } else {
            return list2
        }
    }

    private fun filterResultsByAuthor(list2: List<PostGsonModel>,
                                     filterAuthor: String?
    ): List<PostGsonModel> {
        if (!filterAuthor.isNullOrEmpty()) {
            return list2.filter { (it.name.toLowerCase()).contains(filterAuthor.toLowerCase()) }
        } else {
            return list2
        }
    }

}