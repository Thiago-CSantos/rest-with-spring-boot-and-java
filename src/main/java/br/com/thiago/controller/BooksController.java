package br.com.thiago.controller;

import br.com.thiago.dto.BooksVo;
import br.com.thiago.model.Books;
import br.com.thiago.services.BooksServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books/v1")
@Tag(name = "Livros", description = "Endpoints para gerenciamento de books") // Annotations do swagger
public class BooksController {

    @Autowired
    private BooksServices services;

    @GetMapping("{id}")
    @Operation(
            summary = "Metodo para buscar por id",
            tags = "Buscar por id",
            description = "descrição",
            responses = {
                    @ApiResponse(
                            description = "Sucesso", responseCode = "200",
                            content = {@Content(schema = @Schema(implementation = BooksVo.class))}
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "No content", responseCode = "204", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            }
    )
    public ResponseEntity<BooksVo> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(services.findById(id));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(
            summary = "Metodo para buscar livros",
            tags = "Buscar todos os livros",
            description = "descrição",
            responses = {
                    @ApiResponse(
                            description = "Sucesso", responseCode = "200",
                            content = {@Content(schema = @Schema(implementation = BooksVo.class))}
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "No content", responseCode = "204", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            }
    )
    public ResponseEntity<List<BooksVo>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(services.findAll());
    }

    @PutMapping(value = "update/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(
            summary = "Metodo para buscar livros",
            tags = "Buscar todos os livros",
            description = "descrição",
            responses = {
                    @ApiResponse(
                            description = "Create", responseCode = "201",
                            content = {@Content(schema = @Schema(implementation = BooksVo.class))}
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "No content", responseCode = "204", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            }
    )
    public ResponseEntity<Books> update(@PathVariable Long id, @RequestBody BooksVo booksVo) {
        return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED).body(services.update(id, booksVo));
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(
            summary = "Metodo para buscar livros",
            tags = "Buscar todos os livros",
            description = "descrição",
            responses = {
                    @ApiResponse(
                            description = "Create", responseCode = "201",
                            content = {@Content(schema = @Schema(implementation = BooksVo.class))}
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "No content", responseCode = "204", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            }
    )
    public ResponseEntity<Books> create(@RequestBody BooksVo booksVo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(services.create(booksVo));
    }

}
