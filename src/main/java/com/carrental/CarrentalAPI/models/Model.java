package com.carrental.CarrentalAPI.models;

import com.carrental.CarrentalAPI.models.dto.ModelDto;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "carModel")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private Car car;
    @JoinColumn(name = "model_id")
    public static Model from(ModelDto modelDto){
        Model model = new Model();
        model.setId(model.getId());
        model.setName(modelDto.getName());
        return model;
    }

}
