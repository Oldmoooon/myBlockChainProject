import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;


/**
 * @author guyue
 * @date 2018/4/20
 */
public class Application {
    private static Log log = LogFactory.get();

    public static void main(String... args) {
        MyServerAgent.init();
        log.info("agent init OK !");
        MyServerAgent.start();
        log.info("agent start OK !");
    }
}
