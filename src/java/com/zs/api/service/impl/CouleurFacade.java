/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.api.service.impl;

import com.zs.api.bean.Couleur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MoulaYounes
 */
@Stateless
public class CouleurFacade extends AbstractFacade<Couleur> {

    @PersistenceContext(unitName = "api-labo-v2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CouleurFacade() {
        super(Couleur.class);
    }
    
}
