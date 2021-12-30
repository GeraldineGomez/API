package com.simm.sensor.api.services;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import com.simm.sensor.api.model.sensor_telemetry.SensorTelemetry;
import com.simm.sensor.api.model.sensor_telemetry.SensorTelemetryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RestController
@RequestMapping("telemetry")
public class SensorTelemetryService {
     
    @Autowired
    private SensorTelemetryRepository telemetryRepository;

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SensorTelemetry getSensorTelemetry(
         @PathVariable("id") Long id) {
          SensorTelemetry sensorTelemetry = null;
          sensorTelemetry = telemetryRepository.findById(id).orElse(null);
        return sensorTelemetry;
    }
    
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<SensorTelemetry> getAllSensorTelemetry() {
        return telemetryRepository.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createSensorTelemetry( 
      @RequestBody String json) {
        String telemetry = json;
        SensorTelemetry sensorTelemetry = new SensorTelemetry();
        System.out.println("JSON: " +  telemetry);
        sensorTelemetry.setTelemetry(telemetry);
        sensorTelemetry.setCreatedDate(new Date());
        telemetryRepository.save(sensorTelemetry);
        return telemetry;
        
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody String telemetry) {

        SensorTelemetry sUpdate = telemetryRepository.findById(id).orElse(null);
        telemetryRepository.save(sUpdate);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        telemetryRepository.deleteById(id);
    }

}
