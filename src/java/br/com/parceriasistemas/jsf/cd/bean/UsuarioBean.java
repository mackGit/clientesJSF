/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parceriasistemas.jsf.cd.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.parceriasistemas.jsf.cd.dao.UsuarioDao;
import br.com.parceriasistemas.jsf.cd.model.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = 4230037700031167503L;

    // private List<Usuario> usuarios;
    private UsuarioDao dao = new UsuarioDao();
    private Usuario obj = new Usuario();
    private Usuario selectedObj;
    private static List<Usuario> usuarios = new ArrayList<Usuario>();

    /*
     * metodos do bean
     */
    public void limparCamposUsuarioBean() {
        obj.setNomeUsuario("");
        obj.setLoginUsuario("");
        obj.setEmailUsuario("");
        obj.setSenhaUsuario("");
    }

    public void adicionarUsuarioBean() {
        try {
            dao.adicionarUsuarioBanco(obj);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Usuario " + obj.getNomeUsuario() + " Adicionado."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao tentar adicionar usuario."));
            System.out.println("Exception: " + e);
        }
    }

    public void removerUsuarioBean() {
        String nomeUsuarioApagar = selectedObj.getNomeUsuario();
        dao.removerUsuarioBanco(selectedObj);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Usuario " + nomeUsuarioApagar + " excluído."));
    }

    public void cancelRemoverUsuarioBean() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem: ", "Nenhum Usuario excluído!."));
    }

    public void atualizarUsuarioBean() {
        String nomeUsuarioAtualizar = selectedObj.getNomeUsuario();
        // verifica se o id do usuário logado é o mesmo do usuário a ser
        // atualizado, se for, atualiza o nome na "sessão"
        try {
            String encrypt = DigestUtils.md5Hex(selectedObj.getSenhaUsuario());
            // criptografa a senha
            selectedObj.setSenhaUsuario(encrypt);
            dao.atualizarUsuarioBanco(selectedObj);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Usuario " + nomeUsuarioAtualizar + " Atualizado!."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao tentar atualizar usuario."));
            System.out.println("Exception: " + e);
        }
    }

    public void cancelAtualizarUsuarioBean() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem: ", "Nenhum Usuario Foi Alterado!."));
    }

    public List<Usuario> getUsuarios() {
        usuarios = dao.getList();
        return usuarios;
    }

    public Usuario getObj() {
        return obj;
    }

    public void setUsuario(Usuario obj) {
        this.obj = obj;
    }

    public Usuario getSelectedObj() {
        System.out.println("usuario selecionado: " + selectedObj);
        return selectedObj;
    }

    public void setSelectedObj(Usuario selectedObj) {
        this.selectedObj = selectedObj;
    }

    public UsuarioDao getDao() {
        return dao;
    }

    public void setDao(UsuarioDao dao) {
        this.dao = dao;
    }
}
