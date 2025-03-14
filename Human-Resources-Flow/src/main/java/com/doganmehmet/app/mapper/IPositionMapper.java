package com.doganmehmet.app.mapper;

import com.doganmehmet.app.dto.position.PositionDTO;
import com.doganmehmet.app.dto.position.PositionRequest;
import com.doganmehmet.app.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(implementationName = "PositionMapperImpl", componentModel = "spring")
public interface IPositionMapper {

    @Mapping(source = "department", target = "department", ignore = true)
    Position toPosition(PositionRequest positionRequest);

    PositionDTO toPositionDTO(Position position);

    List<PositionDTO> toPositionDTOList(List<Position> positions);
}
