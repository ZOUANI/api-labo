/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.api.service.impl;

import com.zs.api.bean.ApiElement;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MoulaYounes
 */
@Stateless
public class ApiElementFacade extends AbstractFacade<ApiElement> {

    @PersistenceContext(unitName = "api-labo-v2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ApiElementFacade() {
        super(ApiElement.class);
    }

    //
   public  List<ApiElement> findAllOrderByGroupeOrderByNumero() {
       List<ApiElement> list= em.createQuery("SELECT a FROM ApiElement a ORDER BY a.apiGroupe.numero, a.numero").getResultList();
       return list;
   }
    
}
