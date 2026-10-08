package com.fsd.exp6.controller;

import com.fsd.exp6.dto.AnalyticsDashboardDto;
import com.fsd.exp6.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/dashboard")
    public ResponseEntity<AnalyticsDashboardDto> getAnalyticsDashboard() {
        AnalyticsDashboardDto dashboard = analyticsService.getDashboardMetrics();
        return ResponseEntity.ok(dashboard);
    }
}
