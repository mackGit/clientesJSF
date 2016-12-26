/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parcerisistemas.jsf.cd.teste;

import br.com.parceriasistemas.jsf.cd.dao.CidadeDao;
import br.com.parceriasistemas.jsf.cd.model.Cidade;

/**
 *
 * @author matheus
 */
public class Teste {
    
    public static void main(String[] args) {
        
        /*
        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setNomeCliente("math");
        cliente.setCpfCnpjCliente("111.111.111-11");
        
        clienteDao.adicionarClienteBanco(cliente);
        */
        
        
        CidadeDao cidadeDao = new CidadeDao();
        Cidade cidade = new Cidade();
        cidade.setIdCidade(4);
        cidade.setNomeCidade("Curitiba");
        cidade.setEstadoCidade("PR");
        
        cidadeDao.adicionarCidadeBanco(cidade);
        
    }
    
    
    /*
    Cliente editedCliente = (Cliente) event.getObject();  
    clienteDao.atualizaCliente(editedCliente);  
    */
}
