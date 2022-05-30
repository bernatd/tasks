package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;


@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private DbService service;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provide connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        List<Task> task_list = service.getAllTasks();
/*        List<String> task_list = new ArrayList<>();
        for (Task task : tasks) {
            task_list.add(task.getTitle());
        }*/

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        context.setVariable("company_email", companyConfig.getCompanyEmail());
        context.setVariable("preview_message", "New card created");
        context.setVariable("goodbye_message", "Thank You,");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_header", "Application functionality:");
        context.setVariable("application_functionality", functionality);
        context.setVariable("isInformationEmail", true);
        context.setVariable("information_header", "Current tasks:");
        context.setVariable("task_list", task_list);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
