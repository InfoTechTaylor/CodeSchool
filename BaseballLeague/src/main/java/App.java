import controller.BaseballLeagueController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BaseballLeagueController controller = ctx.getBean("controller", BaseballLeagueController.class);

    }
}
