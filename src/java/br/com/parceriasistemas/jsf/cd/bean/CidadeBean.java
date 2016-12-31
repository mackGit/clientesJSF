/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parceriasistemas.jsf.cd.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.parceriasistemas.jsf.cd.dao.CidadeDAO;
import br.com.parceriasistemas.jsf.cd.model.Cidade;

@ManagedBean
@SessionScoped
public class CidadeBean implements Serializable {

    private static final long serialVersionUID = 2783044749189964355L;

    private List<Cidade> cidades;
    private CidadeDAO cidadeDAO;

    @PostConstruct
    public void init() {
        this.cidadeDAO = new CidadeDAO();
        atualizarListaCidades();
    }

    private void atualizarListaCidades() {
        this.cidades = cidadeDAO.getList();
    }

    public List<Cidade> getCidades() {
        return cidades;
    }
}
