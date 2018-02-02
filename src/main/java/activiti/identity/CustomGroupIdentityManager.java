package activiti.identity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;


public class CustomGroupIdentityManager extends GroupEntityManager {

    @Override
    public List<Group> findGroupsByUser(String userId) {
        Map<String, String> groupMapping = new HashMap<String, String>();
        groupMapping.put("kermit", "admin");
        groupMapping.put("gonzo", "dev");
        groupMapping.put("fozzie", "user");
        
        Group group = new GroupEntity();
        group.setId(groupMapping.get(userId));
        group.setName(groupMapping.get(userId));
        group.setType(groupMapping.get(userId));
        
        return Arrays.asList(group);
    }

    @Override
    public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Group createNewGroup(String groupId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteGroup(String groupId) {
        throw new UnsupportedOperationException();
    }
}
