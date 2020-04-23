package br.com.pocuploadingfiles.service;

import java.io.InputStream;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.multipart.FormDataParam;

import br.com.pocuploadingfiles.controller.FileController;

/**
 * Service dos Arquivos
 *
 * @author Bruno Eduardo
 */
@Path("/file")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FileService {

    @Inject
    FileController fileController;

    /**
     * Endpoint para fazer upload e salvar um novo arquivo
     *
     * @param uploadedInputStream - Stream do Arquivo
     * @param fileName            - Nome do Arquivo
     * @return Response com o Arquivo salvo
     */
    @POST
    @Path("/{fileName}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadAndSaveFile(@FormDataParam("file") final InputStream uploadedInputStream,
            @PathParam("fileName") final String fileName) {
        return Response.ok(this.fileController.saveNewFile(uploadedInputStream, fileName)).build();
    }

    /**
     * Endpoint para fazer o download de um arquivo pelo ID
     *
     * @param id - ID do Arquivo
     * @return Response com a Stream do Arquivo para download
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFileById(@PathParam("id") final Integer id) {
        return Response.ok(this.fileController.downloadFileById(id)).build();
    }

    /**
     * Endpoint para retornar uma lista de Arquivos paginados
     *
     * @return Response com a lista de Arquivos
     */
    @GET
    public Response findAllFilesInParts(@QueryParam("firstIndex") final int firstIndex,
            @QueryParam("qtdMaxItens") final int qtdMaxItens) {
        return Response.ok(this.fileController.findAllFilesInParts(firstIndex, qtdMaxItens)).build();
    }

    /**
     * Endpoint para deletar um Arquivo pelo ID
     *
     * @param id - ID do Arquivo
     * @return Response 200 OK
     */
    @DELETE
    @Path("/{id}")
    public Response deleteArquivoById(@PathParam("id") final Integer id) {
        this.fileController.deleteArquivoById(id);
        return Response.ok().build();
    }

}