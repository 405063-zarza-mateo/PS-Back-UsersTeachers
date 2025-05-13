package org.utn.tup.ps.Service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.utn.tup.ps.Entity.AuditEntity;
import org.utn.tup.ps.Repository.AuditRepository;
import org.utn.tup.ps.Service.AuditService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditServiceImpl implements AuditService {
    private final AuditRepository repository;


    @Override
    public AuditEntity logCreate(String studentName, String teacherName) {
        AuditEntity entity = new AuditEntity();
        entity.setTime(LocalDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")));
        entity.setMessage(teacherName + " ha creado al alumno " + studentName);
        log.info("LOG DE CREAR EN PROCESO");
        return repository.save(entity);
    }

    @Override
    public AuditEntity logUpdate(String studentName, String teacherName) {
        AuditEntity entity = new AuditEntity();
        entity.setTime(LocalDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")));
        entity.setMessage(teacherName + " ha actualizado los datos del alumno " + studentName);
        log.info("LOG DE ACTUALIZAR EN PROCESO");

        return repository.save(entity);
    }


    @Override
    public AuditEntity logDelete(String studentName) {
        AuditEntity entity = new AuditEntity();
        entity.setTime(LocalDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")));
        entity.setMessage("Se ha eliminado al alumno " + studentName);
        log.info("LOG DE ELIMINAR EN PROCESO");

        return repository.save(entity);
    }

    @Override
    public AuditEntity logReview(String studentName, String teacherName) {
        AuditEntity entity = new AuditEntity();
        entity.setTime(LocalDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")));
        entity.setMessage(teacherName + " ha escrito una reseña para " + studentName);
        log.info("LOG DE REVIEW EN PROCESO");
        return repository.save(entity);
    }


    @Override
    public List<AuditEntity> getAuditLog() {
        return repository.findAll();
    }

    @Scheduled(cron = "@weekly")
    @Override
    public void cleanLogs() {
        log.info("BORRANDO LOGS");

        repository.deleteAll();
    }
}
