class TVShow(
    week: String,
    category: String,
    weeklyRank: Int,
    showTitle: String,
    var seasonTitle: String,
    language: String,
    weeklyHoursViewed: Int,
    cumulativeWeeksInTop10: Int,
) : WeeklyShow(
    week,
    category,
    weeklyRank,
    showTitle,
    language,
    weeklyHoursViewed,
    cumulativeWeeksInTop10,
) {
    override fun toString(): String {
        return "TVShow{" +
                "seasonTitle='" + seasonTitle + '\'' +
                '}'
    }
}