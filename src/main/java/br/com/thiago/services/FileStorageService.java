package br.com.thiago.services;

import br.com.thiago.config.FileStorageConfig;
import br.com.thiago.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        Path path = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();
        this.fileStorageLocation = path;

        try {

            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException("Diretorio não encontrado de upload", e);
        }
    }

    public String storeFile(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            //Filename..tx
            if (filename.contains("..")) {
                throw new FileStorageException("Desculpa! nome do arquivo incorreto: " + filename);
            }
            // Cria arquivo vazio no caminho expecifico
            Path targetLocation = this.fileStorageLocation.resolve(filename);
            // Substitui arquivo se ja existir
            Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return filename ;
        } catch (Exception e) {
            throw new FileStorageException("Não foi possivel aarmazenar o arquivo: " + filename + " tente novamente.", e);
        }
    }
}
