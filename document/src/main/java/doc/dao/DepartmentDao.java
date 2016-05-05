package doc.dao;

import doc.entity.Department;
import org.springframework.stereotype.Repository;

/**
 * Created by Amysue on 2016/5/4.
 */
@Repository("depDao")
public class DepartmentDao extends BaseDao<Department> implements IDepartmentDao {
}
