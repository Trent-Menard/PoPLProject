
fun main() {

    val sc = ShowCollection()
    sc.readFromFile()

    // Print 5 first entries.
    println("First 5 entries:")
    for (idx in 0..4)
        println(sc.allShows[idx])
    println()

    println("First 5 Movies:")
    for (idx in 0..4)
        println(sc.movies[idx])
    println()

    println("First 5 TV Shows:")
    for (idx in 0..4)
        println(sc.tvShows[idx])
    println()

    println("Random Suggestion:")
    println(sc.getRandomSuggestion())
    println()

    println("Predictive Suggestion:")
    println(sc.getPredictiveSuggestion(sc.getRandomSuggestion()))
}