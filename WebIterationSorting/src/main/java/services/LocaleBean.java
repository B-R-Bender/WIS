package services;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by bender on 11.09.16.
 */
@SessionScoped
@ManagedBean
public class LocaleBean implements Serializable {

    private Locale locale;
    private List<SelectItem> locales;

    @ManagedProperty("#{messages}")
    private ResourceBundle resourceBundle;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
        locales = new ArrayList<>();
        locales.add(new SelectItem(resourceBundle.getString("common.labels.language.russianCode"),
                resourceBundle.getString("common.labels.language.russian")));
        locales.add(new SelectItem(resourceBundle.getString("common.labels.language.englishCode"),
                resourceBundle.getString("common.labels.language.english")));
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public List<SelectItem> getLocales() {
        return locales;
    }

    public void setLocales(List<SelectItem> locales) {
        this.locales = locales;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }
}
