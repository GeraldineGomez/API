package com.simm.sensor.api.model.sensor_telemetry;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sensorTelemetry")
public class SensorTelemetry {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    @Column(name = "telemetry", nullable = false, length = (2000))
	private String telemetry;
    
	@Column(name = "createdDate", nullable = true, length = (500))
	private Date createdDate;
    
}
