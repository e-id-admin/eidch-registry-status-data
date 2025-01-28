/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bit.eid.status_registry.data_service.controller;

import ch.admin.bit.eid.datastore.shared.service.DatastoreEntityService;
import ch.admin.bit.eid.datastore.vc.service.StatusListEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/statuslist")
@AllArgsConstructor
@Tag(name = "Trust Statement Controller", description = "Returns Trust Statement VC entries from the datastore.")
public class StatusListController {

    private final DatastoreEntityService datastoreEntityService;
    private final StatusListEntityService datastoreFileEntityService;

    @GetMapping(
            value = "/{datastoreEntryId}.jwt",
            produces = {"application/statuslist+jwt"}
    )
    @Operation(
            summary = "Get a tokenstatuslist entry from the datastore.",
            description = "Get a tokenstatuslist entry from the datastore."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns a list of encoded trust statement VCs",
                            useReturnTypeSchema = true
                    ),
            }
    )
    public String getTokenStatusList(
            @PathVariable(name = "datastoreEntryId") UUID datastoreEntryId
    ) {
        var base = this.datastoreEntityService.getDatastoreEntity(datastoreEntryId);
        this.datastoreEntityService.checkCanShow(base);
        return this.datastoreFileEntityService.getTokenStatusListJWT(datastoreEntryId);
    }
}
