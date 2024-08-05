package org.example.mappers;

import org.example.models.JobRole;
import org.example.models.JobRoleResponse;

import java.util.List;
import java.util.stream.Collectors;

public class JobRoleMapper {
    private JobRoleMapper jobRoleMapper;
    public static List<JobRoleResponse> mapJobRoleToJobRoleReponseList(
        final List<JobRole> jobRolesList) {
            return jobRolesList
                    .stream()
                    .map(jobRole -> new JobRoleResponse(jobRole.getId(),
                            jobRole.getRoleName(), jobRole.getLocation(),
                            jobRole.getCapabilityName(), jobRole.getBandName(),
                            jobRole.getClosingDate(), jobRole.getStatus()))
                    .collect(Collectors.toList());
        }
    }
