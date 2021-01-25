/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.api.service.impl;

import com.zs.api.bean.ApiBooklet;
import com.zs.api.bean.ApiElement;
import com.zs.api.bean.ApiGroupe;
import com.zs.api.bean.TestIdentificationElement;
import com.zs.api.bean.TestIdentificationGroupe;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MoulaYounes
 */
@Stateless
public class TestIdentificationElementFacade extends AbstractFacade<TestIdentificationElement> {

    @PersistenceContext(unitName = "api-labo-v2PU")
    private EntityManager em;
    @EJB
    private ApiElementFacade apiElementFacade;
    @EJB
    private ApiGroupeFacade apiGroupeFacade;
    @EJB
    private ApiBookletFacade apiBookletFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TestIdentificationElementFacade() {
        super(TestIdentificationElement.class);
    }

    public List<TestIdentificationElement> prepare() {
        List<TestIdentificationElement> testIdentificationElements = new ArrayList<>();
        List<ApiElement> apiElements = apiElementFacade.findAllOrderByGroupeOrderByNumero();
       
        for (ApiElement apiElement : apiElements) {
            ApiBooklet apiBooklet = apiBookletFacade.findByApiElement(apiElement);
            
            TestIdentificationGroupe testIdentificationGroupe = new TestIdentificationGroupe();
            testIdentificationGroupe.setApiGroupe(apiBooklet.getApiElement().getApiGroupe());

            TestIdentificationElement testIdentificationElement = new TestIdentificationElement();
            testIdentificationElement.setApiElement(apiElement);
            testIdentificationElement.setCouleur(apiBooklet.getCouleur());
            testIdentificationElement.setPoids(apiBooklet.getApiElement().getPoids());
            testIdentificationElement.setPoidsSelected(apiBooklet.getApiElement().getPoids());
            testIdentificationElement.setSelected(true);
            

            testIdentificationElements.add(testIdentificationElement);
        }
        return testIdentificationElements;
    }

}
