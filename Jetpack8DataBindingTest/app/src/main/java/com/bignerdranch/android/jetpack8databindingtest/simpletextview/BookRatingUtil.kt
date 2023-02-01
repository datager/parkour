package com.bignerdranch.android.jetpack8databindingtest.simpletextview

object BookRatingUtil {
    /**
     * 为书本打星，注意，该方法需要为静态方法，在布局文件中，通过<import></import>标签引入
     */
    fun getRatingString(rating: Int): String {
        when (rating) {
            0 -> return "零星"
            1 -> return "一星"
            2 -> return "二星"
            3 -> return "三星"
            4 -> return "四星"
            5 -> return "五星"
        }
        return ""
    }
}