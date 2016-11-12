package ar.edu.itba.paw.forms.admin;

import ar.edu.itba.paw.builders.DocumentBuilder;
import ar.edu.itba.paw.forms.DocumentForm;
import ar.edu.itba.paw.interfaces.admin.IAdminForm;
import ar.edu.itba.paw.models.Document;

public class DocumentAdminForm extends DocumentForm implements IAdminForm<Document> {

    @Override
    public Document buildObjectFromForm() {
        return new DocumentBuilder().setSubject(getSubject()).createModel();
    }

    @Override
    public void loadValuesFromInstance(Document instance) {
        setSubject(instance.getSubject());
        setCourseid((int) instance.getCourse().getCourseid());
    }

}
