package br.com.thiago.services;

import br.com.thiago.controller.BooksController;
import br.com.thiago.dto.BooksVo;
import br.com.thiago.dto.PersonVo;
import br.com.thiago.model.Books;
import br.com.thiago.model.Person;
import br.com.thiago.repository.BooksRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BooksServices {

    @Autowired
    private BooksRepository repository;

    private Logger logger = Logger.getLogger(BooksServices.class.getName());

    public List<BooksVo> findAll() {
        logger.info("Chmando o metodo findAll");
        List<BooksVo> booksVoList = repository.findAll().stream().map(BooksVo::new).toList();

        for (BooksVo b : booksVoList) {
            b.add(linkTo(methodOn(BooksController.class).findById(b.getId_chave())).withSelfRel());
        }

        return booksVoList;
    }

    public BooksVo findById(Long id) {
        Books entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));

        BooksVo booksVo = new BooksVo(entity);

        // Converte o BooksVo para Books
        BeanUtils.copyProperties(booksVo, entity);

        // Esse "findById" é o nome do metodo no controller
        booksVo.add(linkTo(methodOn(BooksController.class).findById(id)).withSelfRel());

        return booksVo;
    }

    public Books create(BooksVo booksVo) {
        //mostra no console
        logger.info("Cria uma Books");

        Books books = new Books();
        // Converte o personVo para person
        BeanUtils.copyProperties(booksVo, books);

        return repository.save(books);
    }

    public Books update(Long id, BooksVo booksVo){

        //mostra no console
        logger.info("Atualiza uma Books");

        Books books = repository.findById(id).orElseThrow(()->new RuntimeException("não encontrado"));

        books.setAuthor(booksVo.getAuthor());
        books.setLaunchDate(booksVo.getLaunchDate());
        books.setPrice(booksVo.getPrice());
        books.setTitle(booksVo.getTitle());

        return repository.save(books);

    }
}
