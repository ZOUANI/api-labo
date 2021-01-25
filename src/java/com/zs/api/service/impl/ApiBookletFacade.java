/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.api.service.impl;

import com.zs.api.bean.ApiBooklet;
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
public class ApiBookletFacade extends AbstractFacade<ApiBooklet> {

    @PersistenceContext(unitName = "api-labo-v2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ApiBookletFacade() {
        super(ApiBooklet.class);
    }
//a.positif = 'true'
     public ApiBooklet findByApiElement(ApiElement apiElement) {
        List<ApiBooklet> resultList = em.createQuery("SELECT a FROM ApiBooklet a WHERE   a.apiElement.id="+apiElement.getId()).getResultList();
        return resultList.get(0);
    }
    
}
