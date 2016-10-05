package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

/**
 *
 * @author Takaya Sakuma
 * ラベル用テーブルをDBに作成するクラス
 */

@Entity
@Table(name = "label")
public class Label extends Model {
    @Id
    @GeneratedValue
    public Long id;

    @Constraints.Required
    @Column(nullable = false, length = 255)
    public String title;

    @Column(name = "CREATE_DATE", nullable = false)
    public java.util.Date createDate;

    @Column(name = "UPDATE_DATE", nullable = false)
    public java.util.Date updateDate;

    private static Finder<Long, Label> find = new Finder<Long, Label>(Label.class);

    public static Label findById(long id) {
        return find.where()
                .eq("id", id)
                .findUnique();
    }

    public static List<Label> findAll() {
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
}