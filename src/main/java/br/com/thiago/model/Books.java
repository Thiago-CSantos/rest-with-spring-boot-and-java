package br.com.thiago.model;

import br.com.thiago.dto.BooksVo;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "books")
public class Books extends RepresentationModel<Books> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "author", nullable = false, length = 180)
    private String author;
    @Column(name = "launch_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date launchDate;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "title", nullable = false, length = 250)
    private String title;

    public Books() {
    }

    public Books(Long id, String author, Date launchDate, Double price, String title) {
        this.id = id;
        this.author = author;
        this.launchDate = launchDate;
        this.price = price;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Books books)) return false;
        if (!super.equals(o)) return false;

        if (!getId().equals(books.getId())) return false;
        if (!getAuthor().equals(books.getAuthor())) return false;
        if (!getLaunchDate().equals(books.getLaunchDate())) return false;
        if (!getPrice().equals(books.getPrice())) return false;
        return getTitle().equals(books.getTitle());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getId().hashCode();
        result = 31 * result + getAuthor().hashCode();
        result = 31 * result + getLaunchDate().hashCode();
        result = 31 * result + getPrice().hashCode();
        result = 31 * result + getTitle().hashCode();
        return result;
    }
}
