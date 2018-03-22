package endpoints.tables;

import database.DatabaseManager;
import database.tables.RestaurantTable;
import database.tables.RestaurantTableStaff;
import database.tables.StaffSession;
import java.util.List;
import javax.persistence.EntityManager;
import spark.Request;
import spark.Response;
import util.JsonUtil;

/**
 * This class has the endpoints for assigning waiters to tables.
 *
 * @author Marcus Messer
 */
public class TableAssign {

  /**
   * This gets the assignments of waiters to tables. For the JSON details see
   * <code>TableAssignParams</code>
   *
   * @param request A HTML request.
   * @param response A HTML response.
   * @return A list of table assignments in JSON.
   */
  public static String getTableAssignments(Request request, Response response) {
    TableAssignParams tableAssignParams = JsonUtil.getInstance()
        .fromJson(request.body(), TableAssignParams.class);
    EntityManager entityManager = DatabaseManager.getInstance().getEntityManager();
    List<RestaurantTableStaff> restaurantTableStaffs = entityManager.createQuery(
        "from RestaurantTableStaff tableStaff where "
            + "tableStaff.staff.id = :staff",
        RestaurantTableStaff.class).setParameter("staff", tableAssignParams.getStaffId())
        .getResultList();

    TableAssignParams[] out = new TableAssignParams[restaurantTableStaffs.size()];

    for (int i = 0; i < out.length; i++) {
      out[i] = new TableAssignParams(restaurantTableStaffs.get(i).getStaff().getEmployeeNumber(),
          restaurantTableStaffs.get(i).getRestaurantTable().getTableNumber());
    }
    return JsonUtil.getInstance().toJson(out);
  }

  /**
   * This method gets the all tables sorted by there IDs and how many waiters are assigned to them.
   *
   * @param request A HTML request
   * @param response A HTML response
   * @return A JSON list of all tables
   */
  public static String getTablesWithAssignmentCount(Request request, Response response) {
    System.out.println(request.body());
    EntityManager entityManager = DatabaseManager.getInstance().getEntityManager();

    StaffSession staffSession = entityManager
        .find(StaffSession.class, request.session().attribute("StaffSessionKey"));

    List<RestaurantTableStaff> restaurantTables = entityManager.createQuery(
        "from RestaurantTableStaff table where table.restaurantTable.franchise = :franchise and "
            + "table.staff.id != :staff or (table.staff.id = :staff and table.isActive = false) order by table.restaurantTable.tableNumber asc ",
        RestaurantTableStaff.class)
        .setParameter("franchise", staffSession.getStaff().getFranchise())
        .setParameter("staff", Long.parseLong(request.body()))
        .getResultList();

    TableAssignData[] tableAssignData = new TableAssignData[restaurantTables.size()];

    for (int i = 0; i < tableAssignData.length; i++) {
      RestaurantTable temp = restaurantTables.get(i).getRestaurantTable();

      Long assignments = (Long) entityManager.createQuery(
          "select count(*) from RestaurantTableStaff tableStaff where tableStaff.restaurantTable = :table"
      ).setParameter("table", temp).getSingleResult();

      tableAssignData[i] = new TableAssignData(temp.getTableNumber(), assignments);
    }
    entityManager.close();

    return JsonUtil.getInstance().toJson(tableAssignData);
  }

  /**
   * This sets the assignments of waiters to tables. See <code>TableAssignParams</code> for JSON
   * details.
   *
   * @param request A HTML request.
   * @param response A HTML response.
   * @return success or fail
   */
  public static String setTableAssignments(Request request, Response response) {
    TableAssignParams tableAssignParams = JsonUtil.getInstance()
        .fromJson(request.body(), TableAssignParams.class);

    EntityManager entityManager = DatabaseManager.getInstance().getEntityManager();

    try {
      entityManager.getTransaction().begin();
      RestaurantTableStaff restaurantTableStaff = entityManager
          .createQuery(
              "from RestaurantTableStaff tableStaff where tableStaff.staff.employeeNumber = :staffId",
              RestaurantTableStaff.class).setParameter("staffId", tableAssignParams.getStaffId())
          .getSingleResult();

      restaurantTableStaff.setRestaurantTable(
          entityManager.find(RestaurantTable.class, tableAssignParams.getTableNumber()));
      entityManager.getTransaction().commit();
    } catch (Exception e) {
      return "failed";
    }

    if (entityManager.getTransaction().isActive()) {
      entityManager.getTransaction().rollback();
    }
    entityManager.close();
    return "success";
  }
}
