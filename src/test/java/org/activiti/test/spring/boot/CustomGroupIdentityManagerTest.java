package org.activiti.test.spring.boot;

import java.io.IOException;

import activiti.MyApp;
import junit.framework.Assert;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomGroupIdentityManagerTest {

    @Test
    public void executeCustomMybatisMapperQuery() throws Exception {
        AnnotationConfigApplicationContext applicationContext = this.context(MyApp.class);
        RepositoryService repositoryService = applicationContext.getBean(RepositoryService.class);
        RuntimeService runtimeService = applicationContext.getBean(RuntimeService.class);
        TaskService taskService = applicationContext.getBean(TaskService.class);
        
        
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().startableByUser("fozzie").singleResult();
        Assert.assertEquals("waiter", processDefinition.getKey());
        
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskCandidateOrAssigned("kermit").singleResult();
        Assert.assertEquals("usertask1", task.getTaskDefinitionKey());
    }

    private AnnotationConfigApplicationContext context(Class<?>... clzz) throws IOException {
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(clzz);
        annotationConfigApplicationContext.refresh();
        return annotationConfigApplicationContext;
    }
}