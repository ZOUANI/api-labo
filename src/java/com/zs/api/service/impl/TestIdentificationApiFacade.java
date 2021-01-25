/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.api.service.impl;

import com.zs.api.bean.TestIdentificationApi;
import com.zs.api.bean.TestIdentificationElement;
import com.zs.api.bean.TestIdentificationGroupe;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MoulaYounes
 */
@Stateless
public class TestIdentificationApiFacade extends AbstractFacade<TestIdentificationApi> {

    @PersistenceContext(unitName = "api-labo-v2PU")
    private EntityManager em;
    
     public  void groupeByGroupeApi(TestIdentificationApi testIdentificationApi,List<TestIdentificationGroupe> testIdentificationGroupes) {
         String identification="";
         for (TestIdentificationGroupe testIdentificationGroupe : testIdentificationGroupes) {
             identification+=testIdentificationGroupe.getCode()+"";
         }
         testIdentificationApi.setIdentification(identification);
         testIdentificationApi.setTestIdentificationGroupes(testIdentificationGroupes);
     }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TestIdentificationApiFacade() {
        super(TestIdentificationApi.class);
    }
    
}
