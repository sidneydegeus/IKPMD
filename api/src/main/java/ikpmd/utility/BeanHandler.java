package ikpmd.utility;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Sidney on 12/30/2016.
 */
public class BeanHandler {
    private static BeanHandler ourInstance = new BeanHandler();
    private AbstractApplicationContext context;

    public static BeanHandler getInstance() {
        return ourInstance;
    }

    private BeanHandler() {
        context = new ClassPathXmlApplicationContext("spring/application-context.xml");
        context.registerShutdownHook();
    }

/*    public JaarDAO createJaarDAO() {
        return (JaarDAO) context.getBean("jaarDAO");
    }

    public BedrijfslevenDAO createBedrijfslevenDAO() {
        return (BedrijfslevenDAO) context.getBean("bedrijfslevenDAO");
    }

    public BedrijfslevenDataDAO createBedrijfslevenDataDAO() {
        return (BedrijfslevenDataDAO) context.getBean("bedrijfslevenDataDAO");
    }

    public BedrijfslevenFactorenDAO createBedrijfslevenFactorenDAO() {
        return (BedrijfslevenFactorenDAO) context.getBean("bedrijfslevenFactorenDAO");
    }

    public BedrijfslevenCorrectiefactorenDAO createBedrijfslevenCorrectiefactorenDAO() {
        return (BedrijfslevenCorrectiefactorenDAO) context.getBean("bedrijfslevenCorrectiefactorenDAO");
    }

    public RegelcodeDAO createRegelcodeDAO() {
        return (RegelcodeDAO) context.getBean("regelcodeDAO");
    }

    public HoofdgroepDAO createHoofdgroepDAO() {
        return (HoofdgroepDAO) context.getBean("hoofdgroepDAO");
    }*/
}
