package redditdemoapp.utils

import redditdemoapp.models.SinglePostDataGsonModel

class FilteringTools {

    fun filterResults(list: List<SinglePostDataGsonModel>,
                      filterTitle: String?
    ): List<SinglePostDataGsonModel> {
        return filterResultsByTitle(list, filterTitle)
    }

    private fun filterResultsByTitle(list: List<SinglePostDataGsonModel>,
                                    filterTitle: String?
    ): List<SinglePostDataGsonModel> {
        if (!filterTitle.isNullOrEmpty()) {
            return list.filter { (it.post.title.toLowerCase()).contains(filterTitle.toLowerCase()) }
        } else {
            return list
        }
    }
}