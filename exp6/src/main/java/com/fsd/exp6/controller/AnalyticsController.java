package com.fsd.exp6.controller;

import com.fsd.exp6.dto.AnalyticsDashboardDto;
import com.fsd.exp6.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<AnalyticsDashboardDto> getAnalyticsDashboard() {
        AnalyticsDashboardDto dashboard = analyticsService.getDashboardMetrics();
        return ResponseEntity.ok(dashboard);
    }
}
