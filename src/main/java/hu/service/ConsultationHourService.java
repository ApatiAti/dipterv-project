package hu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import hu.exception.BasicServiceException;
import hu.model.hospital.ConsultationHourType;
import hu.repository.hospital.ConsultationHourTypeRepository;

@Service
public class ConsultationHourService {

	@Autowired
	private ConsultationHourTypeRepository consultationHourTypeRepository;
	
	public List<ConsultationHourType> findConsultationHourTypeByDepartmentId(long departmentId) throws BasicServiceException{
		List<ConsultationHourType> types = consultationHourTypeRepository.findByDepartmentId(departmentId);
		
		if (CollectionUtils.isEmpty(types)){
			throw new BasicServiceException("ConsultationHourTypes not fount for Department");
		}
		
		return types;
	}
}
