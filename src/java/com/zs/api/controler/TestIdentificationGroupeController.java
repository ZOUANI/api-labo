package com.zs.api.controler;

import com.zs.api.bean.TestIdentificationGroupe;
import com.zs.api.controler.util.JsfUtil;
import com.zs.api.controler.util.JsfUtil.PersistAction;
import com.zs.api.service.impl.TestIdentificationGroupeFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("testIdentificationGroupeController")
@SessionScoped
public class TestIdentificationGroupeController implements Serializable {

    @EJB
    private com.zs.api.service.impl.TestIdentificationGroupeFacade ejbFacade;
    private List<TestIdentificationGroupe> items = null;
    private TestIdentificationGroupe selected;

    public TestIdentificationGroupeController() {
    }

    public TestIdentificationGroupe getSelected() {
        return selected;
    }

    public void setSelected(TestIdentificationGroupe selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TestIdentificationGroupeFacade getFacade() {
        return ejbFacade;
    }

    public TestIdentificationGroupe prepareCreate() {
        selected = new TestIdentificationGroupe();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TestIdentificationGroupeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TestIdentificationGroupeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TestIdentificationGroupeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TestIdentificationGroupe> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public TestIdentificationGroupe getTestIdentificationGroupe(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<TestIdentificationGroupe> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TestIdentificationGroupe> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TestIdentificationGroupe.class)
    public static class TestIdentificationGroupeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TestIdentificationGroupeController controller = (TestIdentificationGroupeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "testIdentificationGroupeController");
            return controller.getTestIdentificationGroupe(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TestIdentificationGroupe) {
                TestIdentificationGroupe o = (TestIdentificationGroupe) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TestIdentificationGroupe.class.getName()});
                return null;
            }
        }

    }

}
