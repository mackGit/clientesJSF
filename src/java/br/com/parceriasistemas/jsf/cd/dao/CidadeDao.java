/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parceriasistemas.jsf.cd.dao;

import java.util.List;

import br.com.parceriasistemas.jsf.cd.model.Cidade;

/**
 *
 * @author matheus
 */
public class CidadeDAO extends BaseDAO {

    @SuppressWarnings("unchecked")
    public List<Cidade> getList() {
        List<Cidade> retorno = getListByCriteriaClass(Cidade.class);
        return retorno;
    }

    public void adicionarCidadeBanco(Cidade cid) {
        insert(cid);
    }

    public void removerCidadeBanco(Cidade cid) {
        delete(cid);
    }

    public void atualizarCidadeBanco(Cidade cid) {
        update(cid);
    }
}
