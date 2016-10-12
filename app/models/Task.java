package models;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.JsonIgnore;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "task")
public class Task extends Model{

	@Id
	@GeneratedValue
	public Long id;

	@Column(name = "label_id", nullable = false , precision = 19)
	public Long labelId ;

	@Constraints.Required
    @Column(nullable = false, length = 255)
    public String title;

    @Column(nullable = false)
    public Boolean done = false;

    @Column(name = "create_date", nullable = false)
    public Date createDate;

    @Column(name = "update_date", nullable = false)
    public Date updateDate;

    private static Finder<Long, Task> find = new Finder<Long, Task>(Task.class);

    public static Task findById(long id) {
        return find.where()
                .eq("id", id)
                .findUnique();
    }
    public static List<Task> findAll() {
        return find.findList();
    }

    @Override
    public void save() {
        Date now = new Date();
        if (this.createDate == null) {
            this.createDate = now;
        }
        this.updateDate = now;
        super.save();
    }

    @Override
    public void update() {
        Date now = new Date();
        if (this.createDate == null) {
            this.createDate = now;
        }
        this.updateDate = now;
        super.update();
    }



    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "label_id", insertable = false, updatable = false)
    public Label label;
}

