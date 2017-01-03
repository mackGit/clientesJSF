/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parceriasistemas.jsf.cd.bean;

import br.com.parceriasistemas.jsf.cd.dao.UsuarioDao;
import br.com.parceriasistemas.jsf.cd.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;


@ManagedBean(name="usuarioBean")
@SessionScoped
public class UsuarioCon implements Serializable{
    
    //private List<Usuario> usuarios;
    private UsuarioDao dao = new UsuarioDao();
    private Usuario obj = new Usuario();
    private Usuario selectedObj;
    private static List<Usuario> usuarios = new ArrayList<Usuario>();
    private List<Usuario> filteredUsuarios;
    
    private String nomeUsuarioLogado;
    private Integer idUsuarioLogado;

    public UsuarioCon() {
    }
    
   
    
    @PostConstruct
	public void init() {
            //code
            usuarios = dao.getList();
	}
        public List<Usuario> getUsuarios() {
                return usuarios;
        }
    
    /*
    metodos de login
    */
    
    public String buscaUsuarioLogado() {
        return nomeUsuarioLogado;
    }
    
    public String logarUsuarioBean() throws Exception {
        UsuarioDao usuDao = new UsuarioDao();
        Usuario us;
        String resultado;
        try {
            String encript = DigestUtils.md5Hex(this.obj.getSenhaUsuario());
            this.obj.setSenhaUsuario(encript);
            
            
            us = usuDao.verificarDadosLoginBanco(this.obj);
            if (us != null) {
                this.nomeUsuarioLogado=us.getNomeUsuario();
                this.idUsuarioLogado=us.getIdUsuario();
                FacesContext.getCurrentInstance().getExternalContext()
                                .getSessionMap().put("usuario", us);

                resultado = "/pages/pageWelcome.xhtml?faces-redirect=true";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Usuário Logado" ));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Login!", "Usuário ou Senha Inválida" ));
                resultado = "/login.xhtml?faces-redirect=false";
            }
        } catch (Exception e) {
                    throw e;
        }
        System.out.println("resultado: "+ resultado);
        return resultado;
    }
    
    public boolean verificarSessao() {
        boolean estado;
            if (FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("usuario") == null) {
                estado = false;
                redirectLoginPage();
            } else {
                estado = true;
            }
        return estado;
    }
    
    public void redirectLoginPage() {
        FacesContext.getCurrentInstance().getApplication()
                .getNavigationHandler()
                .handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml?faces-redirect=true");
    }
    
    public String encerrarSessao() {
        FacesContext.getCurrentInstance().getExternalContext()
                    .invalidateSession();
            return "/login.xhtml?faces-redirect=true";
    }
    
    
    
    
    
    
    
    
    
    /*
    metodos do bean
    */
    public void limparCamposUsuarioBean () {
        obj.setNomeUsuario("");
        obj.setLoginUsuario("");
        obj.setEmailUsuario("");
        obj.setSenhaUsuario("");
    }
    
    public void adicionarUsuarioBean () {
        try {
            dao.adicionarUsuarioBanco(obj);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Usuario " + obj.getNomeUsuario() + " Adicionado." ));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao tentar adicionar usuario." ));
            System.out.println("Exception: " + e);
        } finally {
            init();
        }
    }
    
    
    public void removerUsuarioBean () {
        String nomeUsuarioApagar = selectedObj.getNomeUsuario();
        Integer IdUsuarioApagar = selectedObj.getIdUsuario();
        try {
            if (IdUsuarioApagar==idUsuarioLogado) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "O usuário " + nomeUsuarioApagar + " está logado." ));
            } else {
                dao.removerUsuarioBanco(selectedObj);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Usuario " + nomeUsuarioApagar + " Excluído." ));
            }
        } catch (Exception e) {
            System.out.println("erro ao remover: " +e);
        } finally {
            init();
        }
    }
    public void cancelRemoverUsuarioBean () {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem: ", "Nenhum Usuario Excluído!." ));
    }
    
    
    public void atualizarUsuarioBean () {
        String nomeUsuarioAtualizar = selectedObj.getNomeUsuario();
        // verifica se o id do usuário logado é o mesmo do usuário a ser atualizado; Se for, atualiza o nome na "sessão"
        try {
            if(idUsuarioLogado==selectedObj.getIdUsuario()) {
                nomeUsuarioLogado = selectedObj.getNomeUsuario();
            }
            
            String encrypt = DigestUtils.md5Hex(selectedObj.getSenhaUsuario());
            // criptografa a senha
            selectedObj.setSenhaUsuario(encrypt);
            dao.atualizarUsuarioBanco(selectedObj);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Usuario " + nomeUsuarioAtualizar + " Atualizado!." ));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao tentar atualizar usuario." ));
            System.out.println("Exception: " + e);
        } finally {
            init();
        }
    }
    public void cancelAtualizarUsuarioBean () {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem: ", "Nenhum Usuario Foi Alterado!." ));
    }
    
    
    
    
    /**/
    
    
    public Usuario getObj() {
        return obj;
    }

    public void setUsuario(Usuario obj) {
        this.obj = obj;
    }
    
    public Usuario getSelectedObj() {
        System.out.println("usuario selecionado: "+selectedObj);
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

    public List<Usuario> getFilteredUsuarios() {
        return filteredUsuarios;
    }

    public void setFilteredUsuarios(List<Usuario> filteredUsuarios) {
        this.filteredUsuarios = filteredUsuarios;
    }
    
    
    
    
}

    

