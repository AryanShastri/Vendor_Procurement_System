
package com.procurement.procurement.controller.audit;

import com.procurement.procurement.entity.audit.AuditLog;
import com.procurement.procurement.service.audit.AuditService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    // ===================== ALL LOGS =====================
    @GetMapping("/all")
    public List<AuditLog> getAllLogs() {
        return auditService.getAll();
    }

    // ===================== BY ENTITY =====================
    @GetMapping("/entity/{name}")
    public List<AuditLog> getByEntity(@PathVariable String name) {
        return auditService.getByEntity(name);
    }

    // ===================== BY USER =====================
    @GetMapping("/user/{username}")
    public List<AuditLog> getByUser(@PathVariable String username) {
        return auditService.getByUser(username);
    }
}