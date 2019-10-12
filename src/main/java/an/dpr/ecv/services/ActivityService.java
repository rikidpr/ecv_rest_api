package an.dpr.ecv.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import an.dpr.ecv.model.Activity;

@RequestScoped
public class ActivityService {

	@Transactional
	public void persist(Activity activity) {
		activity.persist();
	}

	public List<Activity> listAll() {
		return Activity.listAll();
	}

	public Activity findById(Long id) {
		return Activity.findById(id);
	}
	
	public List<Activity> findByLocation(String location) {
		return Activity.findByLocation(location);
	}
}
