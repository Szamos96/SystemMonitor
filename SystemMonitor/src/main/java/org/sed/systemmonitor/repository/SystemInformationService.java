package org.sed.systemmonitor.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sed.systemmonitor.config.MongoConfig;
import org.sed.systemmonitor.model.SystemInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SystemInformationService extends MongoRepository<SystemInformation, String>{

	
	
	//List<SystemInformation> findAllByTimestsampLessThanEqualAndTimestampGreaterThanEqual(Date endDate, Date startDate);

}
