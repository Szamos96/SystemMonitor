package org.sed.systemmonitor.util;

import java.lang.management.ManagementFactory;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.sed.systemmonitor.model.CPU;
import org.sed.systemmonitor.model.GPU;
import org.sed.systemmonitor.model.HardDrive;
import org.sed.systemmonitor.model.Hardware;
import org.sed.systemmonitor.model.Memory;
import org.sed.systemmonitor.model.Partition;
import org.sed.systemmonitor.model.Software;
import org.sed.systemmonitor.model.SystemInformation;
import org.sed.systemmonitor.model.Usage;

import com.sun.management.OperatingSystemMXBean;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;


public class SystemInformationFactory {

	private static SystemInformationFactory systemInformationFactoryInstance = null;

	private SystemInformationFactory() {
	}

	public static SystemInformationFactory getInstance() {
		if (systemInformationFactoryInstance == null) {
			systemInformationFactoryInstance = new SystemInformationFactory();
		}
		return systemInformationFactoryInstance;
	}

	public SystemInformation createSystemInformation() {

		SystemInfo systemInfo = new SystemInfo();
		HardwareAbstractionLayer hardwareLayer = systemInfo.getHardware();
		OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory
				.getOperatingSystemMXBean();

		Hardware hardware = createHardware(hardwareLayer);
		Software software = createSoftware(systemInfo.getOperatingSystem());
		Usage usage = createUsage(operatingSystemMXBean);

		return SystemInformation.builder().hardware(hardware).software(software).usage(usage).timestamp(new Timestamp(new Date().getTime())).build();

	}

	private Usage createUsage(OperatingSystemMXBean operatingSystemMXBean) {
		return Usage.builder().CPULoadPercentage(operatingSystemMXBean.getProcessCpuLoad())
				.freeMemory(operatingSystemMXBean.getFreePhysicalMemorySize())
				.totalMemory(operatingSystemMXBean.getTotalPhysicalMemorySize()).build();
	}

	private Software createSoftware(OperatingSystem operatingSystem) {
		return Software.builder().manufacturer(operatingSystem.getManufacturer()).family(operatingSystem.getFamily())
				.bitness(operatingSystem.getBitness()).version(operatingSystem.getVersionInfo().toString()).build();
	}

	private Hardware createHardware(HardwareAbstractionLayer hardwareLayer) {

		return Hardware.builder().CPU(createCPU(hardwareLayer.getProcessor()))
				.GPUs(createGPUs(hardwareLayer.getGraphicsCards())).memories(createMemories(hardwareLayer.getMemory()))
				.hardDrives(createHardDrives(hardwareLayer.getDiskStores())).build();
	}

	private CPU createCPU(CentralProcessor processor) {

		return CPU.builder().name(processor.getProcessorIdentifier().getName())
				.microarchitecture(processor.getProcessorIdentifier().getMicroarchitecture())
				.vendor(processor.getProcessorIdentifier().getVendor()).freq(processor.getMaxFreq())
				.logicalCoreCount(processor.getLogicalProcessorCount())
				.phycicalCoreCount(processor.getPhysicalProcessorCount()).build();
	}

	private List<GPU> createGPUs(List<GraphicsCard> graphicsCards) {

		return graphicsCards.stream().map(graphicsCard -> GPU.builder().name(graphicsCard.getName())
				.VRAM(graphicsCard.getVRam()).vendor(graphicsCard.getVendor()).build()).collect(Collectors.toList());
	}

	private List<Memory> createMemories(GlobalMemory globalMemory) {

		return globalMemory.getPhysicalMemory().stream()
				.map(memorySlot -> Memory.builder().vendor(memorySlot.getManufacturer())
						.type(memorySlot.getMemoryType()).capacity(memorySlot.getCapacity())
						.clockSpeed(memorySlot.getClockSpeed()).bank(memorySlot.getBankLabel()).build())
				.collect(Collectors.toList());
	}

	private List<HardDrive> createHardDrives(List<HWDiskStore> diskStores) {

		return diskStores.stream()
				.map(drive -> HardDrive.builder().model(drive.getModel()).size(drive.getSize())
						.partitions(drive.getPartitions().stream()
								.map(partition -> Partition.builder().mount(partition.getMountPoint())
										.size(partition.getSize()).build())
								.collect(Collectors.toList()))
						.build())
				.collect(Collectors.toList());
	}

}
