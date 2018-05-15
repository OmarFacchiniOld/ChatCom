package chatcom.model;
// Generated 17-mar-2018 13.20.00 by Hibernate Tools 4.3.1


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Instance generated by hbm2java
 */
@Entity
@Table(name="instance"
    ,catalog="chatcom"
)@XmlRootElement

public class Instance  implements java.io.Serializable {


     private Integer id;
     private Chatgroup chatgroup;
     private Message message;
     private User user;

    public Instance() {
    }

    public Instance(Chatgroup chatgroup, Message message, User user) {
       this.chatgroup = chatgroup;
       this.message = message;
       this.user = user;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_chatgroup", nullable=false)
    public Chatgroup getChatgroup() {
        return this.chatgroup;
    }
    
    public void setChatgroup(Chatgroup chatgroup) {
        this.chatgroup = chatgroup;
    }

@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_message", nullable=false)
    public Message getMessage() {
        return this.message;
    }
    
    public void setMessage(Message message) {
        this.message = message;
    }

@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_user", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }




}


