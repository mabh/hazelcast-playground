/*
* Copyright (c) 2013-2015 Software AG, Darmstadt, Germany 
* and/or Software AG USA Inc., Reston, VA, USA, and/or 
* its subsidiaries and or/its affiliates and/or their 
* licensors.
* Use, reproduction, transfer, publication or disclosure 
* is prohibited except as specifically provided for in your 
* License Agreement with Software AG.
*/

package app;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

public final class App {
	
	private static final class Employee implements DataSerializable {
		private String id;
		private String name;
		
		public Employee(final String id, final String name) {
			this.id = id;
			this.name = name;
		}
		
		public Employee() {}
		
		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}
		
		public String toString() {
			return this.name;
		}

		public void readData(ObjectDataInput in) throws IOException {
			this.id = in.readUTF();
			this.name = in.readUTF();
		}

		public void writeData(ObjectDataOutput out) throws IOException {
			out.writeUTF(this.id);
			out.writeUTF(this.name);
		}
	}
	
	public static void main(String[] args) {
		HazelcastInstance hcInstance1 = Hazelcast.newHazelcastInstance();
		Map<String, Employee> map1 = hcInstance1.getMap("employees");
		map1.put("1233", new Employee("1233", "John Day"));
		map1.put("2233", new Employee("1233", "John Way"));
		map1.put("3233", new Employee("1233", "John May"));
		
		HazelcastInstance hcInstance2 = Hazelcast.newHazelcastInstance();
		Map<String, Employee> map2 = hcInstance2.getMap("employees");
		System.out.println(map2.get("1233"));
		System.out.println(map2.get("2233"));
		System.out.println(map2.get("3233"));
	}
}
