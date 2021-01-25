/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.api.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author MoulaYounes
 */
@Entity
public class TestIdentificationElement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private ApiElement apiElement;
    private Long poids;
    private Long poidsSelected;
    private boolean selected;
    @ManyToOne
    private Couleur couleur;
    @ManyToOne
    private TestIdentificationGroupe testIdentificationGroupe;

    public TestIdentificationGroupe getTestIdentificationGroupe() {
        return testIdentificationGroupe;
    }

    public void setTestIdentificationGroupe(TestIdentificationGroupe testIdentificationGroupe) {
        this.testIdentificationGroupe = testIdentificationGroupe;
    }

    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Long getPoidsSelected() {
        return poidsSelected;
    }

    public void setPoidsSelected(Long poidsSelected) {
        this.poidsSelected = poidsSelected;
    }

    public ApiElement getApiElement() {
        return apiElement;
    }

    public void setApiElement(ApiElement apiElement) {
        this.apiElement = apiElement;
    }

    public Long getPoids() {
        return poids;
    }

    public void setPoids(Long poids) {
        this.poids = poids;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
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
        if (!(object instanceof TestIdentificationElement)) {
            return false;
        }
        TestIdentificationElement other = (TestIdentificationElement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TestIdentificationElement{" + "apiElement=" + apiElement.getLibelle() + ", poids=" + poids + ", couleur=" + couleur.getCode() + '}';
    }

}
