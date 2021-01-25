package com.zs.api.controler;

import com.zs.api.bean.ApiBook;
import com.zs.api.bean.TestIdentificationApi;
import com.zs.api.bean.TestIdentificationElement;
import com.zs.api.bean.TestIdentificationGroupe;
import com.zs.api.controler.util.JsfUtil;
import com.zs.api.controler.util.JsfUtil.PersistAction;
import com.zs.api.service.impl.TestIdentificationApiFacade;

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

@Named("testIdentificationApiController")
@SessionScoped
public class TestIdentificationApiController implements Serializable {

    @EJB
    private com.zs.api.service.impl.TestIdentificationApiFacade ejbFacade;
    @EJB
    private com.zs.api.service.impl.TestIdentificationElementFacade testIdentificationElementFacade;

    @EJB
    private com.zs.api.service.impl.TestIdentificationGroupeFacade testIdentificationGroupeFacade;

    @EJB
    private com.zs.api.service.impl.TestIdentificationApiFacade testIdentificationApiFacade;

    @EJB
    private com.zs.api.service.impl.ApiBookFacade apiBooApiFacade;

    private List<TestIdentificationApi> items = null;
    private TestIdentificationApi selected;
    private TestIdentificationElement testIdentificationElement;
    private List<TestIdentificationElement> testIdentificationElements;
    private List<TestIdentificationGroupe> testIdentificationGroupes;

    public void changePoids(TestIdentificationElement myTestIdentificationElement) {
        if (myTestIdentificationElement.isSelected()) {
            myTestIdentificationElement.setPoidsSelected(myTestIdentificationElement.getPoids());
        } else {
            myTestIdentificationElement.setPoidsSelected(0L);
        }
        testIdentificationGroupes = testIdentificationGroupeFacade.groupeByGroupeApi(testIdentificationElements);
        testIdentificationApiFacade.groupeByGroupeApi(getSelected(), testIdentificationGroupes);
        ApiBook apiBook = apiBooApiFacade.findByIdentification(getSelected().getIdentification());
        if (apiBook != null) {
            JsfUtil.addSuccessMessage("une bacterie à été trouvée : "+apiBook.getApiBacterie().getReference()+" ("+getSelected().getReference()+")");
            getSelected().setApiBacterie(apiBook.getApiBacterie());
        }else{
            System.out.println("ma kayna ta bacterie");
        }
    }

    public void init() {
        testIdentificationElements = testIdentificationElementFacade.prepare();
        testIdentificationGroupes = testIdentificationGroupeFacade.groupeByGroupeApi(testIdentificationElements);
        testIdentificationApiFacade.groupeByGroupeApi(getSelected(), testIdentificationGroupes);
    }

    public TestIdentificationElement getTestIdentificationElement() {
        return testIdentificationElement;
    }

    public void setTestIdentificationElement(TestIdentificationElement testIdentificationElement) {
        this.testIdentificationElement = testIdentificationElement;
    }

    public List<TestIdentificationElement> getTestIdentificationElements() {
        return testIdentificationElements;
    }

    public void setTestIdentificationElements(List<TestIdentificationElement> testIdentificationElements) {
        this.testIdentificationElements = testIdentificationElements;
    }

    public TestIdentificationApiController() {
    }

    public TestIdentificationApi getSelected() {
        if (selected == null) {
            selected = new TestIdentificationApi();
        }
        return selected;
    }

    public void setSelected(TestIdentificationApi selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {

    }

    private TestIdentificationApiFacade getFacade() {
        return ejbFacade;
    }

    public TestIdentificationApi prepareCreate() {
        selected = new TestIdentificationApi();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TestIdentificationApiCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TestIdentificationApiUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TestIdentificationApiDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TestIdentificationApi> getItems() {
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

    public TestIdentificationApi getTestIdentificationApi(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<TestIdentificationApi> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TestIdentificationApi> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TestIdentificationApi.class)
    public static class TestIdentificationApiControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TestIdentificationApiController controller = (TestIdentificationApiController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "testIdentificationApiController");
            return controller.getTestIdentificationApi(getKey(value));
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
            if (object instanceof TestIdentificationApi) {
                TestIdentificationApi o = (TestIdentificationApi) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TestIdentificationApi.class.getName()});
                return null;
            }
        }

    }

}
