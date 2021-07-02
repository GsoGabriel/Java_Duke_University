
/**
 * Escreva a descrição da classe RecommendationRunner aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.*;

public class RecommendationRunner implements Recommender {
    public ArrayList<String> getItemsToRate(){
        AllFilters filter = new AllFilters();
        Filter genreFilter = new GenreFilter("Drama");
        Filter minutesFilter = new MinutesFilter(80,100);
        Filter yearFilter = new YearAfterFilter(2014);
        Filter numRatings = new NumRatingsFilter(3);
        filter.addFilter(genreFilter);
        filter.addFilter(minutesFilter);
        filter.addFilter(yearFilter);
        filter.addFilter(numRatings);
        
        ArrayList<String> movies = MovieDatabase.filterBy(filter);
        return movies;
    }
    
    public void printRecommendationsFor(String webRaterID){
        FourthRatings fr = new FourthRatings("ratedmoviesfull.csv", "ratings.csv");
        ArrayList<Rating> ratings = fr.getSimilarRatings(webRaterID, 20, 3);
        
        System.out.println("<style> li { padding: 9px; } .film {font-weight: bold;} </style>");
        System.out.println("<h3>Some recommendations to you:</h3> <ol>");
        for (Rating r : ratings){
            System.out.printf("<li> <div class=\"film\"> %s </div>    Genre: %s; Year: %d; Minutes: %d<br></li>",
                                MovieDatabase.getTitle(r.getItem()),
                                MovieDatabase.getGenres(r.getItem()),
                                MovieDatabase.getYear(r.getItem()),
                                MovieDatabase.getMinutes(r.getItem()) );
        }
        System.out.println("</ol>");
    }
}
