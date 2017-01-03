package br.com.parceriasistemas.jsf.cd.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.parceriasistemas.jsf.cd.dao.UsuarioDAO;
import br.com.parceriasistemas.jsf.cd.model.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 2967658020968750795L;

    private String login;
    private String senha;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String login() throws Exception {
        String resultado;
        try {
            Usuario usuarioLogin = new Usuario();
            usuarioLogin.setLoginUsuario(login);
            usuarioLogin.setSenhaUsuario(senha);
            usuarioLogin.criptografarSenhaUsuario();

            Usuario usuarioAutenticado = new UsuarioDAO().verificarDadosLoginBanco(usuarioLogin);

            if (usuarioAutenticado != null) {
                UsuarioAutenticadoBean.adicionarUsuarioNaSessao(usuarioAutenticado);

                resultado = "/pages/pageWelcome.xhtml?faces-redirect=true";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Usu�rio logado"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Login!", "Usu�rio ou senha inv�lida"));
                resultado = "/login.xhtml?faces-redirect=false";
            }
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
}
