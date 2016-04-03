package com.devcolibri.bean;

import com.devcolibri.entity.Usev;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@Local
public class UserBean {

    // Будет инициализирован контейнером Glassfish
    // unitName = "DEVMODE" - это имя persistence-unit
    // EntityManager дает возможность выполнять CRUD запросы в БД
    @PersistenceContext(unitName = "DEVMODE")
    private EntityManager em;

    // Добавляем User-а В базу данных
    public Usev add(Usev usev){
        return em.merge(usev);
    }

    // Получаем пользователя по id
    public Usev get(long id){
        return em.find(Usev.class, id);
    }

    // обновляем пользователя
    // если User которого мыпытаемся обновить нет,
    // то запишется он как новый
    public void update(Usev usev){
        add(usev);
    }

    // удаляем User по id
    public void delete(long id){
        em.remove(get(id));
    }

    // Получаем все пользователей с БД
    public List<Usev> getAll(){
        TypedQuery<Usev> namedQuery = em.createNamedQuery("Usev.getAll", Usev.class);
        return namedQuery.getResultList();
    }

}
