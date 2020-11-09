package org.sed.systemmonitor.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CPU {
	
	String name;
	String microarchitecture;
	String vendor;
	long freq;
	int logicalCoreCount;
	int phycicalCoreCount;

}
