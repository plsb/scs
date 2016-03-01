package br.scs.bean;

import br.scs.user.User;
import br.scs.user.UserDAO;
import br.scs.util.UsuarioAtivo;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "contextBean")
@SessionScoped
public class ContextBean implements Serializable{

    private User user = null;

    public User getUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();
        String login = external.getRemoteUser();
        if (this.user == null || !login.equals(this.user.getLogin())) {
            if (login != null) {
                UserDAO uDAO = new UserDAO();
                this.user = uDAO.checkExists("login", login).get(0);
                UsuarioAtivo.setUser(this.user);
            }
        }

        return user;
    }
    private static final long serialVersionUID = -5179064316493977537L;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ContextBean other = (ContextBean) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    
}
