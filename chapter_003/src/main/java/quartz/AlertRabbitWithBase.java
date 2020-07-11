package quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import spamers.ImportDB;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Date;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

/**
 * 1.1. Job c параметрами [#281904]
 * @author Kirill Asmanov
 * @since 12.07.2020
 */
public class AlertRabbitWithBase {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        AlertRabbitWithBase alertRabbit = new AlertRabbitWithBase();
        Properties cfg = alertRabbit.getProperties();
        Class.forName(cfg.getProperty("jdbc.driver"));
        Integer interval = Integer.parseInt(cfg.getProperty("rabbit.interval"));
        try (Connection cn = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("connection", cn);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(interval)
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10001);
            scheduler.shutdown();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    /**
     * load properties from .properties file
     * @return config
     */
    private Properties getProperties() {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            cfg.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cfg;
    }

    public static class Rabbit implements Job {
        public Rabbit() {
            System.out.print("Зайчик создан " + System.lineSeparator());
        }

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            Connection cn = (Connection) context.getJobDetail().getJobDataMap().get("connection");
                try (PreparedStatement statement =
                             cn.prepareStatement("insert into rabbit(created_date) values (?)")) {
                    statement.setString(1, String.valueOf(new Date(System.currentTimeMillis())));
                    statement.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            System.out.printf("Зайчик прыгнул: " + new Date() + System.lineSeparator());
        }
    }
}
