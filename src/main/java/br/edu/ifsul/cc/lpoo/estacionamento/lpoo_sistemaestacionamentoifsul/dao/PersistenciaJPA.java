/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.estacionamento.lpoo_sistemaestacionamentoifsul.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Pessoa;

public class PersistenciaJPA implements InterfaceBD{
    public EntityManager entity;
    public EntityManagerFactory factory;
    
    public PersistenciaJPA() {
        factory = Persistence.createEntityManagerFactory("pu_lpoo_estacionamento");
               
        entity = factory.createEntityManager();
    }

    @Override
    public Boolean conexaoAberta() {
        return entity.isOpen();
    }

    @Override
    public void fecharConexao() {
        entity.close();
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void persist(Object o) throws Exception {
        try {
            entity = getEntityManager();
            entity.getTransaction().begin();
            entity.remove(o);
            entity.persist(o);
            entity.getTransaction().commit();
        } catch (Exception e) {
            if (entity.getTransaction().isActive()) {
                entity.getTransaction().rollback();
            }
        }
    }

    @Override
    public void remover(Object o) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public EntityManager getEntityManager() {
        if(entity == null || !entity.isOpen()) {
            entity = factory.createEntityManager();
        }
        return entity;
    }
    
    //funções para listar dados
    
    public List<Pessoa> getPessoas(){
        entity = getEntityManager();
        try {
            TypedQuery<Pessoa> query = entity.createQuery("select p from Pessoa p", Pessoa.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar pessoas: " + e);
            return null;
        }
    }
}
