package br.com.pocuploadingfiles.dto;

import java.io.Serializable;

/**
 * DTO do Arquivo
 *
 * @author Bruno Eduardo
 */
public class FileDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer idFile;

    private String dsBase64;

    private String nmFile;

    private Boolean stFile;

    public Integer getIdFile() {
        return idFile;
    }

    public void setIdFile(final Integer idFile) {
        this.idFile = idFile;
    }

    public String getDsBase64() {
        return dsBase64;
    }

    public void setDsBase64(final String dsBase64) {
        this.dsBase64 = dsBase64;
    }

    public String getNmFile() {
        return nmFile;
    }

    public void setNmFile(final String nmFile) {
        this.nmFile = nmFile;
    }

    public Boolean getStFile() {
        return stFile;
    }

    public void setStFile(final Boolean stFile) {
        this.stFile = stFile;
    }

}