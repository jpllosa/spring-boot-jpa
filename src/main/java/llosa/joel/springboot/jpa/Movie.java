package llosa.joel.springboot.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    
    protected Movie() {}
    
    public Movie(String title, String description) {
    	this.title = title;
    	this.description = description;
    }

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Movie setDescription(String description) {
		this.description = description;
		return this;
	}
	
	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", description=" + description + "]";
	}
}
