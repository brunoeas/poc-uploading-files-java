package br.com.pocuploadingfiles.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import br.com.pocuploadingfiles.converter.ArquivoConverter;
import br.com.pocuploadingfiles.dto.ArquivoDTO;
import br.com.pocuploadingfiles.dto.PageDTO;
import br.com.pocuploadingfiles.orm.Arquivo;

/**
 * Controller do Arquivo
 *
 * @author Bruno Eduardo
 */
@RequestScoped
public class FileController {

    private final static Logger LOGGER = Logger.getLogger(FileController.class.getName());

    @Inject
    ArquivoConverter arquivoConverter;

    @Inject
    EntityManager entityManager;

    /**
     * Salva um novo arquivo
     *
     * @return Arquivo salvo
     */
    @Transactional
    public ArquivoDTO saveNewFile(final InputStream fileInputStream, final String fileName) {

        final Arquivo arquivoORM = new Arquivo();
        arquivoORM.setIdArquivo(null);
        arquivoORM.setNmArquivo(fileName);

        final byte[] fileReaded = this.readInputStream(fileInputStream);
        arquivoORM.setBytesArquivo(fileReaded);

        this.persistArquivo(arquivoORM);

        final ArquivoDTO arquivoDTO = this.arquivoConverter.ormToDto(arquivoORM);
        arquivoDTO.setDsBase64(Base64.getEncoder().encodeToString(fileReaded));
        return arquivoDTO;
    }

    /**
     * Busca na base de dados e descomprime uma parte de todos os arquivos
     *
     * @param firstIndex  - De qual posição a busca deve começar
     * @param qtdMaxItens - Limite de itens que vão retornar
     * @return Lista de arquivos descomprimidos
     */
    @Transactional
    public PageDTO<ArquivoDTO> findAllFilesInParts(final int firstIndex, final int qtdMaxItens) {

        final String query = "SELECT NEW br.com.pocuploadingfiles.dto.ArquivoDTO(a.idArquivo, a.nmArquivo) "
                + "FROM Arquivo a ORDER BY a.dhCriacao DESC";

        final List<ArquivoDTO> dtoList = this.entityManager.createQuery(query, ArquivoDTO.class)
                .setMaxResults(qtdMaxItens)
                .setFirstResult(firstIndex)
                .getResultList();

        final long countTotal = Arquivo.count();

        final PageDTO<ArquivoDTO> pageDTO = new PageDTO<>();
        pageDTO.setCountTotal(countTotal);
        pageDTO.setListData(dtoList);

        return pageDTO;
    }

    /**
     * Deleta um Arquivo pelo ID
     *
     * @param id - ID do Arquivo
     */
    @Transactional
    public void deleteArquivoById(final Integer id) {
        Arquivo.delete("idArquivo", id);
    }

    /**
     * Persiste o arquivo na base de dados
     *
     * @param arquivoORM - ORM do Arquivo
     */
    private void persistArquivo(final Arquivo arquivoORM) {
        arquivoORM.setDhCriacao(ZonedDateTime.now());
        arquivoORM.persist();
        arquivoORM.flush();
    }

    /**
     * Lê o InputStream do arquivo e retorna os seus bytes
     *
     * @param inputStream - InputStream que vai ser lido
     * @return Array de bytes do arquivo
     */
    private byte[] readInputStream(final InputStream inputStream) {
        try {
            final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            final byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            buffer.flush();
            return buffer.toByteArray();
        } catch (final IOException e) {
            LOGGER.log(Logger.Level.ERROR, "Erro ao ler InputStream do arquivo", e);
            throw new RuntimeException("Erro ao ler InputStream do arquivo", e);
        }
    }

}