package database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MenuItemTest {
  private EntityManagerFactory entityManagerFactory;

  @Before
  public void setUp() {
    //Create link to the database
    entityManagerFactory = Persistence.createEntityManagerFactory("server.database");
  }

  @After
  public void tearDown() {
    //Close link to the database
    entityManagerFactory.close();
  }

  @Test
  public void createMenuItemTest() {

    EntityManager entityManager;
    //Create new Franchise
    entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Category category = new Category("Food");
    entityManager.persist(category);
    entityManager.getTransaction().commit();
    entityManager.close();

    //Create new menu item
    entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    MenuItem menuItem = new MenuItem("Burger", "Got meat",
        "Well it's a burger", 1.00, false, false,
        false, category);
    entityManager.persist(menuItem);
    entityManager.getTransaction().commit();
    entityManager.close();


    //Get menu item from database.
    entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    List<MenuItem> result = entityManager.createQuery("from MenuItem ",
        MenuItem.class).getResultList();

    for (MenuItem item : result) {
      assertEquals("Check id", item.getMenuItemId(), menuItem.getMenuItemId());
      assertEquals("Check name", item.getName(), menuItem.getName());
      assertEquals("Check allergy info", item.getAllergyInfo(), menuItem.getAllergyInfo());
      assertEquals("Check desc", item.getDescription(), menuItem.getDescription());
      assertEquals("Check price", item.getPrice(), menuItem.getPrice());
      assertEquals("Check isVegan", item.getVegan(), menuItem.getVegan());
      assertEquals("Check veg", item.getVegitarrenan(), menuItem.getVegitarrenan());
      assertEquals("Check gluten", item.getGlutenFree(), menuItem.getGlutenFree());
      assertEquals("Check category", item.getCategory().getCategoryId(),
          menuItem.getCategory().getCategoryId());
    }

    entityManager.getTransaction().commit();
    entityManager.close();
  }
}
