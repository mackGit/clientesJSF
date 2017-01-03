/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parceriasistemas.jsf.cd.dao;

import java.util.List;

import br.com.parceriasistemas.jsf.cd.model.Cliente;

/**
 *
 * @author Mack
 */
public class ClienteDAO extends BaseDAO {

    @SuppressWarnings("unchecked")
    public List<Cliente> getList() {
        List<Cliente> retorno = getListByCriteriaClass(Cliente.class);
        return retorno;
    }
}
