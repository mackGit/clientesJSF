/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parceriasistemas.jsf.cd.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.parceriasistemas.jsf.cd.dao.UsuarioDAO;
import br.com.parceriasistemas.jsf.cd.model.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = 4230037700031167503L;

    private Usuario usuarioSelecionado;
    private UsuarioDAO usuarioDAO;
    private List<Usuario> usuarios;

    @PostConstruct
    public void init() {
        this.usuarioDAO = new UsuarioDAO();
        atualizarListaUsuarios();
    }

    private void atualizarListaUsuarios() {
        this.usuarios = usuarioDAO.getList();
    }

    public void criarNovoUsuario() {
        this.usuarioSelecionado = new Usuario();
    }

    public void adicionarNovoUsuario() {
        try {
            usuarioDAO.insert(usuarioSelecionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Usuario " + usuarioSelecionado.getNomeUsuario() + " Adicionado."));
            atualizarListaUsuarios();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao tentar adicionar usuario."));
            System.out.println("Exception: " + e);
        }
    }

    public void removerUsuarioSelecionado() {
        String nomeUsuarioApagar = usuarioSelecionado.getNomeUsuario();
        usuarioDAO.delete(usuarioSelecionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Usuario " + nomeUsuarioApagar + " excluído."));
        atualizarListaUsuarios();
    }

    public void cancelarRemocaoUsuarioSelecionado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem: ", "Nenhum Usuario excluído!."));
    }

    public void alterarUsuarioSelecionado() {
        try {
            usuarioSelecionado.criptografarSenhaUsuario();
            usuarioDAO.update(usuarioSelecionado);

            if (usuarioSelecionado.equals(UsuarioAutenticadoBean.getUsuarioAutenticadoNaSessao())) {
                UsuarioAutenticadoBean.atualizarUsuarioNaSessao(usuarioSelecionado);
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Usuario " + usuarioSelecionado.getNomeUsuario() + " Atualizado!."));
            atualizarListaUsuarios();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao tentar atualizar usuario."));
            e.printStackTrace();
        }
    }

    public void cancelarAlteracaoUsuarioSelecionado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem: ", "Nenhum Usuario Foi Alterado!."));
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario selectedObj) {
        this.usuarioSelecionado = selectedObj;
    }
}
