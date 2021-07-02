
/**
 * Escreva a descrição da classe MovieRunnerWithFilters aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<Rating> ratingArray = sr.getAverageRatings(35);
        
        System.out.printf("read data for %s raters\n", sr.getRaterSize());
        
        System.out.printf("read data for %d movies\n", MovieDatabase.size());
        
        System.out.printf("found %d movies\n", ratingArray.size());
        
        Collections.sort(ratingArray);
        for (Rating r : ratingArray){
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYear(){
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter filter = new YearAfterFilter(2000);
        ArrayList<Rating> ratingArray = sr.getAverageRatingsByFilter(20, filter);
        
        System.out.printf("read data for %s raters\n", sr.getRaterSize());
        
        System.out.printf("read data for %d movies\n", MovieDatabase.size());
        
        System.out.printf("found %d movies\n", ratingArray.size());
        
        Collections.sort(ratingArray);
        
        for (Rating r : ratingArray){
            System.out.printf("%.2f %d %s%n", 
                                r.getValue(),
                                MovieDatabase.getYear(r.getItem()),
                                MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter filter = new GenreFilter("Comedy");
        ArrayList<Rating> ratingArray = sr.getAverageRatingsByFilter(20, filter);
        
        System.out.printf("read data for %s raters\n", sr.getRaterSize());
        
        System.out.printf("read data for %d movies\n", MovieDatabase.size());
        
        System.out.printf("found %d movies\n", ratingArray.size());
        
        Collections.sort(ratingArray);
        
        for (Rating r : ratingArray){
            System.out.printf("%.2f %s%n  %s%n",
                                r.getValue(),
                                MovieDatabase.getTitle(r.getItem()),
                                MovieDatabase.getGenres(r.getItem()) );
        }
    }
    
    public void printAverageRatingsByMinutes(){
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter filter = new MinutesFilter(105, 135);
        ArrayList<Rating> ratingArray = sr.getAverageRatingsByFilter(5, filter);
        
        System.out.printf("read data for %s raters\n", sr.getRaterSize());
        
        System.out.printf("read data for %d movies\n", MovieDatabase.size());
        
        System.out.printf("found %d movies\n", ratingArray.size());
        
        Collections.sort(ratingArray);
        
        for (Rating r : ratingArray){
            System.out.printf("%.2f Time: %d %s%n",
                                r.getValue(),
                                MovieDatabase.getMinutes(r.getItem()),
                                MovieDatabase.getTitle(r.getItem()) );
        }
    }
    
    public void printAverageRatingsByDirectors(){
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter filter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> ratingArray = sr.getAverageRatingsByFilter(4, filter);
        
        System.out.printf("read data for %s raters\n", sr.getRaterSize());
        
        System.out.printf("read data for %d movies\n", MovieDatabase.size());
        
        System.out.printf("found %d movies\n", ratingArray.size());
        
        Collections.sort(ratingArray);
        
        for (Rating r : ratingArray){
            System.out.printf("%.2f %s%n   %s%n",
                                r.getValue(),
                                MovieDatabase.getTitle(r.getItem()),
                                MovieDatabase.getDirector(r.getItem()) );
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters filter = new AllFilters();
        Filter yearFilter = new YearAfterFilter(1990);
        Filter genreFilter = new GenreFilter("Drama");
        filter.addFilter(yearFilter);
        filter.addFilter(genreFilter);
        ArrayList<Rating> ratingArray = sr.getAverageRatingsByFilter(8, filter);
        
        System.out.printf("read data for %s raters\n", sr.getRaterSize());
        
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
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters filter = new AllFilters();
        Filter directorsFilter = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        Filter minutesFilter = new MinutesFilter(90, 180);
        filter.addFilter(directorsFilter);
        filter.addFilter(minutesFilter);
        ArrayList<Rating> ratingArray = sr.getAverageRatingsByFilter(3, filter);
        
        System.out.printf("read data for %s raters\n", sr.getRaterSize());
        
        System.out.printf("read data for %d movies\n", MovieDatabase.size());
        
        System.out.printf("%d movies matched\n", ratingArray.size());
        
        Collections.sort(ratingArray);
        
        for (Rating r : ratingArray){
            System.out.printf("%.2f Time: %d %s%n %s%n",
                                r.getValue(),
                                MovieDatabase.getMinutes(r.getItem()),
                                MovieDatabase.getTitle(r.getItem()),
                                MovieDatabase.getDirector(r.getItem()) );
        }
    }
}
