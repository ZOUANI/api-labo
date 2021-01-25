/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.api.service.impl;

import com.zs.api.bean.Api;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MoulaYounes
 */
@Stateless
public class ApiFacade extends AbstractFacade<Api> {

    @PersistenceContext(unitName = "api-labo-v2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ApiFacade() {
        super(Api.class);
    }
    
}
