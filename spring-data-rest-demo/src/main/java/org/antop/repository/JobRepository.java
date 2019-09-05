package org.antop.repository;

import org.antop.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JobRepository extends JpaRepository<Job, String> {
}
