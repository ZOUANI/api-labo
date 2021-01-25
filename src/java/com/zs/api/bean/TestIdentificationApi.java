/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.api.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author MoulaYounes
 */
@Entity
public class TestIdentificationApi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    private String referencPatient;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateTestIdentificationApi;
    private String identification;
    @ManyToOne
    private ApiBacterie apiBacterie;
    private String description;

    @OneToMany(mappedBy = "testIdentificationApi")
    private List<TestIdentificationGroupe> testIdentificationGroupes;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReferencPatient() {
        return referencPatient;
    }

    public void setReferencPatient(String referencPatient) {
        this.referencPatient = referencPatient;
    }

    public Date getDateTestIdentificationApi() {
        return dateTestIdentificationApi;
    }

    public void setDateTestIdentificationApi(Date dateTestIdentificationApi) {
        this.dateTestIdentificationApi = dateTestIdentificationApi;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public ApiBacterie getApiBacterie() {
        return apiBacterie;
    }

    public void setApiBacterie(ApiBacterie apiBacterie) {
        this.apiBacterie = apiBacterie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TestIdentificationGroupe> getTestIdentificationGroupes() {
        return testIdentificationGroupes;
    }

    public void setTestIdentificationGroupes(List<TestIdentificationGroupe> testIdentificationGroupes) {
        this.testIdentificationGroupes = testIdentificationGroupes;
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
        if (!(object instanceof TestIdentificationApi)) {
            return false;
        }
        TestIdentificationApi other = (TestIdentificationApi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return reference+"("+identification+")";
    }

}
