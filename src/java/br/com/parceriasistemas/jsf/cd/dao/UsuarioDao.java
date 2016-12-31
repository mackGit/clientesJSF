/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parceriasistemas.jsf.cd.dao;

import java.util.List;

import br.com.parceriasistemas.jsf.cd.model.Usuario;

/**
 *
 * @author matheus
 */
public class UsuarioDAO extends BaseDAO {

    @SuppressWarnings("unchecked")
    public Usuario verificarDadosLoginBanco(Usuario usuario) {
        String hql = "SELECT u FROM Usuario u WHERE loginUsuario = '" + usuario.getLoginUsuario() + "' and senhaUsuario = '" + usuario.getSenhaUsuario() + "'";
        List<Usuario> usuarios = getListByHQLQuery(hql);

        Usuario us = null;
        if (usuarios != null && !usuarios.isEmpty()) {
            us = (Usuario) usuarios.get(0);
        }
        return us;
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> getList() {
        List<Usuario> retorno = getListByCriteriaClass(Usuario.class);
        return retorno;
    }
}
