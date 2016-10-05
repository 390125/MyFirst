package controllers;

import java.util.List;

import com.google.inject.Inject;

import models.Label;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

/**
 *
 * @author Takaya Sakuma
 * ラベルを操作するクラス
 * 各メソッドでラベルの操作を行う
 */

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
    public Result edit(Long id) {
        Label label = Label.findById(id);
        if (label != null) {
            Form<Label> f = formFactory.form(Label.class).fill(label);
            return ok(edit.render(f));
        }
        return notFound();
    }
    public Result update() {
        Form<Label> f = formFactory.form(Label.class).bindFromRequest();
        if (!f.hasErrors()) {
            Label label = f.get();
            label.update();
            return redirect(routes.LabelC.index().url());
        } else {
            return badRequest(edit.render(f));
        }
    }
    public Result delete(long id){
    	Label label = Label.findById(id);
        if (label != null) {
            Form<Label> f = formFactory.form(Label.class).fill(label);
            return ok(delete.render(f));
        }
    	return notFound();
    }
    public Result destroy() {
        Form<Label> f = formFactory.form(Label.class).bindFromRequest();
        if (!f.hasErrors()) {
            Label label = f.get();
            label.delete();
            return redirect(routes.LabelC.index().url());
        } else {
            return badRequest();
        }
    }
}