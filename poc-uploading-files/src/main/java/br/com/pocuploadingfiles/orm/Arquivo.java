package br.com.pocuploadingfiles.orm;

import javax.persistence.*;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

/**
 * ORM do Usuário
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
@Entity
@Table(name = "arquivo")
public class Arquivo extends PanacheEntityBase {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arquivo")
    private Integer idArquivo;

    /**
     * Nome
     */
    @Column(name = "nm_arquivo", nullable = false)
    private String nmArquivo;

    /**
     * Bytes do arquivo
     */
    @Column(name = "bytes_arquivo", nullable = false)
    private byte[] bytesArquivo;

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

    public byte[] getBytesArquivo() {
        return this.bytesArquivo;
    }

    public void setBytesArquivo(final byte[] bytesArquivo) {
        this.bytesArquivo = bytesArquivo;
    }

}