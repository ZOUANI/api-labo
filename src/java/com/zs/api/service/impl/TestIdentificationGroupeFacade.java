/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.api.service.impl;

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
public class TestIdentificationGroupeFacade extends AbstractFacade<TestIdentificationGroupe> {

    @PersistenceContext(unitName = "api-labo-v2PU")
    private EntityManager em;
    @EJB
    private ApiGroupeFacade apiGroupeFacade;

    public List<TestIdentificationGroupe> groupeByGroupeApi(List<TestIdentificationElement> testIdentificationElements) {
        List<TestIdentificationGroupe> testIdentificationGroupes = new ArrayList<>();
        ApiGroupe apiGroupe = apiGroupeFacade.find(52L);
        Long codeGroupe = 0L;
        int i = 0;
        for (TestIdentificationElement testIdentificationElement : testIdentificationElements) {
            ApiGroupe apiGroupeLoop = testIdentificationElement.getApiElement().getApiGroupe();
            if (apiGroupeLoop.getId().equals(apiGroupe.getId())) {
                codeGroupe += testIdentificationElement.getPoidsSelected();
                if (i == testIdentificationElements.size() - 1) {
                    TestIdentificationGroupe testIdentificationGroupe = new TestIdentificationGroupe();
                    testIdentificationGroupe.setCode(codeGroupe + "");
                    testIdentificationGroupe.setApiGroupe(apiGroupeLoop);
                    testIdentificationGroupes.add(testIdentificationGroupe);
                }
            } else {
                TestIdentificationGroupe testIdentificationGroupe = new TestIdentificationGroupe();
                testIdentificationGroupe.setCode(codeGroupe + "");
                testIdentificationGroupe.setApiGroupe(apiGroupe);
                testIdentificationGroupes.add(testIdentificationGroupe);
                codeGroupe = testIdentificationElement.getPoidsSelected();
                apiGroupe = apiGroupeLoop;
            }

            i++;
        }
        return testIdentificationGroupes;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TestIdentificationGroupeFacade() {
        super(TestIdentificationGroupe.class);
    }

}
