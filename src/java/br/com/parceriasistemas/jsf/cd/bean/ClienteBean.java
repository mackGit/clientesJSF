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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.parceriasistemas.jsf.cd.dao.ClienteDAO;
import br.com.parceriasistemas.jsf.cd.model.Cliente;

@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable {

    private static final long serialVersionUID = 8495427135147700288L;

    private ClienteDAO clienteDAO;
    private Cliente clienteSelecionado;
    private List<Cliente> clientes;
    private List<Cliente> filteredClientes;

    @PostConstruct
    public void init() {
        this.clienteDAO = new ClienteDAO();
        atualizarListaClientes();
    }

    private void atualizarListaClientes() {
        this.clientes = clienteDAO.getList();
        this.filteredClientes = null;
    }

    public void adicionarClienteBean() {
        try {
            clienteDAO.insert(clienteSelecionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Cliente " + clienteSelecionado.getNomeCliente() + " Adicionado."));
            atualizarListaClientes();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao tentar adicionar cliente."));
            e.printStackTrace();
        }
    }

    public void limparCamposClienteBean() {
        this.clienteSelecionado = new Cliente();
        this.clienteSelecionado.setStCliente(true);
    }

    public void removerClienteBean() {
        String nomeClienteApagar = clienteSelecionado.getNomeCliente();
        clienteDAO.delete(clienteSelecionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Cliente " + nomeClienteApagar + " excluido."));
        atualizarListaClientes();
    }

    public void cancelRemoverClienteBean() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem: ", "Nenhum Cliente excluído!."));
    }

    public void atualizarClienteBean() {
        String nomeClienteApagar = clienteSelecionado.getNomeCliente();
        try {
            clienteDAO.update(clienteSelecionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Cliente " + nomeClienteApagar + " Atualizado!."));
            atualizarListaClientes();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao tentar atualizar cliente."));
            e.printStackTrace();
        }
    }

    public void cancelAtualizarClienteBean() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem: ", "Nenhum Cliente Foi Alterado!."));
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

    public List<Cliente> getFilteredClientes() {
        return filteredClientes;
    }

    public void setFilteredClientes(List<Cliente> filteredClientes) {
        this.filteredClientes = filteredClientes;
    }
}
