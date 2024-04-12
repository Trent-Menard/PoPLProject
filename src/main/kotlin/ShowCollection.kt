import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.exitProcess

class ShowCollection {
    var allShows: MutableList<WeeklyShow> = mutableListOf()
    var movies: MutableList<Movie> = mutableListOf()
    var tvShows: MutableList<TVShow> = mutableListOf()

    fun add(movie: Movie) {
        this.allShows.add(movie)
        this.movies.add(movie)
    }

    fun add(tvShow: TVShow) {
        this.allShows.add(tvShow)
        this.tvShows.add(tvShow)
    }

    fun readFromFile() {
        val cd = Paths.get("").toAbsolutePath()
        val path = cd.resolve("all-weeks-global.tsv")

        if (!Files.exists(path)) {
            System.err.println("Error: Unable to read from file as it does not exist.\nEnsure it is saved in the same directory and titled 'all-weeks-global'.")
            exitProcess(-1)
        }

        var read = Files.readAllLines(path)
        .drop(1)
            .forEach { s -> s.split("\t")
                .also { t -> extractData(t) } }
    }

    private fun extractData(line: List<String>) {
        val wk = line[0]
        val cat = line[1]
        val rnk = line[2].toInt()
        val showTTL = line[3]
        val seasonTTL = line[4]
        val hrsVwd = line[5].toInt()
        val top10 = line[6].toInt()
        val lang = extractLanguage(cat)

        // If an entry's 'seasonTitle' is 'N/A' then it's a Movie, not TV Show.
        if (seasonTTL == "N/A") {
            val movie = Movie(wk, cat, rnk, showTTL, lang, hrsVwd, top10)
            add(movie)
        }
        else {
            val tvShow = TVShow(wk, cat, rnk, showTTL, seasonTTL, lang, hrsVwd, top10)
            add(tvShow)
        }
    }

    private fun extractLanguage(show: String): String {
        // Regex matches between parenthesis in 'category' (English or Non-English) and removes ().
        val pattern = "\\(([^()]+)\\)".toRegex()
        val match = pattern.find(show)
        return if (match != null)
            match.groupValues[1].replace("[\\(\\)]", "")
        else
            "Unknown"
    }

    fun getRandomSuggestion(): WeeklyShow {
        return this.allShows.random()
    }

    fun getPredictiveSuggestion(basedOn: WeeklyShow): WeeklyShow {
        val suggestion = this.allShows.filter { s -> !s.getIsPurged() && !s.showTitle.equals(basedOn.showTitle, true) }
        val filteredHigherWeeklyHoursViewed = suggestion.filter { s -> s.weeklyHoursViewed >= basedOn.weeklyHoursViewed }
        return filteredHigherWeeklyHoursViewed.takeIf { it.isNotEmpty() }?.random() ?: suggestion.random()
    }

    fun getShows(nameOrDate: String): List<WeeklyShow> {
        return this.allShows.filter { s -> s.showTitle.equals(nameOrDate, true) }
    }

    override fun toString(): String {
        return "ShowCollection{" +
                "allShows=$allShows" +
                ", movies=$movies" +
                ", tvShows=$tvShows" +
                '}'
    }
}