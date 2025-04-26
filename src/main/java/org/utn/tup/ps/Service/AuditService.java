package org.utn.tup.ps.Service;

import org.utn.tup.ps.Entity.AuditEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface AuditService {
    AuditEntity logCreate(String studentName, String teacherName   );

    AuditEntity logUpdate(String studentName, String teacherName   );

    AuditEntity logDelete(String studentName   );

    AuditEntity logReview(String studentName, String teacherName);

    List<AuditEntity> getAuditLog();

    void cleanLogs();
}
