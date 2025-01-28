/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bit.eid.datastore.vc.service;

import ch.admin.bit.eid.datastore.shared.exceptions.ResourceNotFoundException;
import ch.admin.bit.eid.datastore.vc.model.enums.VcTypeEnum;
import ch.admin.bit.eid.datastore.vc.repository.VcEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class StatusListEntityService {

    private final VcEntityRepository vcEntityRepository;

    public String getTokenStatusListJWT(UUID id) {
        var optionVc = this.vcEntityRepository.findByBase_IdAndVcType(id, VcTypeEnum.TokenStatusListJWT);

        if (!optionVc.isPresent())
            throw new ResourceNotFoundException(id.toString());

        return optionVc.get().getRawVc();
    }
}
