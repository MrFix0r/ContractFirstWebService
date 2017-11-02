package db;

import javax.persistence.*;

@Entity
@Table(name = "marks", schema = "uni", catalog = "postgres")
public class MarksEntity {
    private int rating;
    private int id;
    private Integer studentid;
    private Integer subjectid;

    @Basic
    @Column(name = "rating", nullable = false)
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "studentid", nullable = true)
    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    @Basic
    @Column(name = "subjectid", nullable = true)
    public Integer getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarksEntity that = (MarksEntity) o;

        if (rating != that.rating) return false;
        if (id != that.id) return false;
        if (studentid != null ? !studentid.equals(that.studentid) : that.studentid != null) return false;
        if (subjectid != null ? !subjectid.equals(that.subjectid) : that.subjectid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rating;
        result = 31 * result + id;
        result = 31 * result + (studentid != null ? studentid.hashCode() : 0);
        result = 31 * result + (subjectid != null ? subjectid.hashCode() : 0);
        return result;
    }
}
