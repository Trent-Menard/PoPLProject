class Movie(
    week: String,
    category: String,
    weeklyRank: Int,
    showTitle: String,
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
)