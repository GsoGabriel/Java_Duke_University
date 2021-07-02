
/**
 * Escreva a descrição da classe MovieRunnerSimilarRatings aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        FourthRatings sr = new FourthRatings("ratedmovies_short.csv", "ratings_short.csv");
        
        ArrayList<Rating> ratingArray = sr.getAverageRatings(1);
        
        System.out.printf("read data for %s raters\n", RaterDatabase.size());
        
        System.out.printf("read data for %d movies\n", MovieDatabase.size());
        
        System.out.printf("found %d movies\n", ratingArray.size());
        
        Collections.sort(ratingArray);
        for (Rating r : ratingArray){
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings sr = new FourthRatings("ratedmovies_short.csv", "ratings_short.csv");
        
        AllFilters filter = new AllFilters();
        Filter yearFilter = new YearAfterFilter(1990);
        Filter genreFilter = new GenreFilter("Drama");
        filter.addFilter(yearFilter);
        filter.addFilter(genreFilter);
        ArrayList<Rating> ratingArray = sr.getAverageRatingsByFilter(1, filter);
        
        System.out.printf("read data for %s raters\n", RaterDatabase.size());
        
        System.out.printf("read data for %d movies\n", MovieDatabase.size());
        
        System.out.printf("%d movies matched\n", ratingArray.size());
        
        Collections.sort(ratingArray);
        
        for (Rating r : ratingArray){
            System.out.printf("%.2f %d %s%n %s%n", 
                                r.getValue(),
                                MovieDatabase.getYear(r.getItem()),
                                MovieDatabase.getTitle(r.getItem()),
                                MovieDatabase.getGenres(r.getItem()) );
        }
    }
    
    public void printSimilarRatings(){
        FourthRatings sr = new FourthRatings("ratedmoviesfull.csv", "ratings.csv");
        
        for (Rating r : sr.getSimilarRatings("71",20,5)){
            System.out.println(MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre(){
        FourthRatings sr = new FourthRatings("ratedmoviesfull.csv", "ratings.csv");
        
        AllFilters filter = new AllFilters();
        Filter genreFilter = new GenreFilter("Mystery");
        filter.addFilter(genreFilter);
        
        
        for (Rating r : sr.getSimilarRatingsByFilter("964",20,5, filter)){
            System.out.println(MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector(){
        FourthRatings sr = new FourthRatings("ratedmoviesfull.csv", "ratings.csv");
        
        AllFilters filter = new AllFilters();
        Filter directorFilter = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        filter.addFilter(directorFilter);
        
        
        for (Rating r : sr.getSimilarRatingsByFilter("120",10,2, filter)){
            System.out.println(MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings sr = new FourthRatings("ratedmoviesfull.csv", "ratings.csv");
        
        AllFilters filter = new AllFilters();
        Filter genreFilter = new GenreFilter("Drama");
        Filter minutesFilter = new MinutesFilter(80,160);
        filter.addFilter(genreFilter);
        filter.addFilter(minutesFilter);
        
        
        for (Rating r : sr.getSimilarRatingsByFilter("168",10,3, filter)){
            System.out.println(MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes (){
        FourthRatings sr = new FourthRatings("ratedmoviesfull.csv", "ratings.csv");
        
        AllFilters filter = new AllFilters();
        Filter yearFilter = new YearAfterFilter(1975);
        Filter minutesFilter = new MinutesFilter(70,200);
        filter.addFilter(yearFilter);
        filter.addFilter(minutesFilter);
        
        
        for (Rating r : sr.getSimilarRatingsByFilter("314",10,5, filter)){
            System.out.println(MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    
}
