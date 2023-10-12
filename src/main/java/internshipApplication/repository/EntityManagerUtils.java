package internshipApplication.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
public class EntityManagerUtils {
/**
 * I am working manually with EntityManager, not with SpringBoot, which I read will handle it for you.
 * I decided to create this class, so I won`t have duplicated code in my repositories.
 */
    public static void closeEntityManager(EntityManager entityManager, EntityManagerFactory emFactory) {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (emFactory != null && emFactory.isOpen()) {
            emFactory.close();
        }
    }
}
