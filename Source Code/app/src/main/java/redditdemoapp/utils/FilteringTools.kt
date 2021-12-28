package redditdemoapp.utils

import redditdemoapp.models.SinglePostDataGsonModel

class FilteringTools {

    fun filterResults(list: List<SinglePostDataGsonModel>,
                      filterTitle: String?,
                      filterAuthor: String?
    ): List<SinglePostDataGsonModel> {
        val titleList = filterResultsByTitle(list, filterTitle)
        val authorList = filterResultsByAuthor(list, filterAuthor)
        return titleList.plus(authorList)
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
            return list.filter { (it.post.author.toLowerCase()).contains(filterAuthor.toLowerCase()) }
        } else {
            return list
        }
    }

}