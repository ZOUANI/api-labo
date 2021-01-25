/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.api.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author MoulaYounes
 */
@Entity
public class TestIdentificationGroupe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private ApiGroupe apiGroupe;
    @ManyToOne
    private TestIdentificationApi testIdentificationApi;
    private String code;
    @OneToMany(mappedBy = "testIdentificationGroupe")
    private List<TestIdentificationElement> testIdentificationElements;

    public List<TestIdentificationElement> getTestIdentificationElements() {
        return testIdentificationElements;
    }

    public void setTestIdentificationElements(List<TestIdentificationElement> testIdentificationElements) {
        this.testIdentificationElements = testIdentificationElements;
    }

    public ApiGroupe getApiGroupe() {
        return apiGroupe;
    }

    public void setApiGroupe(ApiGroupe apiGroupe) {
        this.apiGroupe = apiGroupe;
    }

    public TestIdentificationApi getTestIdentificationApi() {
        return testIdentificationApi;
    }

    public void setTestIdentificationApi(TestIdentificationApi testIdentificationApi) {
        this.testIdentificationApi = testIdentificationApi;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestIdentificationGroupe)) {
            return false;
        }
        TestIdentificationGroupe other = (TestIdentificationGroupe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zs.api.bean.TestIdentificationGroupe[ id=" + id + " ]";
    }

}
