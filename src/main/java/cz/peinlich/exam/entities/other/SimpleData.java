package cz.peinlich.exam.entities.other;

import javax.persistence.*;

/**
 * This is a sample entity to check that my environment is set up. I do not know what entities I will
 * need in exam.
 * <p/>
 * User: George
 * Date: 20.5.2014
 * Time: 5:29
 */
@Entity
public class SimpleData {
    Long id;
    String content;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
