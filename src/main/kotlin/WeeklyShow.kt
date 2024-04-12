abstract class WeeklyShow(
    var week: String,
    var category: String,
    var weeklyRank: Int,
    var showTitle: String,
    var language: String,
    var weeklyHoursViewed: Int,
    var cumulativeWeeksInTop10: Int,
) {
    private var isPurged: Boolean = false

    fun setIsPurged(isPurged: Boolean) {
        this.isPurged = isPurged
    }

    fun getIsPurged(): Boolean = this.isPurged
//    fun getIsPurged(): Boolean {return this.isPurged}

    override fun toString(): String {
        return "WeeklyShow{" +
                "week='" + week + '\'' +
                ", category='" + category + '\'' +
                ", weeklyRank=" + weeklyRank +
                ", showTitle='" + showTitle + '\'' +
                ", language='" + language + '\'' +
                ", weeklyHoursViewed=" + weeklyHoursViewed +
                ", cumulativeWeeksInTop10=" + cumulativeWeeksInTop10 +
                ", isPurged=" + isPurged +
                '}'
    }
}
