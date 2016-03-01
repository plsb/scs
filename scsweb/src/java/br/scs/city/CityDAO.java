/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.scs.city;

import br.scs.util.GenericDAO;
import br.scs.util.HibernateUtil;
import java.util.List;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Pedro Saraiva
 */
public class CityDAO extends GenericDAO<City> {

    public CityDAO() {
        super(City.class);
    }

    public List<City> listActivesByCity(int idIdbge) {
        List<City> cities=null;
        try {
            this.setSessao(HibernateUtil.getSessionFactory().openSession());
            setTransacao(getSessao().beginTransaction());
            cities = this.getSessao().createCriteria(City.class).
                    add(Restrictions.eq("idIBGE", idIdbge))
                    .add(Restrictions.eq("active", true))
                    .list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return cities;
    }

}
