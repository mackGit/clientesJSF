/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parceriasistemas.jsf.cd.bean;



import br.com.parceriasistemas.jsf.cd.dao.CidadeDao;
import br.com.parceriasistemas.jsf.cd.dao.ClienteDao;
import br.com.parceriasistemas.jsf.cd.model.Cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="clienteBean")
@SessionScoped
public class ClienteCon implements Serializable{    
    
    //private List<Cliente> clientes;
    private ClienteDao dao = new ClienteDao();
    private Cliente obj = new Cliente();
    private Cliente selectedObj;
    private static List<Cliente> clientes = new ArrayList<Cliente>();
    private CidadeDao cidadeDao = new CidadeDao();
    private List<Cliente> filteredClientes;

    
    
    public ClienteCon() {
    }
    
    
    
    
    
    
    public void adicionarClienteBean () {
        try {
            dao.adicionarClienteBanco(obj);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Cliente " + obj.getNomeCliente() + " Adicionado." ));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao tentar adicionar cliente." ));
            System.out.println("Exception: " + e);
        }
    }
    
    /*
    metodos do bean
    */
    public void limparCamposClienteBean () {
        obj.setBairroCliente("");
        obj.setCepCliente("");
        obj.setCidadeCliente(null);
        obj.setComplementoCliente("");
        obj.setCpfCnpjCliente("");
        obj.setDtNascimentoCliente(null);
        obj.setEmailCliente("");
        obj.setEnderecoCliente("");
        obj.setNomeCliente("");
        obj.setNumeroCliente(null);
        obj.setStCliente(true);
        obj.setTelCelularCliente("");
        obj.setTelComercialCliente("");
    }
    
    
    
    
    public void removerClienteBean () {
        String nomeClienteApagar = selectedObj.getNomeCliente();
        dao.removerClienteBanco(selectedObj);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Cliente " + nomeClienteApagar + " Excluído." ));
    }
    public void cancelRemoverClienteBean () {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem: ", "Nenhum Cliente Excluído!." ));
    }
    
    
    public void atualizarClienteBean () {
        String nomeClienteApagar = selectedObj.getNomeCliente();
        try {
            dao.atualizarClienteBanco(selectedObj);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Cliente " + nomeClienteApagar + " Atualizado!." ));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Erro ao tentar atualizar cliente." ));
            System.out.println("Exception: " + e);
        }
    }
    public void cancelAtualizarClienteBean () {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem: ", "Nenhum Cliente Foi Alterado!." ));
    }
    
    
    
    
    /**/

    public List<Cliente> getClientes() {
        clientes = dao.getList();
        return clientes;
    }
    
    public Cliente getObj() {
        return obj;
    }

    public void setCliente(Cliente obj) {
        this.obj = obj;
    }
    
    public Cliente getSelectedObj() {
        System.out.println("cliente selecionado: "+selectedObj);
        return selectedObj;
    }
    
    public void setSelectedObj(Cliente selectedObj) {
        this.selectedObj = selectedObj;
    }

    public ClienteDao getDao() {
        return dao;
    }

    public void setDao(ClienteDao dao) {
        this.dao = dao;
    }

    public CidadeDao getCidadeDao() {
        return cidadeDao;
    }

    public void setCidadeDao(CidadeDao cidadeDao) {
        this.cidadeDao = cidadeDao;
    }
    
    public List<Cliente> getFilteredClientes() {
        return filteredClientes;
    }

    public void setFilteredClientes(List<Cliente> filteredClientes) {
        this.filteredClientes = filteredClientes;
    }
    
}
