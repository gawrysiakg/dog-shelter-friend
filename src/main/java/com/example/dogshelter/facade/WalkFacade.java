package com.example.dogshelter.facade;

import com.example.dogshelter.mapper.WalkMapper;
import com.example.dogshelter.service.WalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalkFacade {

    private final WalkService walkService;
    private final WalkMapper walkMapper;





}
