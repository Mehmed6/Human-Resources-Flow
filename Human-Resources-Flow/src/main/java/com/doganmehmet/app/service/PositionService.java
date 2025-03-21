package com.doganmehmet.app.service;

import com.doganmehmet.app.dto.position.PositionDTO;
import com.doganmehmet.app.dto.position.PositionRequest;
import com.doganmehmet.app.enums.LogType;
import com.doganmehmet.app.exception.ApiException;
import com.doganmehmet.app.exception.MyError;
import com.doganmehmet.app.mapper.IPositionMapper;
import com.doganmehmet.app.repository.IDepartmentRepository;
import com.doganmehmet.app.repository.IPositionRepository;
import com.doganmehmet.app.utility.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    private final IPositionRepository m_positionRepository;
    private final IDepartmentRepository m_departmentRepository;
    private final IPositionMapper m_positionMapper;
    private final LogEntryService m_logEntryService;

    public PositionService(IPositionRepository positionRepository, IDepartmentRepository departmentRepository, IPositionMapper positionMapper, LogEntryService logEntryService)
    {
        m_positionRepository = positionRepository;
        m_departmentRepository = departmentRepository;
        m_positionMapper = positionMapper;
        m_logEntryService = logEntryService;
    }

    public PositionDTO savePosition(PositionRequest positionRequest)
    {
        var department = m_departmentRepository.findByDepartmentName(positionRequest.getDepartment())
                .orElseThrow(() -> new ApiException(MyError.DEPARTMENT_NOT_FOUND));

        var position = m_positionMapper.toPosition(positionRequest);
        position.setDepartment(department);
        m_logEntryService.logger(SecurityUtil.getUsername(), "Position successfully created", LogType.SUCCESSFUL);
        return m_positionMapper.toPositionDTO(m_positionRepository.save(position));
    }

    public PositionDTO findPositionById(long id)
    {
        return m_positionMapper.toPositionDTO(m_positionRepository.findById(id)
                .orElseThrow(() -> new ApiException(MyError.POSITION_NOT_FOUND)));
    }

    public PositionDTO findPositionByTitle(String positionTitle)
    {
        return m_positionMapper.toPositionDTO(m_positionRepository.findByTitle(positionTitle));
    }

    public List<PositionDTO> findAllPosition()
    {
        return m_positionMapper.toPositionDTOList(m_positionRepository.findAll());
    }

    public void deletePositionById(long id)
    {
        m_positionRepository.deleteById(id);
        m_logEntryService.logger(SecurityUtil.getUsername(), "Position successfully deleted", LogType.SUCCESSFUL);
    }
}
