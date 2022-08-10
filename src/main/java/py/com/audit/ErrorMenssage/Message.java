<<<<<<< HEAD
package py.com.audit.ErrorMenssage;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author crixx
 */
public class Message {

    public static void addErrorMessage(String id, String message) {
        FacesContext.getCurrentInstance().addMessage(id,
                new FacesMessage(message));

    }
}
=======
package py.com.audit.auditory.ErrorMenssage;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author crixx
 */
public class Message {

    public static void addErrorMessage(String id, String message) {
        FacesContext.getCurrentInstance().addMessage(id,
                new FacesMessage(message));

    }
}
>>>>>>> 32d47b74d9d48a5e74dc4b3f24762222cca1cb51
