package org.sed.systemmonitor.util;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.sed.systemmonitor.model.CPU;
import org.sed.systemmonitor.model.GPU;
import org.sed.systemmonitor.model.HardDrive;
import org.sed.systemmonitor.model.Hardware;
import org.sed.systemmonitor.model.Memory;
import org.sed.systemmonitor.model.SystemInformation;

public class JTreeMapper {

	public static DefaultMutableTreeNode createFromHardware(Hardware hardware) {

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Hardware");

		CPU CPUObject = hardware.getCPU();
		DefaultMutableTreeNode cpu = new DefaultMutableTreeNode("CPU");

		DefaultMutableTreeNode cpuName = new DefaultMutableTreeNode("Name: " + CPUObject.getName());
		DefaultMutableTreeNode cpuMicroarchitecture = new DefaultMutableTreeNode(
				"Microarchitecture: " + CPUObject.getMicroarchitecture());
		DefaultMutableTreeNode cpuVendor = new DefaultMutableTreeNode("Vendor: " + CPUObject.getVendor());
		DefaultMutableTreeNode cpuFreq = new DefaultMutableTreeNode("Freq: " + CPUObject.getFreq());
		DefaultMutableTreeNode cpuLogicalCoreCount = new DefaultMutableTreeNode(
				"Logical core count: " + CPUObject.getLogicalCoreCount());
		DefaultMutableTreeNode cpuPhycicalCoreCount = new DefaultMutableTreeNode(
				"Physical core count: " + CPUObject.getPhycicalCoreCount());

		cpu.add(cpuName);
		cpu.add(cpuMicroarchitecture);
		cpu.add(cpuVendor);
		cpu.add(cpuFreq);
		cpu.add(cpuLogicalCoreCount);
		cpu.add(cpuPhycicalCoreCount);

		List<GPU> GPUObjects = hardware.getGPUs();
		DefaultMutableTreeNode gpus = new DefaultMutableTreeNode("GPUs");

		GPUObjects.forEach(GPU -> {

			DefaultMutableTreeNode gpuName = new DefaultMutableTreeNode(GPU.getName());
			DefaultMutableTreeNode gpuRam = new DefaultMutableTreeNode("VRAM: " + GPU.getVRAM());
			DefaultMutableTreeNode gpuVendor = new DefaultMutableTreeNode("Vendor: " + GPU.getVendor());

			gpus.add(gpuName);
			gpuName.add(gpuRam);
			gpuName.add(gpuVendor);

		});

		List<Memory> MemoryObjects = hardware.getMemories();
		DefaultMutableTreeNode mems = new DefaultMutableTreeNode("Memories");

		MemoryObjects.forEach(memory -> {

			DefaultMutableTreeNode memVendor = new DefaultMutableTreeNode(
					memory.getVendor() + " (" + memory.getBank() + ")");
			DefaultMutableTreeNode memType = new DefaultMutableTreeNode("Type: " + memory.getType());
			DefaultMutableTreeNode memCapacity = new DefaultMutableTreeNode("Capacity: " + memory.getCapacity());
			DefaultMutableTreeNode memClockSpeed = new DefaultMutableTreeNode("Clockspeed: " + memory.getClockSpeed());

			mems.add(memVendor);

			memVendor.add(memType);
			memVendor.add(memCapacity);
			memVendor.add(memClockSpeed);

		});

		List<HardDrive> HardDriveObjects = hardware.getHardDrives();
		DefaultMutableTreeNode harddrive = new DefaultMutableTreeNode("Hard drives");

		HardDriveObjects.forEach(hdd -> {

			DefaultMutableTreeNode hddModel = new DefaultMutableTreeNode(hdd.getModel());
			DefaultMutableTreeNode hddSize = new DefaultMutableTreeNode("Size: " + hdd.getSize());

			DefaultMutableTreeNode hddPartitions = new DefaultMutableTreeNode("Partitions");

			harddrive.add(hddModel);
			hddModel.add(hddSize);
			hddModel.add(hddPartitions);

			hdd.getPartitions().forEach(part -> {

				DefaultMutableTreeNode partMount = new DefaultMutableTreeNode("Mount: " + part.getMount());
				DefaultMutableTreeNode partSize = new DefaultMutableTreeNode("Size: " + part.getSize());

				hddPartitions.add(partMount);
				hddPartitions.add(partSize);

			});

		});

		top.add(cpu);
		top.add(gpus);
		top.add(mems);
		top.add(harddrive);

		return top;
	}
	
	public static DefaultMutableTreeNode createFromSystemInformationList(List<SystemInformation> systemInformations) {
		
		if(systemInformations.isEmpty()) {
			DefaultMutableTreeNode top = new DefaultMutableTreeNode();
			return top;
		}
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("System Informations ( "+systemInformations.get(0).getTimestamp()+" - " +systemInformations.get(systemInformations.size()-1).getTimestamp()+" )");
		
		
		
		systemInformations.forEach(systemInformation -> top.add(createFromSystemInformation(systemInformation)));
		
		return top;
		
	}

	public static DefaultMutableTreeNode createFromSystemInformation(SystemInformation systemInformation) {

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("System Information " +systemInformation.getTimestamp());

		top.add(createFromHardware(systemInformation.getHardware()));

		DefaultMutableTreeNode software = new DefaultMutableTreeNode("Software");

		software.add(new DefaultMutableTreeNode("Manufacturer: " + systemInformation.getSoftware().getManufacturer()));
		software.add(new DefaultMutableTreeNode("Family: " + systemInformation.getSoftware().getFamily()));
		software.add(new DefaultMutableTreeNode("Bit: " + systemInformation.getSoftware().getBitness()));
		software.add(new DefaultMutableTreeNode("Version: " + systemInformation.getSoftware().getVersion()));

		top.add(software);
		
		DefaultMutableTreeNode usage = new DefaultMutableTreeNode("Usage");
		
		usage.add(new DefaultMutableTreeNode("CPU Load percentage: "+systemInformation.getUsage().getCPULoadPercentage()));
		usage.add(new DefaultMutableTreeNode("Free memory: "+systemInformation.getUsage().getFreeMemory()));
		usage.add(new DefaultMutableTreeNode("Total memory: "+systemInformation.getUsage().getTotalMemory()));
		
		top.add(usage);
		
		top.add(new DefaultMutableTreeNode("Timestamp: "+systemInformation.getTimestamp()));
		
		return top;

	}

}
