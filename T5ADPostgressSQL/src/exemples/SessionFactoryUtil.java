package exemples;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SessionFactoryUtil {

	private static SessionFactory sF;

	public static SessionFactory getSessionFactory() {
		if (sF == null) {
			Configuration conf = new Configuration().configure();
			ServiceRegistryBuilder reg = new ServiceRegistryBuilder();
			reg.applySettings(conf.getProperties());
			ServiceRegistry serviceRegistry = reg.buildServiceRegistry();

			sF = conf.buildSessionFactory(serviceRegistry);
		}

		return sF;
	}
}