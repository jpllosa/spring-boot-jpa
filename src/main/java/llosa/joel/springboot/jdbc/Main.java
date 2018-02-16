package llosa.joel.springboot.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

//import java.sql.ResultSet;
//import java.sql.SQLException;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowCallbackHandler;

@SpringBootApplication
public class Main implements CommandLineRunner {
	
//	@Autowired
//	JdbcTemplate jdbcTemplate;
	
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

//	public void run(String... arg0) throws Exception {
//		System.out.println("Building tables");
//		jdbcTemplate.execute("DROP TABLE movies IF EXISTS");
//		jdbcTemplate.execute("CREATE TABLE movies(id SERIAL, title VARCHAR(255), description VARCHAR(255))");
//		
//		System.out.println("\nCreating 3 movie records...");
//		jdbcTemplate.update("INSERT INTO movies(title, description) VALUES (?, ?)", "Mr. Deeds", "Comedy");
//		jdbcTemplate.update("INSERT INTO movies(title, description) VALUES (?, ?)", "Mad Max Fury Road", "Science Fiction");
//		jdbcTemplate.update("INSERT INTO movies(title, description) VALUES (?, ?)", "We Were Soldiers", "War");
//		
//		readRecords();
//		
//		System.out.println("\nUpdating Mad Max Fury Road record...");
//		jdbcTemplate.update("UPDATE movies SET description = ? WHERE title = ?", "Action/Adventure", "Mad Max Fury Road");
//		
//		readRecords();
//		
//		System.out.println("\nDeleting Mr. Deeds record...");
//		jdbcTemplate.update("DELETE FROM movies WHERE title = ?", "Mr. Deeds");
//		
//		readRecords();
//	}
//	
//	private void readRecords() {
//		System.out.println("Reading movie records...");
//		System.out.printf("%-30.30s  %-30.30s%n", "Title", "Description");
//		jdbcTemplate.query("SELECT * FROM movies", new RowCallbackHandler() {
//
//			public void processRow(ResultSet rs) throws SQLException {
//				System.out.printf("%-30.30s  %-30.30s%n", rs.getString("title"), rs.getString("description"));
//			}
//			
//		});
//	}

}
