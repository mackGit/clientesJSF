/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parceriasistemas.jsf.cd.bean;

import br.com.parceriasistemas.jsf.cd.dao.CidadeDao;
import br.com.parceriasistemas.jsf.cd.model.Cidade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="cidadeBean")
@SessionScoped
public class CidadeCon implements Serializable{
    
    private Cidade cidade = new Cidade();
    private List<Cidade> cidades;
    private CidadeDao cidadeDao = new CidadeDao();

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getCidades() {
        cidades = cidadeDao.getList();
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
    
    

    
}
