package br.com.thiago.dto;

import br.com.thiago.model.Books;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BooksVo extends RepresentationModel<BooksVo> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Mapping("id")
    private Long id_chave;

    private String author;

    private Date launchDate;

    private Double price;

    private String title;

    public BooksVo(){
    }

    public BooksVo(Long id_chave, String author, Date launchDate, Double price, String title) {
        this.id_chave = id_chave;
        this.author = author;
        this.launchDate = launchDate;
        this.price = price;
        this.title = title;
    }

    public BooksVo(Books books) {
        this(books.getId(),books.getAuthor(),books.getLaunchDate(),books.getPrice(), books.getTitle());
    }

    public Long getId_chave() {
        return id_chave;
    }

    public void setId_chave(Long id_chave) {
        this.id_chave = id_chave;
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
        if (!(o instanceof BooksVo vo)) return false;
        if (!super.equals(o)) return false;

        if (getId_chave() != null ? !getId_chave().equals(vo.getId_chave()) : vo.getId_chave() != null) return false;
        if (getAuthor() != null ? !getAuthor().equals(vo.getAuthor()) : vo.getAuthor() != null) return false;
        if (getLaunchDate() != null ? !getLaunchDate().equals(vo.getLaunchDate()) : vo.getLaunchDate() != null)
            return false;
        if (getPrice() != null ? !getPrice().equals(vo.getPrice()) : vo.getPrice() != null) return false;
        return getTitle() != null ? getTitle().equals(vo.getTitle()) : vo.getTitle() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getId_chave() != null ? getId_chave().hashCode() : 0);
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getLaunchDate() != null ? getLaunchDate().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        return result;
    }
}
