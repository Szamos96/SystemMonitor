package org.sed.systemmonitor.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usage {

	double CPULoadPercentage;
	long freeMemory;
	long totalMemory;

}
