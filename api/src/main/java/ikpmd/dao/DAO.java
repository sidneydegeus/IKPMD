package ikpmd.dao;


import ikpmd.entity.EntityObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Sidney on 12/5/2016.
 */
public abstract class DAO {

    @PersistenceContext
    protected EntityManager manager;

    protected boolean performInsert(EntityObject entity) {
        boolean result = false;
        try{
            manager.persist(entity);
            result = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        manager.flush();
        manager.clear();
        return result;
    }

    protected boolean performUpdate(EntityObject entity) {
        boolean result = false;
        try{
            manager.merge(entity);
            result = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        manager.flush();
        manager.clear();
        return result;
    }

    protected boolean performDelete(EntityObject entity) {
        boolean result = false;
        try{
            manager.remove(manager.contains(entity) ? entity : manager.merge(entity));
            result = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        manager.flush();
        manager.clear();
        return result;
    }
}
