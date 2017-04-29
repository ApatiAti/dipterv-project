package hu.swagger;

import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hu.model.hospital.ConsultationHour;
import hu.service.interfaces.ConsultationHourService;
import hu.swagger.interfaces.ConsultationHourApiInterface;
import hu.swagger.util.ApiHospitalMapper;
import io.swagger.model.ConsultationHourSearch;

@Service
public class ConsultationHourApiImplementor extends AbstractImplementor implements ConsultationHourApiInterface{

	static final Logger logger = LoggerFactory.getLogger(ConsultationHourApiImplementor.class);

	@Autowired
	ConsultationHourService consultationHourService;
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	
	@Override
	public ResponseEntity<Object> apiConsultationHourSearchPost(ConsultationHourSearch request) {
		logger.debug("call apiConsultationHourSearchPost. Parameters  ConsultationHourSearch = " + request.toString());
		return handlingServiceCall("Sikeres ConsultationHour keresés"
				, "Sikertelen ConsultationHour keresés"
				, new Callable<Object>() {
					@Override
					public Object call() throws Exception {
						hu.model.hospital.dto.ConsultationHourSearch seachEntity = ApiHospitalMapper.mapConsultationHourSearchFromApi(request);
						
						List<ConsultationHour> consultationHourList = consultationHourService.sortConsultationHour(seachEntity, request.getDepartmentId());
						
						return ApiHospitalMapper.mapConsultationHourToApi(consultationHourList);
					}
				});
	}
	
	
}