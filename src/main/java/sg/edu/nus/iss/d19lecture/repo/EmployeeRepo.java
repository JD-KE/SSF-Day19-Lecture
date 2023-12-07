package sg.edu.nus.iss.d19lecture.repo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import sg.edu.nus.iss.d19lecture.model.Employee;

@Repository
public class EmployeeRepo {

    private String hashRef = "employees";

    @Resource(name="redisEmployeeTemplate")
    private HashOperations<String,String,Employee> hOps;

    // for using ListOp
    @Resource(name="redisEmployeeTemplate")
    private ListOperations<String,Employee> lOps;

    public void saveRecord(Employee e) {
        hOps.put(hashRef, e.getId().toString(), e);
    }

    public Employee getRecord(Integer id) {
        Employee e = hOps.get(hashRef, String.valueOf(id));
        return e;
    }

    public Map<String,Employee> getAll() {
        Map<String,Employee> employees = hOps.entries(hashRef);

        return employees;
    }

    
}
