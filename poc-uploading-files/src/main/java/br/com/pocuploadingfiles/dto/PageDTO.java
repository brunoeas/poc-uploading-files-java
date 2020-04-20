package br.com.pocuploadingfiles.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO para uma query com paginação
 *
 * @param <T> - Tipo da lista de dados paginada
 * @author Bruno Eduardo
 */
public class PageDTO<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Lista com dados paginados
     */
    private List<T> listData;

    /**
     * Número total de dados
     */
    private long countTotal;

    public List<T> getListData() {
        return this.listData;
    }

    public void setListData(final List<T> listData) {
        this.listData = listData;
    }

    public long getCountTotal() {
        return this.countTotal;
    }

    public void setCountTotal(final long countTotal) {
        this.countTotal = countTotal;
    }

}