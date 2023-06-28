package com.example.backend.services.scheduled;

import com.example.backend.components.usercomponents.UserCRUDComponent;
import com.example.backend.components.usercomponents.UserCalcComponent;
import com.example.backend.entities.User;
import com.example.backend.services.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final UserCRUDComponent userCRUDComponent;
    private final UserCalcComponent userCalcComponent;
    @Autowired
    private MailSender mailSender;

    @Scheduled(cron = "@midnight")
    public void remindAll() {
        userCRUDComponent.get().forEach(user -> {
            if (userCalcComponent.calculateUncompletedTasks(user) != 0 && userCalcComponent.calculateUncompletedHabits(user) != 0) {
                sendNotification(user, userCalcComponent.calculateUncompletedTasks(user), userCalcComponent.calculateUncompletedTodayTasks(user));
            }
        });
    }

    private void sendNotification(User user, Integer tasks, Integer duties) {
        mailSender.send(user.getEmail(), "Notification!", "Hi! We're would like to remind you about your today tasks and duties. So, today you should do " + tasks.toString() + " uncompleted tasks and also you need to do " + duties.toString() + " daily tasks, and mark it on our service. Otherwise, all of this will be marked as undone, in the future it'll be impossible to change it!");
    }
}
