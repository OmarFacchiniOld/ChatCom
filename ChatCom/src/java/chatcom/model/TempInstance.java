/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatcom.model;

/**
 *
 * @author Uberti Davide <ubertidavide@gmail.com>
 */
public class TempInstance {
    
    private Integer id;
    private Chatgroup chatgroup;
    private Message message;
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Chatgroup getChatgroup() {
        return chatgroup;
    }

    public void setChatgroup(Chatgroup chatgroup) {
        this.chatgroup = chatgroup;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
