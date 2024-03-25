package com.phsz.userservice.userserviceprovider.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity

public class Test {
	@Id
	@Column(name="test_id")
	public int test_id;

	@Column
	public int content;

	public int getTest_id() {
		return test_id;
	}

	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}

	@Override
	public String toString() {
		return "Test{" +
				"test_id=" + test_id +
				", content=" + content +
				'}';
	}
}
