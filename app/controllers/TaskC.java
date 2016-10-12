package controllers;

import com.google.inject.Inject;
import models.Label;
import models.Task;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.LabelC.show;

public class TaskC extends Controller {

    @Inject
    FormFactory formFactory;
    public Result create(Long labelId) {
    	Label label = Label.findById(labelId);
        Form<Task> f = formFactory.form(Task.class).bindFromRequest();
        	if (!f.hasErrors()) {
        		Task task = f.get();
        		task.save();
        		return redirect(routes.LabelC.show(labelId).url());
        	} else {
        return badRequest(show.render(label, f));
        }
    }
    public Result destroy(Long taskId){
    	Task task = Task.findById(taskId) ;
    	if(task != null){
    		Long labelId = task.labelId ;
    		task.delete();
    		return redirect(routes.LabelC.show(labelId).url());
    	}else{
    		return badRequest();
    	}
    }


}

