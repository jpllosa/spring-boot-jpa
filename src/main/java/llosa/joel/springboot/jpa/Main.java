package llosa.joel.springboot.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {
	
	@Autowired
	private MovieRepository movieRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	
	public void run(String... args) throws Exception {
		System.out.println("\nCreating 3 movie records...");
		movieRepo.save(new Movie("Mr. Deeds", "Comedy"));
		movieRepo.save(new Movie("Mad Max Fury Road", "Science Fiction"));
		movieRepo.save(new Movie("We Were Soldiers", "War"));
		
		readRecords();
		
		System.out.println("\nUpdating Mad Max Fury Road record...");
		List<Movie> movies = movieRepo.findByTitle("Mad Max Fury Road");
		Movie madMax = movies.get(0);
		madMax.setDescription("Action/Adventure");
		movieRepo.save(madMax);
		
		readRecords();
		
		System.out.println("\nDeleting Mr. Deeds record...");
		movies = movieRepo.findByTitle("Mr. Deeds");
		Movie mrDeeds = movies.get(0);
		movieRepo.delete(mrDeeds);
		
		readRecords();
	}
	
	private void readRecords() {
		System.out.println("Reading movie records...");
		System.out.printf("%-30.30s  %-30.30s%n", "Title", "Description");
		for (Movie movie : movieRepo.findAll()) {
			System.out.printf("%-30.30s  %-30.30s%n", movie.getTitle(), movie.getDescription());
		}		
	}
}
