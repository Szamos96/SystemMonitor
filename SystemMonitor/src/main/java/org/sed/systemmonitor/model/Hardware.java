package org.sed.systemmonitor.model;

import java.util.Date;
import java.util.List;

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
public class Hardware {
	
	CPU CPU;
	List<GPU> GPUs;
	List<Memory> memories;
	List<HardDrive> hardDrives;

}
