package br.com.pocuploadingfiles.service;

import br.com.pocuploadingfiles.dto.FileDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service dos Arquivos
 *
 * @author Bruno Eduardo
 */
@Path("/file")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FileService {

    private final String path = Paths.get("../").toAbsolutePath().normalize().toString();

    @POST
    public Response saveFile(final FileDTO fileDTO) {

        this.saveNewFile(fileDTO);

        return Response.ok().build();
    }

    private void saveNewFile(final FileDTO fileDTO) {

        final List<FileDTO> fileDTOList = this.getFileContent();

        this.setNewId(fileDTO, fileDTOList);
        fileDTOList.add(fileDTO);

        this.setFileContent(fileDTOList);
    }

    private void setNewId(final FileDTO fileDTO, final List<FileDTO> fileDTOList) {
        final List<Integer> ids = fileDTOList.stream().map(FileDTO::getIdFile).collect(Collectors.toList());

        if (ids.size() > 0) {
            final Integer maiorId = Collections.max(ids);
            fileDTO.setIdFile(maiorId + 1);
        } else {
            fileDTO.setIdFile(1);
        }
    }

    private void setFileContent(final List<FileDTO> fileDTOList) {
        try (final FileWriter fileWriter = new FileWriter(path + "/content.json")) {
            fileWriter.write(this.getGson().toJson(fileDTOList));
            fileWriter.flush();
        } catch (final IOException e) {
            System.out.println("Erro ao salvar JSON: " + e.toString());
            throw new RuntimeException("Erro ao salvar JSON");
        }
    }

    private List<FileDTO> getFileContent() {

        try (final FileReader fileReader = new FileReader(path + "/content.json")) {
            return new ArrayList<>(Arrays.asList(this.getGson().fromJson(fileReader, FileDTO[].class)));
        } catch (final FileNotFoundException e) {
            this.setFileContent(new ArrayList<>());
            return new ArrayList<>();
        } catch (final IOException e) {
            System.out.println("Erro ao ler JSON: " + e.toString());
            throw new RuntimeException("Erro ao ler JSON");
        }
    }

    private Gson getGson() {
        return new GsonBuilder().create();
    }

}