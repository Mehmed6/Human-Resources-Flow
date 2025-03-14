package com.doganmehmet.app.controller;

import com.doganmehmet.app.dto.position.PositionDTO;
import com.doganmehmet.app.dto.position.PositionRequest;
import com.doganmehmet.app.service.PositionService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/position")
public class PositionController {
    private final PositionService m_positionService;

    public PositionController(PositionService positionService)
    {
        m_positionService = positionService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public PositionDTO save(@Valid @RequestBody PositionRequest positionRequest)
    {
        return m_positionService.savePosition(positionRequest);
    }

    @GetMapping("/find/id/{positionId}")
    public PositionDTO findById(@PathVariable long positionId)
    {
        return m_positionService.findPositionById(positionId);
    }

    @GetMapping("/find/title/{positionTitle}")
    public PositionDTO findByTitle(@PathVariable String positionTitle)
    {
        return m_positionService.findPositionByTitle(positionTitle);
    }

    @GetMapping("/find/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<PositionDTO> findAll()
    {
        return m_positionService.findAllPosition();
    }

    @DeleteMapping("/delete/{positionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable long positionId)
    {
        m_positionService.deletePositionById(positionId);
    }
}
