package com.procurement.procurement.controller.procurement;

import com.procurement.procurement.entity.procurement.Requisition;
import com.procurement.procurement.repository.procurement.RequisitionRepository;
import com.procurement.procurement.service.procurement.RequisitionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/procurement/requisition")
public class RequisitionController {


    private final RequisitionService requisitionService;
    private final RequisitionRepository requisitionRepository;

    public RequisitionController(RequisitionService requisitionService,
                                 RequisitionRepository requisitionRepository) {
        this.requisitionService = requisitionService;
        this.requisitionRepository = requisitionRepository;
    }
    

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Requisition> createRequisition(
            @RequestBody Requisition requisition
    ) {
        return ResponseEntity.ok(
                requisitionService.createRequisition(requisition)
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<Requisition>> getAll() {
        return ResponseEntity.ok(requisitionRepository.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable Long id,
                                               @RequestParam String status) {

        Optional<Requisition> reqOpt = requisitionRepository.findById(id);
        if (reqOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Requisition not found");
        }

        Requisition requisition = reqOpt.get();
        requisition.setStatus(status);
        requisitionRepository.save(requisition);

        return ResponseEntity.ok("Requisition status updated to " + status);
    }
}