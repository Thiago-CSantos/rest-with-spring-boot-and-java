package br.com.thiago.controller;

import br.com.thiago.dto.PersonVo;
import br.com.thiago.model.Person;
import br.com.thiago.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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

//@CrossOrigin
@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "Pessoas", description = "Endpoints para gerenciamento de pessoas") // Annotations do swagger
public class PersonController {

    @Autowired
    private PersonServices services;
    //private PersonServices services = new PersonServices();

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Encontrar uma pessoas - FindById", description = "descrição",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVo.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "No content", responseCode = "204", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            }
    )
    public ResponseEntity<PersonVo> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(services.findById(id));
    }

    @CrossOrigin(origins = {"https://thiago.com.br"} )
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Encontrar todas as pessoas - FindAll", description = "descrição", parameters = {@Parameter(name = "Teste parametro")},
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200",
                            content = {
                                    @Content(mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = PersonVo.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            }
    )
    public ResponseEntity<List<PersonVo>> people() {
        return ResponseEntity.status(HttpStatus.OK).body(services.findAll());
    }

    @CrossOrigin(origins = {"http://localhost:8080", "https://thiago.com.br"} )
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(summary = "Criar uma pessoa - Create", description = "descrição",
            tags = {"Criar pessoa"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = PersonVo.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content}),
                    @ApiResponse(description = "No content", responseCode = "204", content = {@Content})
            }
    )
    public ResponseEntity<Person> create(@RequestBody PersonVo person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(services.create(person));
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @Operation(summary = "Atulizar dados de uma pessoa - Update", description = "descrição",
            tags = {"Atulizar pessoa"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "204",
                            content =
                            @Content(schema = @Schema(implementation = PersonVo.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content}),
                    @ApiResponse(description = "No content", responseCode = "204", content = {@Content})
            }
    )
    public ResponseEntity<Person> update(@RequestBody PersonVo person) {
        return ResponseEntity.status(HttpStatus.OK).body(services.update(person));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar uma pessoa - Delete", description = "descrição",
            tags = {"Deletar pessoa"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = PersonVo.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content}),
                    @ApiResponse(description = "No content", responseCode = "204", content = {@Content})
            }
    )
    public ResponseEntity delete(@PathVariable Long id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Desabilitar uma pessoas por ID - disablePerson", description = "descrição",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVo.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "No content", responseCode = "204", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            }
    )
    public ResponseEntity<PersonVo> disablePerson(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(services.disablePerson(id));
    }

    @PatchMapping(value = "/h/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Habilitando uma pessoas por ID - disablePerson", description = "descrição",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVo.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
                    @ApiResponse(description = "No content", responseCode = "204", content = {@Content}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
            }
    )
    public ResponseEntity<PersonVo> enablePerson(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(services.enablePerson(id));
    }

}
