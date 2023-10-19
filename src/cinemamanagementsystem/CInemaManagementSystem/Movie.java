package cinemamanagementsystem.CInemaManagementSystem;

/**
 *
 * @author John Paul Uy
 */
public class Movie {
    
    private int id;
    private String title;
    private String director;

    public Movie(int id, String title, String director) {
        this.id = id;
        this.title = title;
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    
}
