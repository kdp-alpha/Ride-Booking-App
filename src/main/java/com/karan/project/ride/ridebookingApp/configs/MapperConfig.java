package com.karan.project.ride.ridebookingApp.configs;

import com.karan.project.ride.ridebookingApp.dto.PointDto;
import com.karan.project.ride.ridebookingApp.utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(PointDto.class, Point.class).setConverter(context -> {
            PointDto pointdto = context.getSource();
            return GeometryUtil.createPoint(pointdto);
        });

        mapper.typeMap(Point.class,PointDto.class).setConverter(context -> {
            Point point = context.getSource();
            double coordinates[] = {
                    point.getX(),
                    point.getY()
            };

            return new PointDto(coordinates);
        });

        return mapper;
    }
}
