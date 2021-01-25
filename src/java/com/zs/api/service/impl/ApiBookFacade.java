/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.api.service.impl;

import com.zs.api.bean.ApiBook;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MoulaYounes
 */
@Stateless
public class ApiBookFacade extends AbstractFacade<ApiBook> {

    @PersistenceContext(unitName = "api-labo-v2PU")
    private EntityManager em;

    public ApiBook findByIdentification(String identification) {
        String query="SELECT b FROM ApiBook b WHERE b.identification='" + identification + "'";
        System.out.println("query = " + query);
        List<ApiBook> resultList = em.createQuery(query).getResultList();
        return resultList != null && !resultList.isEmpty() ? resultList.get(0) : null;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ApiBookFacade() {
        super(ApiBook.class);
    }

}
