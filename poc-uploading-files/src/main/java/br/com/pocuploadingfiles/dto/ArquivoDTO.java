package br.com.pocuploadingfiles.dto;

import java.io.Serializable;

/**
 * DTO do Arquivo
 *
 * @author Bruno Eduardo
 */
public class ArquivoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer idArquivo;

    /**
     * Nome
     */
    private String nmArquivo;

    /**
     * Base64
     */
    private String dsBase64;

    /**
     * Construtor padrão
     */
    public ArquivoDTO() {}

    /**
     * Construtor passando o ID e o nome do arquivo para serem settados
     */
    public ArquivoDTO(final Integer idArquivo, final String nmArquivo) {
        this.idArquivo = idArquivo;
        this.nmArquivo = nmArquivo;
    }

    public Integer getIdArquivo() {
        return this.idArquivo;
    }

    public void setIdArquivo(final Integer idArquivo) {
        this.idArquivo = idArquivo;
    }

    public String getNmArquivo() {
        return this.nmArquivo;
    }

    public void setNmArquivo(final String nmArquivo) {
        this.nmArquivo = nmArquivo;
    }

    public String getDsBase64() {
        return this.dsBase64;
    }

    public void setDsBase64(final String dsBase64) {
        this.dsBase64 = dsBase64;
    }

}