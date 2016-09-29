/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.marfeel.qualify.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Velez
 */
@Entity
@Table(name = "websites")
@XmlRootElement
public class Website implements Serializable, Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "url")
    private String url;

    @Size(max = 10)
    @Column(name = "rank")
    private String rank;

    @Size(max = 10)
    @Column(name = "qualify")
    private String qualify;

    @Size(max = 50)
    @Column(name = "error")
    private String error;
    
    @Column(name = "creationtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationtime;

    public Website() {
    }

    public Website(String url) {
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getQualify() {
        return qualify;
    }

    public void setQualify(String qualify) {
        this.qualify = qualify;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public void emptyError() {
        this.error = null;
    }

    public Date getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }
    
    

    public static enum Qualification {

        YES, NO;

       
    }

}
