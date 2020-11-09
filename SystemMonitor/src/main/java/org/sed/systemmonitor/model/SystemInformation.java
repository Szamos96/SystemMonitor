package org.sed.systemmonitor.model;



import java.sql.Timestamp;
import java.util.Date;

import org.sed.systemmonitor.serializer.DateSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SystemInformation {
	
	@Id
	String id;
	
	Hardware hardware;
	
	Software software;
	
	Usage usage;
	
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@JsonDeserialize(using = DateSerializer.class)
	Date timestamp;

}
