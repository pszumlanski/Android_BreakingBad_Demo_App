package redditdemoapp.utils

import redditdemoapp.models.SinglePostDataGsonModel

class FilteringTools {

    fun filterResults(list: List<SinglePostDataGsonModel>,
                      filterTitle: String?,
                      filterAuthor: String?
    ): List<SinglePostDataGsonModel> {
        val newList = filterResultsByTitle(list, filterTitle)
        return filterResultsByAuthor(newList, filterAuthor)
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

    private fun filterResultsByAuthor(list: List<SinglePostDataGsonModel>,
                                     filterAuthor: String?
    ): List<SinglePostDataGsonModel> {
        if (!filterAuthor.isNullOrEmpty()) {
            return list.filter { (it.post.title.toLowerCase()).contains(filterAuthor.toLowerCase()) }
        } else {
            return list
        }
    }

}