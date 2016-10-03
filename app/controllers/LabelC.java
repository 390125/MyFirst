package controllers;

import com.google.inject.Inject;
import models.Label;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;
import views.html.LabelC.index;
import views.html.LabelC.add;

public class LabelC extends Controller {
    @Inject
    FormFactory formFactory;

    public Result index() {
        List<Label> labels = Label.findAll();
        return ok(index.render(labels));
    }
    public Result add() {
        Form<Label> f = formFactory.form(Label.class);
        return ok(add.render(f));
    }
    public Result create() {
        Form<Label> f = formFactory.form(Label.class).bindFromRequest();
        if (!f.hasErrors()) {
            Label label = f.get();
            label.save();
            return redirect(routes.LabelC.index().url());
        } else {
            return badRequest(add.render(f));
        }
    }
}