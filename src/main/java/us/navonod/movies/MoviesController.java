package us.navonod.movies;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MoviesController {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Movie {
        private String title;
        private int minutes;
        private String genre;
        private float rating;
        private int metaScore;
        private String description;
        private int votes;
        private float gross;
        @JsonProperty("Year")
        private String year;
        @JsonProperty("Credits")
        private List<Credit> credits;

        @JsonProperty("Title")
        public String getTitle() {
            return title;
        }
        @JsonProperty("title")
        public void setTitle(String title) {
            this.title = title;
        }

        @JsonProperty("Minutes")
        public int getMinutes() {
            return minutes;
        }

        @JsonProperty("minutes")
        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        @JsonProperty("Genre")
        public String getGenre() {
            return genre;
        }

        @JsonProperty("genre")
        public void setGenre(String genre) {
            this.genre = genre;
        }

        @JsonProperty("Rating")
        public float getRating() {
            return rating;
        }
        @JsonProperty("rating")
        public void setRating(float rating) {
            this.rating = rating;
        }

        @JsonProperty("Metascore")
        public int getMetaScore() {
            return metaScore;
        }

        @JsonProperty("metaScore")
        public void setMetaScore(int metaScore) {
            this.metaScore = metaScore;
        }

        @JsonProperty("Description")
        public String getDescription() {
            return description;
        }
        @JsonProperty("description")
        public void setDescription(String description) {
            this.description = description;
        }

        @JsonProperty("Votes")
        public int getVotes() {
            return votes;
        }
        @JsonProperty("votes")
        public void setVotes(int votes) {
            this.votes = votes;
        }

        @JsonProperty("Gross")
        public float getGross() {
            return gross;
        }
        @JsonProperty("gross")
        public void setGross(float gross) {
            this.gross = gross;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public List<Credit> getCredits() {
            return credits;
        }

        public void setCredits(List<Credit> credits) {
            this.credits = credits;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Credit {
        @JsonProperty("Person")
        private Person person;

        public Person getPerson() {
            return person;
        }

        public void setPerson(Person person) {
            this.person = person;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Person {
        private String role;
        @JsonProperty("FirstName")
        private String firstName;
        @JsonProperty("LastName")
        private String lastName;

        @JsonProperty("Role")
        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }


        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }


        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

    }

    @GetMapping("/movies/movie")
    public Movie movie() {
        Movie godfather = new Movie();
        godfather.setTitle("The Godfather");
        godfather.setMinutes(175);
        godfather.setGenre("Crime, Drama");
        godfather.setRating((float) 9.2);
        godfather.setMetaScore(100);
        godfather.setDescription("The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.");
        godfather.setVotes(1561591);
        godfather.setGross((float) 134.97);
        godfather.setYear("1972");

        List<Credit> credits = new ArrayList<>();


        Person person1 = new Person();
        person1.role = "Director";
        person1.firstName = "Francis Ford";
        person1.lastName = "Copolla";
        Credit credit1 = new Credit();
        credit1.setPerson(person1);

        Person person2 = new Person();
        person2.role = "Star";
        person2.firstName = "Marlon";
        person2.lastName = "Brando";
        Credit credit2 = new Credit();
        credit2.setPerson(person2);

        credits.add(credit1);
        credits.add(credit2);
        godfather.setCredits(credits);

        return godfather;
    }

    public static class Result {
        private int result;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }
    }

    @PostMapping("/movies/gross/total")
    public Result gross(@RequestBody List<Movie> movies) {
        int sum = 0;
        for (Movie movie : movies) {
            sum += (int) movie.gross;
        }
        Result result = new Result();
        result.setResult(sum);
        return result;
    }
}
