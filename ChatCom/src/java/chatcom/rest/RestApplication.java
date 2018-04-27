/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatcom.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Uberti Davide <ubertidavide@gmail.com>
 */
@ApplicationPath("/api")
public class RestApplication extends Application{

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        // register root resource
        classes.add(ChatgroupResource.class);
        classes.add(UserResource.class);
        classes.add(MessageResource.class);
        classes.add(InstanceResource.class);
        
        return classes;
    }
    
}
